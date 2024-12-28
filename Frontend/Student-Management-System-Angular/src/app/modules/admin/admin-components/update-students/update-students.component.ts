import { Component } from '@angular/core';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AdminService } from '../../admin-service/admin.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { NgIf ,NgFor} from '@angular/common';

@Component({
  selector: 'app-update-students',
  imports: [MatProgressSpinnerModule,MatFormFieldModule,NgFor,
    MatSelectModule,MatButtonModule,MatDatepickerModule,MatNativeDateModule,
    ReactiveFormsModule,FormsModule,NgIf
  ],
  templateUrl: './update-students.component.html',
  styleUrl: './update-students.component.css'
})
export class UpdateStudentsComponent {
    CLASS:string[]=[
      "Play","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"
  
    ];
  
    GENDER: string[]=[
      "Male","Female","Other"
    ]
    isSpinning: boolean;
    validateForm:FormGroup;
  constructor(private service:AdminService,private activatedRoute:ActivatedRoute,
     private fb:FormBuilder,
          private snackbar:MatSnackBar
  ){}

  
  studentId:number

  ngOnit(){
  
   this.validateForm=this.fb.group({
          email:['',Validators.required],
          name:['',Validators.required],
          fatherName:['',Validators.required],
          motherName:['',Validators.required],
          studentClass:['',Validators.required],
          dob:['',Validators.required],
          address:['',Validators.required],
          gender:['',Validators.required],
  })
  this.getStudentById();
}

    getStudentById(){
      this.service.getStudentById(this.studentId).subscribe((res)=>{
        const student=res.studentDto;
        this.validateForm.patchValue(student);
        console.log(res);
      })

    }
  
  }

