import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';
import { Authority } from 'app/config/authority.constants';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PricingModule } from './pages/pricing/pricing.module';
import { WorkoutsModule } from './pages/workouts/workouts.module';
import { VideosModule } from './pages/videos/videos.module';
import { ContactUsModule } from './pages/contact-us/contact-us.module';
import { AboutUsModule } from './pages/about-us/about-us.module';

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: 'admin',
          data: {
            authorities: [Authority.ADMIN],
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule),
        },
        {
          path: 'account',
          loadChildren: () => import('./account/account.module').then(m => m.AccountModule),
        },
        {
          path: 'login',
          loadChildren: () => import('./login/login.module').then(m => m.LoginModule),
        },
        {
          path: '',
          loadChildren: () => import(`./entities/entity-routing.module`).then(m => m.EntityRoutingModule),
        },
        {
          path: 'pricing',
          loadChildren: () => import('./pages/pricing/pricing.module').then(m => PricingModule),
        },
        {
          path: 'pages/workouts',
          loadChildren: () => import('./pages/workouts/workouts.module').then(m => WorkoutsModule),
        },
        {
          path: 'pages/videos',
          loadChildren: () => import('./pages/videos/videos.module').then(m => VideosModule),
        },
        {
          path: 'pages/contact-us',
          loadChildren: () => import('./pages/contact-us/contact-us.module').then(m => ContactUsModule),
        },
        {
          path: 'pages/about-us',
          loadChildren: () => import('./pages/about-us/about-us.module').then(m => AboutUsModule),
        },
        navbarRoute,
        ...errorRoute,
      ],
      { enableTracing: DEBUG_INFO_ENABLED }
    ),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
