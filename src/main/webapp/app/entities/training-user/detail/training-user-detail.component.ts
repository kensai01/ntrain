import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrainingUser } from '../training-user.model';

@Component({
  selector: 'jhi-training-user-detail',
  templateUrl: './training-user-detail.component.html',
})
export class TrainingUserDetailComponent implements OnInit {
  trainingUser: ITrainingUser | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trainingUser }) => {
      this.trainingUser = trainingUser;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
