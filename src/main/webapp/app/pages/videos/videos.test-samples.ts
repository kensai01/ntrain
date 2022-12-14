import { IVideos, NewVideos } from './videos.model';

export const sampleWithRequiredData: IVideos = {
  id: 88410,
  playlistTitle: 'bluetooth Computers AI',
  description: 'Account quantify',
};

export const sampleWithPartialData: IVideos = {
  id: 12721,
  playlistTitle: 'Brook transform Soap',
  description: 'Berkshire India Cotton',
};

export const sampleWithFullData: IVideos = {
  id: 10244,
  playlistTitle: 'Table uniform Wyoming',
  description: 'Pennsylvania',
  videos: [],
};

export const sampleWithNewData: NewVideos = {
  playlistTitle: 'Automotive Buckinghamshire',
  description: 'Shoes',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
