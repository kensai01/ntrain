import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { VideosComponent } from '../list/videos.component';

const workoutRoute: Routes = [
  {
    path: '',
    component: VideosComponent,
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
export class VideosRoutingModule {}
