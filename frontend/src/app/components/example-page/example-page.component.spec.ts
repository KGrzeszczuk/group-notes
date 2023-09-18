import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamplePageComponent } from './example-page.component';

describe('WelcomeComponent', () => {
  let component: ExamplePageComponent;
  let fixture: ComponentFixture<ExamplePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExamplePageComponent]
    });
    fixture = TestBed.createComponent(ExamplePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
