import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITrainingUser } from '../training-user.model';
import { TrainingUserService } from '../service/training-user.service';

@Injectable({ providedIn: 'root' })
export class TrainingUserRoutingResolveService implements Resolve<ITrainingUser | null> {
  constructor(protected service: TrainingUserService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITrainingUser | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((trainingUser: HttpResponse<ITrainingUser>) => {
          if (trainingUser.body) {
            return of(trainingUser.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
