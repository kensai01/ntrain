import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IWorkouts, NewWorkouts } from '../workouts.model';

export type PartialUpdateWorkout = Partial<IWorkouts> & Pick<IWorkouts, 'id'>;

export type EntityResponseType = HttpResponse<IWorkouts>;
export type EntityArrayResponseType = HttpResponse<IWorkouts[]>;

@Injectable({ providedIn: 'root' })
export class WorkoutsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/pages/workouts');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWorkouts[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getWorkoutIdentifier(workout: Pick<IWorkouts, 'id'>): number {
    return workout.id;
  }

  compareWorkout(o1: Pick<IWorkouts, 'id'> | null, o2: Pick<IWorkouts, 'id'> | null): boolean {
    return o1 && o2 ? this.getWorkoutIdentifier(o1) === this.getWorkoutIdentifier(o2) : o1 === o2;
  }

  addWorkoutToCollectionIfMissing<Type extends Pick<IWorkouts, 'id'>>(
    workoutCollection: Type[],
    ...workoutsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const workouts: Type[] = workoutsToCheck.filter(isPresent);
    if (workouts.length > 0) {
      const workoutCollectionIdentifiers = workoutCollection.map(workoutItem => this.getWorkoutIdentifier(workoutItem)!);
      const workoutsToAdd = workouts.filter(workoutItem => {
        const workoutIdentifier = this.getWorkoutIdentifier(workoutItem);
        if (workoutCollectionIdentifiers.includes(workoutIdentifier)) {
          return false;
        }
        workoutCollectionIdentifiers.push(workoutIdentifier);
        return true;
      });
      return [...workoutsToAdd, ...workoutCollection];
    }
    return workoutCollection;
  }
}
