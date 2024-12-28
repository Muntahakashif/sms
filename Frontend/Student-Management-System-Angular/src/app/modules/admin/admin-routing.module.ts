import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './admin-components/dashboard/dashboard.component';
import { adminGuard } from '../../auth/guards/admin-guard/admin.guard';
import { PostStudentComponent } from './admin-components/post-student/post-student.component';
import { AllStudentsComponent } from './admin-components/all-students/all-students.component';
import { PayFeeComponent } from './admin-components/pay-fee/pay-fee.component';
import { UpdateStudentsComponent } from './admin-components/update-students/update-students.component';


const routes: Routes = [
  {
    path:'dashboard',
    component:DashboardComponent,
    canActivate:[adminGuard]
  },
  {
    path:'student',
    component:PostStudentComponent,
    canActivate:[adminGuard]
  },

  {
    path:'students',
    component:AllStudentsComponent,
    
  },
   {
    path:'student/:studentId',
    component:UpdateStudentsComponent,
    
  },
  
 
  {
    path:'fee/:studentId',
    component:PayFeeComponent,
    
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
