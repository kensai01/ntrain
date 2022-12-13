import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'price',
        data: { pageTitle: 'nitetrainApp.price.home.title' },
        loadChildren: () => import('./price/price.module').then(m => m.PriceModule),
      },
      {
        path: 'workout',
        data: { pageTitle: 'nitetrainApp.workout.home.title' },
        loadChildren: () => import('./workout/workout.module').then(m => m.WorkoutModule),
      },
      {
        path: 'beginner-workout',
        data: { pageTitle: 'nitetrainApp.beginnerWorkout.home.title' },
        loadChildren: () => import('./beginner-workout/beginner-workout.module').then(m => m.BeginnerWorkoutModule),
      },
      {
        path: 'intermediate-workout',
        data: { pageTitle: 'nitetrainApp.intermediateWorkout.home.title' },
        loadChildren: () => import('./intermediate-workout/intermediate-workout.module').then(m => m.IntermediateWorkoutModule),
      },
      {
        path: 'workout-step',
        data: { pageTitle: 'nitetrainApp.workoutStep.home.title' },
        loadChildren: () => import('./workout-step/workout-step.module').then(m => m.WorkoutStepModule),
      },
      {
        path: 'training-user',
        data: { pageTitle: 'nitetrainApp.trainingUser.home.title' },
        loadChildren: () => import('./training-user/training-user.module').then(m => m.TrainingUserModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
