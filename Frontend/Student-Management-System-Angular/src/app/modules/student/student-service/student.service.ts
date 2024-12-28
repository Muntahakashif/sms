import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/service/storage-service/storage.service';

const BASIC_URL=["http://localhost:8080/"];
@Injectable({
  providedIn: 'root'
})
export class StudentService {


  constructor(private http:HttpClient,private storageService: StorageService) { }
    getStudentById(): Observable<any>{
      return this.http.get<[]>(BASIC_URL + `api/student/${this.storageService.getUserId()}`,{
        headers:this.createAuthorizationHeader()
      })
    }
    applyLeave(studentLeaveDto): Observable<any>{
      studentLeaveDto.userId=this.storageService.getUserId();
      return this.http.post<[]>(BASIC_URL + `api/student/leave`,studentLeaveDto,{
        headers:this.createAuthorizationHeader()
      })
    }
    getAllAppliedLeaveByStudentId(): Observable<any>{
      return this.http.get<[]>(BASIC_URL + `api/student/leave/${this.storageService.getUserId()}`,{
        headers:this.createAuthorizationHeader()
      })
    }

      createAuthorizationHeader(): HttpHeaders {
        let authHeaders: HttpHeaders = new HttpHeaders();
        const token = this.storageService.getToken(); // Get token from StorageService
        console.log("JWT Token:", token); // Debugging (remove in production)
    
        if (token) {
          // Extract the Bearer token and set it in the header
          console.log("Token is present, entering the if block."); // Debugging
          return authHeaders.set("Authorization", "Bearer " + token);
        } else {
          console.log("No token found, returning empty headers."); // Debugging
          return authHeaders; // Return empty headers if no token
        }
      }
}
