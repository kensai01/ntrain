import { IWorkoutStep } from 'app/entities/workout-step/workout-step.model';

export interface IWorkouts {
  id: number;
  title?: string | null;
  description?: string | null;
  workoutSteps?: Array<IWorkoutStep>;
  time?: number | null;
  videoId?: string | null;
  scaling?: string | null;
  beginnerDescription?: string | null;
  beginnerSteps?: Array<IWorkoutStep>;
  intermediateDescription?: string | null;
  intermidateSteps?: Array<IWorkoutStep>;
}

export type NewWorkouts = Omit<IWorkouts, 'id'> & { id: null };
