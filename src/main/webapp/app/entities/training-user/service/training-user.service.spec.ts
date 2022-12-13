import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITrainingUser } from '../training-user.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../training-user.test-samples';

import { TrainingUserService } from './training-user.service';

const requireRestSample: ITrainingUser = {
  ...sampleWithRequiredData,
};

describe('TrainingUser Service', () => {
  let service: TrainingUserService;
  let httpMock: HttpTestingController;
  let expectedResult: ITrainingUser | ITrainingUser[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TrainingUserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a TrainingUser', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const trainingUser = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(trainingUser).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TrainingUser', () => {
      const trainingUser = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(trainingUser).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TrainingUser', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TrainingUser', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TrainingUser', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTrainingUserToCollectionIfMissing', () => {
      it('should add a TrainingUser to an empty array', () => {
        const trainingUser: ITrainingUser = sampleWithRequiredData;
        expectedResult = service.addTrainingUserToCollectionIfMissing([], trainingUser);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(trainingUser);
      });

      it('should not add a TrainingUser to an array that contains it', () => {
        const trainingUser: ITrainingUser = sampleWithRequiredData;
        const trainingUserCollection: ITrainingUser[] = [
          {
            ...trainingUser,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTrainingUserToCollectionIfMissing(trainingUserCollection, trainingUser);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TrainingUser to an array that doesn't contain it", () => {
        const trainingUser: ITrainingUser = sampleWithRequiredData;
        const trainingUserCollection: ITrainingUser[] = [sampleWithPartialData];
        expectedResult = service.addTrainingUserToCollectionIfMissing(trainingUserCollection, trainingUser);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(trainingUser);
      });

      it('should add only unique TrainingUser to an array', () => {
        const trainingUserArray: ITrainingUser[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const trainingUserCollection: ITrainingUser[] = [sampleWithRequiredData];
        expectedResult = service.addTrainingUserToCollectionIfMissing(trainingUserCollection, ...trainingUserArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const trainingUser: ITrainingUser = sampleWithRequiredData;
        const trainingUser2: ITrainingUser = sampleWithPartialData;
        expectedResult = service.addTrainingUserToCollectionIfMissing([], trainingUser, trainingUser2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(trainingUser);
        expect(expectedResult).toContain(trainingUser2);
      });

      it('should accept null and undefined values', () => {
        const trainingUser: ITrainingUser = sampleWithRequiredData;
        expectedResult = service.addTrainingUserToCollectionIfMissing([], null, trainingUser, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(trainingUser);
      });

      it('should return initial array if no TrainingUser is added', () => {
        const trainingUserCollection: ITrainingUser[] = [sampleWithRequiredData];
        expectedResult = service.addTrainingUserToCollectionIfMissing(trainingUserCollection, undefined, null);
        expect(expectedResult).toEqual(trainingUserCollection);
      });
    });

    describe('compareTrainingUser', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTrainingUser(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTrainingUser(entity1, entity2);
        const compareResult2 = service.compareTrainingUser(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTrainingUser(entity1, entity2);
        const compareResult2 = service.compareTrainingUser(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTrainingUser(entity1, entity2);
        const compareResult2 = service.compareTrainingUser(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
