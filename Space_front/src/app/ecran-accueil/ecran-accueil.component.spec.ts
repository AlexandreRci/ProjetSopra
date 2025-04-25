import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EcranAccueilComponent } from './ecran-accueil.component';

describe('EcranAccueilComponent', () => {
  let component: EcranAccueilComponent;
  let fixture: ComponentFixture<EcranAccueilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EcranAccueilComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EcranAccueilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
