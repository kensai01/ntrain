import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ContactUsRoutingModule } from './contact-us-routing.module';
import { ContactUsComponent } from './contact-us.component';

@NgModule({
  imports: [SharedModule, ContactUsRoutingModule],
  declarations: [ContactUsComponent],
})
export class ContactUsModule {}
