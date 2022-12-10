import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PricingComponent } from '../list/pricing.component';
import { PricingRoutingResolveService } from './pricing-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const priceRoute: Routes = [
  {
    path: '',
    component: PricingComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(priceRoute)],
  exports: [RouterModule],
})
export class PricingRoutingModule {}
