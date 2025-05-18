import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanetSeedComponent } from './planet-seed.component';

describe('PlanetSeedComponent', () => {
  let component: PlanetSeedComponent;
  let fixture: ComponentFixture<PlanetSeedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PlanetSeedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlanetSeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
