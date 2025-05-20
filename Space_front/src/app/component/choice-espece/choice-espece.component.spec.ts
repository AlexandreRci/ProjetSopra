import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoiceEspeceComponent } from './choice-espece.component';

describe('ChoiceEspeceComponent', () => {
  let component: ChoiceEspeceComponent;
  let fixture: ComponentFixture<ChoiceEspeceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ChoiceEspeceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChoiceEspeceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
