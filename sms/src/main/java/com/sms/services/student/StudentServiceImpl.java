package com.sms.services.student;

import com.sms.dto.SingleStudentDto;
import com.sms.dto.StudentLeaveDto;
import com.sms.entities.StudentLeave;
import com.sms.entities.User;
import com.sms.enums.StudentLeaveStatus;
import com.sms.repositories.StudentLeaveRepository;
import com.sms.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private final UserRepository userrepository;
    private final StudentLeaveRepository studentLeaveRepository;

    public StudentServiceImpl(UserRepository userRepository, StudentLeaveRepository studentLeaveRepository) {
        this.userrepository = userRepository;
        this.studentLeaveRepository = studentLeaveRepository;
    }

    @Override
    public SingleStudentDto getStudentById(Long studentId) {
        Optional<User> optionalUser=userrepository.findById(studentId);
        SingleStudentDto singleStudentDto=new SingleStudentDto();
        optionalUser.ifPresent(user-> singleStudentDto.setStudentDto(user.getStudentDto()));
        return singleStudentDto;
    }

    @Override
    public StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto) {
        Optional<User> optionalUser=userrepository.findById((studentLeaveDto.getUserid()));
        if(optionalUser.isPresent())
        {
            StudentLeave studentLeave=new StudentLeave();
            StudentLeave.setSubject(studentLeaveDto.getSubject());
            StudentLeave.setBody(studentLeaveDto.getBody());
            StudentLeave.setDate(new Date());
            StudentLeave.setStudentLeaveStatus(StudentLeaveStatus.Pending);
            studentLeave.setUser(optionalUser.get());
            StudentLeave submittedStudentLeave=studentLeaveRepository.save(studentLeave);
            StudentLeaveDto studentLeaveDto1=new StudentLeaveDto();
            studentLeaveDto1.setId(submittedStudentLeave.getId());
            return studentLeaveDto1;
        }
        return null;
    }

    @Override
    public List<StudentLeaveDto> getAllAppliedLeaveByStudentId(Long studentId) {
        return studentLeaveRepository.findAllByUserId(studentId).stream().map(StudentLeave::getStudentLeaveDto).collect(Collectors.toList());
    }
}

