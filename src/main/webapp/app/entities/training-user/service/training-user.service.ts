import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITrainingUser, NewTrainingUser } from '../training-user.model';

export type PartialUpdateTrainingUser = Partial<ITrainingUser> & Pick<ITrainingUser, 'id'>;

export type EntityResponseType = HttpResponse<ITrainingUser>;
export type EntityArrayResponseType = HttpResponse<ITrainingUser[]>;

@Injectable({ providedIn: 'root' })
export class TrainingUserService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/training-users');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(trainingUser: NewTrainingUser): Observable<EntityResponseType> {
    return this.http.post<ITrainingUser>(this.resourceUrl, trainingUser, { observe: 'response' });
  }

  update(trainingUser: ITrainingUser): Observable<EntityResponseType> {
    return this.http.put<ITrainingUser>(`${this.resourceUrl}/${this.getTrainingUserIdentifier(trainingUser)}`, trainingUser, {
      observe: 'response',
    });
  }

  partialUpdate(trainingUser: PartialUpdateTrainingUser): Observable<EntityResponseType> {
    return this.http.patch<ITrainingUser>(`${this.resourceUrl}/${this.getTrainingUserIdentifier(trainingUser)}`, trainingUser, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITrainingUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITrainingUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTrainingUserIdentifier(trainingUser: Pick<ITrainingUser, 'id'>): number {
    return trainingUser.id;
  }

  compareTrainingUser(o1: Pick<ITrainingUser, 'id'> | null, o2: Pick<ITrainingUser, 'id'> | null): boolean {
    return o1 && o2 ? this.getTrainingUserIdentifier(o1) === this.getTrainingUserIdentifier(o2) : o1 === o2;
  }

  addTrainingUserToCollectionIfMissing<Type extends Pick<ITrainingUser, 'id'>>(
    trainingUserCollection: Type[],
    ...trainingUsersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const trainingUsers: Type[] = trainingUsersToCheck.filter(isPresent);
    if (trainingUsers.length > 0) {
      const trainingUserCollectionIdentifiers = trainingUserCollection.map(
        trainingUserItem => this.getTrainingUserIdentifier(trainingUserItem)!
      );
      const trainingUsersToAdd = trainingUsers.filter(trainingUserItem => {
        const trainingUserIdentifier = this.getTrainingUserIdentifier(trainingUserItem);
        if (trainingUserCollectionIdentifiers.includes(trainingUserIdentifier)) {
          return false;
        }
        trainingUserCollectionIdentifiers.push(trainingUserIdentifier);
        return true;
      });
      return [...trainingUsersToAdd, ...trainingUserCollection];
    }
    return trainingUserCollection;
  }
}
