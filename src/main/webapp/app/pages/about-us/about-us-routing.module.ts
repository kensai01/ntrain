import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us.component';

const aboutUsRoute: Routes = [
  {
    path: '',
    component: AboutUsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(aboutUsRoute)],
  exports: [RouterModule],
})
export class AboutUsRoutingModule {}
