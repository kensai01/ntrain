import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVideos } from '../videos.model';

export type EntityResponseType = HttpResponse<IVideos>;
export type EntityArrayResponseType = HttpResponse<IVideos[]>;

@Injectable({ providedIn: 'root' })
export class VideosService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/pages/videos');
  private CHANNEL_ID = 'UC_q_omNPIXWXjzD9WRb8V9A';
  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
    this.resourceUrl = this.resourceUrl.concat(`/${this.CHANNEL_ID}`);
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    console.log(this.resourceUrl);
    return this.http.get<IVideos[]>(this.resourceUrl, { observe: 'response' });
  }

  getVideosIdentifier(video: Pick<IVideos, 'id'>): number {
    return video.id;
  }

  compareVideos(o1: Pick<IVideos, 'id'> | null, o2: Pick<IVideos, 'id'> | null): boolean {
    return o1 && o2 ? this.getVideosIdentifier(o1) === this.getVideosIdentifier(o2) : o1 === o2;
  }

  addVideoToCollectionIfMissing<Type extends Pick<IVideos, 'id'>>(
    videoCollection: Type[],
    ...videosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const videos: Type[] = videosToCheck.filter(isPresent);
    if (videos.length > 0) {
      const videoCollectionIdentifiers = videoCollection.map(videoItem => this.getVideosIdentifier(videoItem)!);
      const videosToAdd = videos.filter(videoItem => {
        const videoIdentifier = this.getVideosIdentifier(videoItem);
        if (videoCollectionIdentifiers.includes(videoIdentifier)) {
          return false;
        }
        videoCollectionIdentifiers.push(videoIdentifier);
        return true;
      });
      return [...videosToAdd, ...videoCollection];
    }
    return videoCollection;
  }
}
