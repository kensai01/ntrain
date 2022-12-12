import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { WorkoutsComponent } from '../list/workouts.component';
import { ASC } from 'app/config/navigation.constants';

const workoutRoute: Routes = [
  {
    path: '',
    component: WorkoutsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(workoutRoute)],
  exports: [RouterModule],
})
export class WorkoutsRoutingModule {}
