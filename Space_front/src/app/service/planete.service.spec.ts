import { TestBed } from '@angular/core/testing';

import { PlaneteService } from './planete.service';

describe('PlaneteService', () => {
  let service: PlaneteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlaneteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
