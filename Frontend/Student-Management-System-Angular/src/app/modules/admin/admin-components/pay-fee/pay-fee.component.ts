import { Component } from '@angular/core';
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
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-pay-fee',
  imports: [MatFormFieldModule,FormsModule,ReactiveFormsModule,MatSelectModule,
    MatButtonModule,MatDatepickerModule,MatNativeDateModule,NgFor,NgIf,MatProgressSpinnerModule
  ],
  templateUrl: './pay-fee.component.html',
  styleUrl: './pay-fee.component.css'
})
export class PayFeeComponent {
  studentId:number
  validateForm:FormGroup;
  isSpinnung:boolean=false;

  MONTH:string[]=[
    "January","February","March","April","May","June",
    "July","August","September","October","November","December"
  ];

  constructor(private service:AdminService,
    private activatedRoute:ActivatedRoute,
    private fb:FormBuilder,
    private snackbar:MatSnackBar
  ){

  }

  ngOnit(){
    this.validateForm=this.fb.group({
      amount:['',Validators.required],
      month:['',Validators.required],
      givenBy:['',Validators.required],
      description:['',Validators.required],
    })

  }

  payFee(){
    console.log(this.validateForm.value);
    this.service.payFee(this.studentId,this.validateForm.value).subscribe((res)=>{
      console.log(res);
      if(res.id!=null){
          this.snackbar.open("Fee Paid Successfully","Close",{duration:5000});
      }else{
        this.snackbar.open("Something Went Wrong","Close",{duration:5000});
      }
      
    })

  }

}
