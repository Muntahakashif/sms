package com.sms.services.admin;


import com.sms.dto.FeeDto;
import com.sms.dto.SingleStudentDto;
import com.sms.dto.StudentDto;
import com.sms.dto.StudentLeaveDto;

import java.util.List;

public interface AdminService {
    void createAdminAccount();

    // Remove the static modifier here
    StudentDto postStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    void deleteStudent(Long studentId);

    SingleStudentDto getStudentById(Long studentId);

    StudentDto updateStudent(Long studentId, StudentDto studentDto);

    FeeDto payFee(Long studentId, FeeDto feeDto);

    List<StudentLeaveDto> getAllAppliedLeaves();

    StudentLeaveDto changeLeaveStatus(Long leaveId, String status);
}

