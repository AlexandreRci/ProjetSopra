import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuPartieComponent } from './menu-partie.component';

describe('MenuPartieComponent', () => {
  let component: MenuPartieComponent;
  let fixture: ComponentFixture<MenuPartieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MenuPartieComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuPartieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
