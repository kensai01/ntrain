import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITrainingUser, NewTrainingUser } from '../training-user.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITrainingUser for edit and NewTrainingUserFormGroupInput for create.
 */
type TrainingUserFormGroupInput = ITrainingUser | PartialWithRequiredKeyOf<NewTrainingUser>;

type TrainingUserFormDefaults = Pick<NewTrainingUser, 'id'>;

type TrainingUserFormGroupContent = {
  id: FormControl<ITrainingUser['id'] | NewTrainingUser['id']>;
  firstName: FormControl<ITrainingUser['firstName']>;
  lastName: FormControl<ITrainingUser['lastName']>;
  internalUser: FormControl<ITrainingUser['internalUser']>;
};

export type TrainingUserFormGroup = FormGroup<TrainingUserFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TrainingUserFormService {
  createTrainingUserFormGroup(trainingUser: TrainingUserFormGroupInput = { id: null }): TrainingUserFormGroup {
    const trainingUserRawValue = {
      ...this.getFormDefaults(),
      ...trainingUser,
    };
    return new FormGroup<TrainingUserFormGroupContent>({
      id: new FormControl(
        { value: trainingUserRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      firstName: new FormControl(trainingUserRawValue.firstName, {
        validators: [Validators.required],
      }),
      lastName: new FormControl(trainingUserRawValue.lastName, {
        validators: [Validators.required],
      }),
      internalUser: new FormControl(trainingUserRawValue.internalUser),
    });
  }

  getTrainingUser(form: TrainingUserFormGroup): ITrainingUser | NewTrainingUser {
    return form.getRawValue() as ITrainingUser | NewTrainingUser;
  }

  resetForm(form: TrainingUserFormGroup, trainingUser: TrainingUserFormGroupInput): void {
    const trainingUserRawValue = { ...this.getFormDefaults(), ...trainingUser };
    form.reset(
      {
        ...trainingUserRawValue,
        id: { value: trainingUserRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TrainingUserFormDefaults {
    return {
      id: null,
    };
  }
}
