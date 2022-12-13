import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TrainingUserDetailComponent } from './training-user-detail.component';

describe('TrainingUser Management Detail Component', () => {
  let comp: TrainingUserDetailComponent;
  let fixture: ComponentFixture<TrainingUserDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TrainingUserDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ trainingUser: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TrainingUserDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TrainingUserDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load trainingUser on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.trainingUser).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
