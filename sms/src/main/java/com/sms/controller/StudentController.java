package com.sms.controller;
import com.sms.dto.SingleStudentDto;
import com.sms.dto.StudentLeaveDto;
import com.sms.services.student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")




    public class StudentController {

        private final StudentService studentService;

        public StudentController(StudentService studentService) {
            this.studentService = studentService;
        }

        @GetMapping("/{studentId}")
        public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId){
            SingleStudentDto singleStudentDto=studentService.getStudentById(studentId);
            if(singleStudentDto==null)
            {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(singleStudentDto);
        }

        @PostMapping("/leave")
        public ResponseEntity<?> applyLeave(@RequestBody StudentLeaveDto studentLeaveDto){
            StudentLeaveDto submittedStudentLeaveDto= studentService.applyLeave(studentLeaveDto);
            if(submittedStudentLeaveDto==null)
            {
                return new ResponseEntity<>("Something went Wrong", HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(submittedStudentLeaveDto);

        }
        @GetMapping("/leave/{studentId}")
        public ResponseEntity<List<StudentLeaveDto>> getAllAppliedLeaveByStudentId(@PathVariable Long studentId){
            List<StudentLeaveDto> studentLeaveDtos=studentService.getAllAppliedLeaveByStudentId(studentId);
            if(studentLeaveDtos==null)
            {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(studentLeaveDtos);
        }

    }

