import { HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { IContact } from 'app/entities/contact/contact.model';
import { ContactService } from 'app/entities/contact/service/contact.service';
import { ContactFormGroup, ContactFormService } from 'app/entities/contact/update/contact-form.service';
import { finalize, Observable } from 'rxjs';

@Component({
  selector: 'jhi-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.scss', '../pages-shared.scss'],
})
export class ContactUsComponent {
  isSaving = false;
  contact: IContact | null = null;
  editForm: ContactFormGroup = this.contactFormService.createContactFormGroup();

  constructor(private contactService: ContactService, protected contactFormService: ContactFormService) {}

  save(): void {
    this.isSaving = true;
    const contact = this.contactFormService.getContact(this.editForm);
    if (contact.id !== null) {
      this.subscribeToSaveResponse(this.contactService.update(contact));
    } else {
      this.subscribeToSaveResponse(this.contactService.create(contact));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContact>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  previousState(): void {
    window.history.back();
  }
}
