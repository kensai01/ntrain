import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AboutUsRoutingModule } from './about-us-routing.module';
import { AboutUsComponent } from './about-us.component';

@NgModule({
  imports: [SharedModule, AboutUsRoutingModule],
  declarations: [AboutUsComponent],
})
export class AboutUsModule {}
