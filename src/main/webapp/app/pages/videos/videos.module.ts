import { NgModule } from '@angular/core';
import { YouTubePlayerModule } from '@angular/youtube-player';
import { SharedModule } from 'app/shared/shared.module';
import { VideosComponent } from './list/videos.component';
import { VideosRoutingModule } from './route/videos-routing.module';

@NgModule({
  imports: [SharedModule, VideosRoutingModule, YouTubePlayerModule],
  declarations: [VideosComponent],
})
export class VideosModule {}
