import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StudentService } from '../../student-service/student.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { NgIf} from '@angular/common';

@Component({
  selector: 'app-apply-leave',
  imports: [ MatFormFieldModule, FormsModule,ReactiveFormsModule,
    MatButtonModule,MatNativeDateModule,NgIf,MatCardModule
  ],
  templateUrl: './apply-leave.component.html',
  styleUrl: './apply-leave.component.css'
})
export class ApplyLeaveComponent {
  validateForm:FormGroup

  constructor(
    private fb:FormBuilder,
    private studentService:StudentService,
    private snackBar:MatSnackBar,
    private router:Router

  ){}

  ngOnit():void{
     this.validateForm=this.fb.group({
      subject:['',[Validators.required]],
      body:['',[Validators.required]],
     });
  }

  applyLeave(){
    console.log(this.validateForm.value);
    this.studentService.applyLeave(this.validateForm.value).subscribe((res)=>{
      console.log(res);
      if(res.id!=null)
      {
        this.snackBar.open('Leave Applied Successfully','Success',{duration:5000});
        this.router.navigate(['student/dashboard']);
      }
      else{
        this.snackBar.open('Failed to Apply Leave','Error',{duration:5000});
      }
    },
  );
  }


}
