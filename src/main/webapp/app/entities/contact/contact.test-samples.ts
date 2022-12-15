import { IContact, NewContact } from './contact.model';

export const sampleWithRequiredData: IContact = {
  id: 36482,
  firstName: 'Alvera',
  lastName: 'Schiller',
  email: 'Colin76@yahoo.com',
  message: 'transmitting Sudan SDD',
};

export const sampleWithPartialData: IContact = {
  id: 35477,
  firstName: 'Marjory',
  lastName: 'Pagac',
  email: 'Jefferey.Pouros26@gmail.com',
  message: 'Beauty Berkshire Manager',
};

export const sampleWithFullData: IContact = {
  id: 50638,
  firstName: 'Tatum',
  lastName: 'Blick',
  email: 'Mya_Waters10@yahoo.com',
  message: 'pink generating THX',
};

export const sampleWithNewData: NewContact = {
  firstName: 'Curt',
  lastName: 'Pfannerstill',
  email: 'Kennedi55@gmail.com',
  message: 'Administrator primary',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
