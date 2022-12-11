import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { WorkoutsComponent } from './list/workouts.component';
import { WorkoutsRoutingModule } from './route/workouts-routing.module';

@NgModule({
  imports: [SharedModule, WorkoutsRoutingModule],
  declarations: [WorkoutsComponent],
})
export class WorkoutsModule {}
