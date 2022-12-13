import { ITrainingUser, NewTrainingUser } from './training-user.model';

export const sampleWithRequiredData: ITrainingUser = {
  id: 38574,
  firstName: 'Milford',
  lastName: 'Kuphal',
};

export const sampleWithPartialData: ITrainingUser = {
  id: 23210,
  firstName: 'Hailee',
  lastName: 'Connelly',
};

export const sampleWithFullData: ITrainingUser = {
  id: 41522,
  firstName: 'Maximillia',
  lastName: 'MacGyver',
};

export const sampleWithNewData: NewTrainingUser = {
  firstName: 'Enid',
  lastName: 'Schultz',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
