package com.sms.controller;

import com.sms.dto.FeeDto;
import com.sms.dto.SingleStudentDto;
import com.sms.dto.StudentDto;
import com.sms.dto.StudentLeaveDto;
import com.sms.services.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

//    private final AdminService adminService;
//
//    public AdminController(AdminService adminService) {
//        this.adminService = adminService;
//    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent() {
        System.out.println("Add st: ");
//        StudentDto createdStudentDto = adminService.postStudent(studentDto); // Use adminService
//
//        if (createdStudentDto == null) {
//            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body("hi");
    }
//
//    @GetMapping("/students")
//    public ResponseEntity<List<StudentDto>> getAllStudents() {
//        List<StudentDto> allStudents= adminService.getAllStudents();
//        return ResponseEntity.ok(allStudents);
//    }
//
//    @DeleteMapping("/student.{studentId}")
//    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
//
//        adminService.deleteStudent(studentId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/student/{studentId}")
//    public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId){
//        SingleStudentDto singleStudentDto=adminService.getStudentById(studentId);
//        if(singleStudentDto==null)
//        {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(singleStudentDto);
//    }
//    @PutMapping("/student/{studentId}")
//    public ResponseEntity<?> updateStudent(@PathVariable Long studentId,@RequestBody StudentDto studentDto) {
//        StudentDto createdStudentDto = adminService.updateStudent(studentId,studentDto); // Use adminService
//
//        if (createdStudentDto == null) {
//            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
//    }
//
//    @PostMapping("/fee/{studentId}")
//    public ResponseEntity<?> payFee(@PathVariable Long studentId,@RequestBody FeeDto feeDto) {
//        FeeDto paidFeeDto = adminService.payFee(studentId,feeDto); // Use adminService
//
//        if (paidFeeDto == null) {
//            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(paidFeeDto);
//    }
//    @GetMapping("/leaves")
//    public ResponseEntity<List<StudentLeaveDto>> getAllAppliedLeaves(){
//        List<StudentLeaveDto> studentLeaveDtos=adminService.getAllAppliedLeaves();
//        if(studentLeaveDtos==null)
//        {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(studentLeaveDtos);
//    }
//
//    @GetMapping("/leave/{leaveId}/{status}")
//    public ResponseEntity<?> changeLeaveStatus(@PathVariable Long leaveId,String status){
//        StudentLeaveDto studentLeaveDto=adminService.changeLeaveStatus(leaveId,status);
//        if(studentLeaveDto==null)
//        {
//            return new ResponseEntity<>("Something Went Wrong",HttpStatus.BAD_REQUEST);
//        }
//        return ResponseEntity.ok(studentLeaveDto);
//    }

}
