import { Component } from '@angular/core';
import { StudentService } from '../../student-service/student.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
@Component({
  selector: 'app-dasboard',
  imports: [MatFormFieldModule,FormsModule,ReactiveFormsModule,
    MatButtonModule,MatDatepickerModule ,MatNativeDateModule,MatCardModule, CommonModule
  ],
  templateUrl: './dasboard.component.html',
  styleUrl: './dasboard.component.css'
})
export class DasboardComponent {

  student:any;
  constructor(private service:StudentService){

  }

  ngOnit(){
    this.getStudentById();
  }

  getStudentById(){
    this.service.getStudentById().subscribe((res)=>
    {
       console.log(res);
       this.student=res.studentDto;
    })

  }

}
