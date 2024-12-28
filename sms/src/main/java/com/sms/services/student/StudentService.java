package com.sms.services.student;


import com.sms.dto.SingleStudentDto;
import com.sms.dto.StudentLeaveDto;

import java.util.List;

public interface StudentService {
    SingleStudentDto getStudentById(Long studentId);

    StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto);

    List<StudentLeaveDto> getAllAppliedLeaveByStudentId(Long studentId);
}