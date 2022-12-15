import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactUsComponent } from './contact-us.component';

const contactUsRoute: Routes = [
  {
    path: '',
    component: ContactUsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(contactUsRoute)],
  exports: [RouterModule],
})
export class ContactUsRoutingModule {}
