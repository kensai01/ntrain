import { IUser } from 'app/entities/user/user.model';

export interface ITrainingUser {
  id: number;
  firstName?: string | null;
  lastName?: string | null;
  internalUser?: Pick<IUser, 'id'> | null;
}

export type NewTrainingUser = Omit<ITrainingUser, 'id'> & { id: null };
