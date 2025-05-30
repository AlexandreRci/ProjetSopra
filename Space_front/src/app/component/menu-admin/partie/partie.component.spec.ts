import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartieComponent } from './partie.component';

describe('PartieComponent', () => {
  let component: PartieComponent;
  let fixture: ComponentFixture<PartieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PartieComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
