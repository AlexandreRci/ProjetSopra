import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopButtonsConnectedComponent } from './top-buttons-connected.component';

describe('TopButtonsComponent', () => {
  let component: TopButtonsConnectedComponent;
  let fixture: ComponentFixture<TopButtonsConnectedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TopButtonsConnectedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TopButtonsConnectedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
