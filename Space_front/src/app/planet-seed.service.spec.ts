import { TestBed } from '@angular/core/testing';

import { PlanetSeedService } from './planet-seed.service';

describe('PlanetSeedService', () => {
  let service: PlanetSeedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlanetSeedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
