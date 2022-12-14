import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVideos } from '../videos.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../videos.test-samples';

import { VideosService } from './videos.service';

const requireRestSample: IVideos = {
  ...sampleWithRequiredData,
};

describe('Videos Service', () => {
  let service: VideosService;
  let httpMock: HttpTestingController;
  let expectedResult: IVideos | IVideos[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VideosService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should return a list of Video', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addVideoToCollectionIfMissing', () => {
      it('should add a Video to an empty array', () => {
        const video: IVideos = sampleWithRequiredData;
        expectedResult = service.addVideoToCollectionIfMissing([], video);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(video);
      });

      it('should not add a Video to an array that contains it', () => {
        const video: IVideos = sampleWithRequiredData;
        const videoCollection: IVideos[] = [
          {
            ...video,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addVideoToCollectionIfMissing(videoCollection, video);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Video to an array that doesn't contain it", () => {
        const video: IVideos = sampleWithRequiredData;
        const videoCollection: IVideos[] = [sampleWithPartialData];
        expectedResult = service.addVideoToCollectionIfMissing(videoCollection, video);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(video);
      });

      it('should add only unique Video to an array', () => {
        const videoArray: IVideos[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const videoCollection: IVideos[] = [sampleWithRequiredData];
        expectedResult = service.addVideoToCollectionIfMissing(videoCollection, ...videoArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const video: IVideos = sampleWithRequiredData;
        const video2: IVideos = sampleWithPartialData;
        expectedResult = service.addVideoToCollectionIfMissing([], video, video2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(video);
        expect(expectedResult).toContain(video2);
      });

      it('should accept null and undefined values', () => {
        const video: IVideos = sampleWithRequiredData;
        expectedResult = service.addVideoToCollectionIfMissing([], null, video, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(video);
      });

      it('should return initial array if no Video is added', () => {
        const videoCollection: IVideos[] = [sampleWithRequiredData];
        expectedResult = service.addVideoToCollectionIfMissing(videoCollection, undefined, null);
        expect(expectedResult).toEqual(videoCollection);
      });
    });

    describe('compareVideo', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareVideos(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareVideos(entity1, entity2);
        const compareResult2 = service.compareVideos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareVideos(entity1, entity2);
        const compareResult2 = service.compareVideos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareVideos(entity1, entity2);
        const compareResult2 = service.compareVideos(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
