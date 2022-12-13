import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TrainingUserComponent } from '../list/training-user.component';
import { TrainingUserDetailComponent } from '../detail/training-user-detail.component';
import { TrainingUserUpdateComponent } from '../update/training-user-update.component';
import { TrainingUserRoutingResolveService } from './training-user-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const trainingUserRoute: Routes = [
  {
    path: '',
    component: TrainingUserComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TrainingUserDetailComponent,
    resolve: {
      trainingUser: TrainingUserRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TrainingUserUpdateComponent,
    resolve: {
      trainingUser: TrainingUserRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TrainingUserUpdateComponent,
    resolve: {
      trainingUser: TrainingUserRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(trainingUserRoute)],
  exports: [RouterModule],
})
export class TrainingUserRoutingModule {}
