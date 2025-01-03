import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateStudentsComponent } from './update-students.component';

describe('UpdateStudentsComponent', () => {
  let component: UpdateStudentsComponent;
  let fixture: ComponentFixture<UpdateStudentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateStudentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateStudentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
