import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { TrainingUserFormService, TrainingUserFormGroup } from './training-user-form.service';
import { ITrainingUser } from '../training-user.model';
import { TrainingUserService } from '../service/training-user.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';

@Component({
  selector: 'jhi-training-user-update',
  templateUrl: './training-user-update.component.html',
})
export class TrainingUserUpdateComponent implements OnInit {
  isSaving = false;
  trainingUser: ITrainingUser | null = null;

  usersSharedCollection: IUser[] = [];

  editForm: TrainingUserFormGroup = this.trainingUserFormService.createTrainingUserFormGroup();

  constructor(
    protected trainingUserService: TrainingUserService,
    protected trainingUserFormService: TrainingUserFormService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareUser = (o1: IUser | null, o2: IUser | null): boolean => this.userService.compareUser(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trainingUser }) => {
      this.trainingUser = trainingUser;
      if (trainingUser) {
        this.updateForm(trainingUser);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const trainingUser = this.trainingUserFormService.getTrainingUser(this.editForm);
    if (trainingUser.id !== null) {
      this.subscribeToSaveResponse(this.trainingUserService.update(trainingUser));
    } else {
      this.subscribeToSaveResponse(this.trainingUserService.create(trainingUser));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrainingUser>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(trainingUser: ITrainingUser): void {
    this.trainingUser = trainingUser;
    this.trainingUserFormService.resetForm(this.editForm, trainingUser);

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing<IUser>(
      this.usersSharedCollection,
      trainingUser.internalUser
    );
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing<IUser>(users, this.trainingUser?.internalUser)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));
  }
}
