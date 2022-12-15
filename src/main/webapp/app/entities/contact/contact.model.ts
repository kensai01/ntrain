export interface IContact {
  id: number;
  firstName?: string | null;
  lastName?: string | null;
  email?: string | null;
  message?: string | null;
}

export type NewContact = Omit<IContact, 'id'> & { id: null };
