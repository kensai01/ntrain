import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TrainingUserFormService } from './training-user-form.service';
import { TrainingUserService } from '../service/training-user.service';
import { ITrainingUser } from '../training-user.model';

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';

import { TrainingUserUpdateComponent } from './training-user-update.component';

describe('TrainingUser Management Update Component', () => {
  let comp: TrainingUserUpdateComponent;
  let fixture: ComponentFixture<TrainingUserUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let trainingUserFormService: TrainingUserFormService;
  let trainingUserService: TrainingUserService;
  let userService: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TrainingUserUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(TrainingUserUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TrainingUserUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    trainingUserFormService = TestBed.inject(TrainingUserFormService);
    trainingUserService = TestBed.inject(TrainingUserService);
    userService = TestBed.inject(UserService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call User query and add missing value', () => {
      const trainingUser: ITrainingUser = { id: 456 };
      const internalUser: IUser = { id: 8632 };
      trainingUser.internalUser = internalUser;

      const userCollection: IUser[] = [{ id: 36156 }];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
      const additionalUsers = [internalUser];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ trainingUser });
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining)
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const trainingUser: ITrainingUser = { id: 456 };
      const internalUser: IUser = { id: 95385 };
      trainingUser.internalUser = internalUser;

      activatedRoute.data = of({ trainingUser });
      comp.ngOnInit();

      expect(comp.usersSharedCollection).toContain(internalUser);
      expect(comp.trainingUser).toEqual(trainingUser);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITrainingUser>>();
      const trainingUser = { id: 123 };
      jest.spyOn(trainingUserFormService, 'getTrainingUser').mockReturnValue(trainingUser);
      jest.spyOn(trainingUserService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trainingUser });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trainingUser }));
      saveSubject.complete();

      // THEN
      expect(trainingUserFormService.getTrainingUser).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(trainingUserService.update).toHaveBeenCalledWith(expect.objectContaining(trainingUser));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITrainingUser>>();
      const trainingUser = { id: 123 };
      jest.spyOn(trainingUserFormService, 'getTrainingUser').mockReturnValue({ id: null });
      jest.spyOn(trainingUserService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trainingUser: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trainingUser }));
      saveSubject.complete();

      // THEN
      expect(trainingUserFormService.getTrainingUser).toHaveBeenCalled();
      expect(trainingUserService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITrainingUser>>();
      const trainingUser = { id: 123 };
      jest.spyOn(trainingUserService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trainingUser });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(trainingUserService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareUser', () => {
      it('Should forward to userService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(userService, 'compareUser');
        comp.compareUser(entity, entity2);
        expect(userService.compareUser).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
