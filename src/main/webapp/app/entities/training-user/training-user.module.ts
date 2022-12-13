import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TrainingUserComponent } from './list/training-user.component';
import { TrainingUserDetailComponent } from './detail/training-user-detail.component';
import { TrainingUserUpdateComponent } from './update/training-user-update.component';
import { TrainingUserDeleteDialogComponent } from './delete/training-user-delete-dialog.component';
import { TrainingUserRoutingModule } from './route/training-user-routing.module';

@NgModule({
  imports: [SharedModule, TrainingUserRoutingModule],
  declarations: [TrainingUserComponent, TrainingUserDetailComponent, TrainingUserUpdateComponent, TrainingUserDeleteDialogComponent],
})
export class TrainingUserModule {}
