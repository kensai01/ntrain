import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITrainingUser } from '../training-user.model';
import { TrainingUserService } from '../service/training-user.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './training-user-delete-dialog.component.html',
})
export class TrainingUserDeleteDialogComponent {
  trainingUser?: ITrainingUser;

  constructor(protected trainingUserService: TrainingUserService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.trainingUserService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
