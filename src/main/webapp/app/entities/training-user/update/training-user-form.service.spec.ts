import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../training-user.test-samples';

import { TrainingUserFormService } from './training-user-form.service';

describe('TrainingUser Form Service', () => {
  let service: TrainingUserFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrainingUserFormService);
  });

  describe('Service methods', () => {
    describe('createTrainingUserFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTrainingUserFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            firstName: expect.any(Object),
            lastName: expect.any(Object),
            internalUser: expect.any(Object),
          })
        );
      });

      it('passing ITrainingUser should create a new form with FormGroup', () => {
        const formGroup = service.createTrainingUserFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            firstName: expect.any(Object),
            lastName: expect.any(Object),
            internalUser: expect.any(Object),
          })
        );
      });
    });

    describe('getTrainingUser', () => {
      it('should return NewTrainingUser for default TrainingUser initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createTrainingUserFormGroup(sampleWithNewData);

        const trainingUser = service.getTrainingUser(formGroup) as any;

        expect(trainingUser).toMatchObject(sampleWithNewData);
      });

      it('should return NewTrainingUser for empty TrainingUser initial value', () => {
        const formGroup = service.createTrainingUserFormGroup();

        const trainingUser = service.getTrainingUser(formGroup) as any;

        expect(trainingUser).toMatchObject({});
      });

      it('should return ITrainingUser', () => {
        const formGroup = service.createTrainingUserFormGroup(sampleWithRequiredData);

        const trainingUser = service.getTrainingUser(formGroup) as any;

        expect(trainingUser).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITrainingUser should not enable id FormControl', () => {
        const formGroup = service.createTrainingUserFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTrainingUser should disable id FormControl', () => {
        const formGroup = service.createTrainingUserFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
