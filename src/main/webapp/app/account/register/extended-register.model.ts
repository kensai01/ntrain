import { Registration } from './register.model';

export class ExtendedRegistration extends Registration {
  constructor(
    public login: string,
    public email: string,
    public firstName: string,
    public lastName: string,
    public password: string,
    public langKey: string
  ) {
    super(login, email, password, langKey);
  }
}
