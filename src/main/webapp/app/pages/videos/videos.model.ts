export interface IVideos {
  id: number;
  playlistTitle?: string;
  description?: string;
  videos?: Array<Video>;
}

export type NewVideos = Omit<IVideos, 'id'> & { id: null };

export interface Video {
  contentDetails: ContentDetails;
  etag: string;
  id: string;
  kind: string;
  snippet: Snippet;
}

export interface ContentDetails {
  caption: string;
  contentRating: ContentRating;
  definition: string;
  dimension: string;
  duration: string;
  licensedContent: boolean;
  projection: string;
}

export interface ContentRating {}

export interface Snippet {
  categoryId: string;
  channelId: string;
  channelTitle: string;
  description: string;
  liveBroadcastContent: string;
  localized: Localized;
  publishedAt: PublishedAt;
  thumbnails: Thumbnails;
  title: string;
}

export interface Localized {
  description: string;
  title: string;
}

export interface PublishedAt {
  value: number;
  dateOnly: boolean;
  timeZoneShift: number;
}

export interface Thumbnails {
  default: Default;
  high: High;
  maxres: Maxres;
  medium: Medium;
  standard: Standard;
}

export interface Default {
  height: number;
  url: string;
  width: number;
}

export interface High {
  height: number;
  url: string;
  width: number;
}

export interface Maxres {
  height: number;
  url: string;
  width: number;
}

export interface Medium {
  height: number;
  url: string;
  width: number;
}

export interface Standard {
  height: number;
  url: string;
  width: number;
}
