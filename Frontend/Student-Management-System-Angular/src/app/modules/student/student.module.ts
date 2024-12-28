import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { MatFormFieldModule } from '@angular/material/form-field';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { NgIf ,NgFor} from '@angular/common';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    StudentRoutingModule,MatFormFieldModule,NgFor,MatButtonModule,MatDatepickerModule,MatNativeDateModule,
      ReactiveFormsModule,FormsModule,NgIf
  ]
})
export class StudentModule { }
