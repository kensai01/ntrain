import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IWorkouts } from '../workouts.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../workouts.test-samples';

import { WorkoutsService } from './workout.service';

const requireRestSample: IWorkouts = {
  ...sampleWithRequiredData,
};

describe('Workouts Service', () => {
  let service: WorkoutsService;
  let httpMock: HttpTestingController;
  let expectedResult: IWorkouts | IWorkouts[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(WorkoutsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should return a list of Workout', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addWorkoutToCollectionIfMissing', () => {
      it('should add a Workout to an empty array', () => {
        const workout: IWorkouts = sampleWithRequiredData;
        expectedResult = service.addWorkoutToCollectionIfMissing([], workout);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(workout);
      });

      it('should not add a Workout to an array that contains it', () => {
        const workout: IWorkouts = sampleWithRequiredData;
        const workoutCollection: IWorkouts[] = [
          {
            ...workout,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addWorkoutToCollectionIfMissing(workoutCollection, workout);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Workout to an array that doesn't contain it", () => {
        const workout: IWorkouts = sampleWithRequiredData;
        const workoutCollection: IWorkouts[] = [sampleWithPartialData];
        expectedResult = service.addWorkoutToCollectionIfMissing(workoutCollection, workout);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(workout);
      });

      it('should add only unique Workout to an array', () => {
        const workoutArray: IWorkouts[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const workoutCollection: IWorkouts[] = [sampleWithRequiredData];
        expectedResult = service.addWorkoutToCollectionIfMissing(workoutCollection, ...workoutArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const workout: IWorkouts = sampleWithRequiredData;
        const workout2: IWorkouts = sampleWithPartialData;
        expectedResult = service.addWorkoutToCollectionIfMissing([], workout, workout2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(workout);
        expect(expectedResult).toContain(workout2);
      });

      it('should accept null and undefined values', () => {
        const workout: IWorkouts = sampleWithRequiredData;
        expectedResult = service.addWorkoutToCollectionIfMissing([], null, workout, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(workout);
      });

      it('should return initial array if no Workout is added', () => {
        const workoutCollection: IWorkouts[] = [sampleWithRequiredData];
        expectedResult = service.addWorkoutToCollectionIfMissing(workoutCollection, undefined, null);
        expect(expectedResult).toEqual(workoutCollection);
      });
    });

    describe('compareWorkout', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareWorkout(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareWorkout(entity1, entity2);
        const compareResult2 = service.compareWorkout(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareWorkout(entity1, entity2);
        const compareResult2 = service.compareWorkout(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareWorkout(entity1, entity2);
        const compareResult2 = service.compareWorkout(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
