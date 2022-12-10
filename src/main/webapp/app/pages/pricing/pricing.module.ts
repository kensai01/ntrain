import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PricingComponent } from './list/pricing.component';
import { PricingRoutingModule } from './route/pricing-routing.module';

@NgModule({
  imports: [SharedModule, PricingRoutingModule],
  declarations: [PricingComponent],
})
export class PricingModule {}
