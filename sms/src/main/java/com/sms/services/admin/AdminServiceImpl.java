package com.sms.services.admin;


import com.sms.dto.FeeDto;
import com.sms.dto.SingleStudentDto;
import com.sms.dto.StudentDto;
import com.sms.dto.StudentLeaveDto;
import com.sms.entities.Fee;
import com.sms.entities.StudentLeave;
import com.sms.entities.User;
import com.sms.enums.StudentLeaveStatus;
import com.sms.enums.UserRole;
import com.sms.repositories.FeeRepository;
import com.sms.repositories.StudentLeaveRepository;
import com.sms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class AdminServiceImpl implements AdminService {

    private final UserRepository userrepository;
    private final FeeRepository feeRepository;
    private final StudentLeaveRepository studentLeaveRepository;

    public AdminServiceImpl(UserRepository userrepository, FeeRepository feeRepository, StudentLeaveRepository studentLeaveRepository) {
        this.userrepository = userrepository;
        this.feeRepository = feeRepository;
        this.studentLeaveRepository = studentLeaveRepository;
    }


    @PostConstruct
    public void validateDatabaseConnection() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/school_database", "root", "Muntaha@8")) {
            System.out.println("Database connected successfully: " + connection.getMetaData().getDatabaseProductName());
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }




    @PostConstruct
    public void createAdminAccount() {
        System.out.println("createAdminAccount method called.");
        Optional<User> adminAccount = userrepository.findByRole(UserRole.admin);
        if (adminAccount.isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setName("Admin");
            admin.setRole(UserRole.admin);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            admin.setPassword(passwordEncoder.encode("admin123"));
            userrepository.save(admin);
            System.out.println("Admin account created.");
        } else {
            System.out.println("Admin account already exists.");
        }
    }

    @Override
    public StudentDto postStudent(StudentDto studentDto) {
        // Logic for saving a student
        System.out.println("StudentDto received: " + studentDto);
        // For now, simply return the studentDto for demonstration purposes
        return studentDto;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return userrepository.findAllByRole(UserRole.student).stream().map(User::getStudentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(Long studentId){
        userrepository.deleteById(studentId);
    }

    @Override
    public SingleStudentDto getStudentById(Long studentId) {
        Optional<User> optionalUser=userrepository.findById(studentId);
        SingleStudentDto singleStudentDto=new SingleStudentDto();
        optionalUser.ifPresent(user-> singleStudentDto.setStudentDto(user.getStudentDto()));
        return singleStudentDto;


    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
        Optional<User> optionalUser=userrepository.findById(studentId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            user.setName(studentDto.getName());
            user.setGender(studentDto.getGender());
            user.setAddress(studentDto.getAddress());
            user.setDob(studentDto.getDob());
            user.setStudentClass(studentDto.getStudentClass());
            user.setFatherName(studentDto.getFatherName());
            user.setMotherName(studentDto.getMotherName());
            user.setEmail(studentDto.getEmail());
            User updatedStudent=userrepository.save(user);
            StudentDto updatedStudentDto=new StudentDto();
            updatedStudentDto.setId(updatedStudent.getId());
            return updatedStudentDto;
        }
        return null;
    }

    @Override
    public FeeDto payFee(Long studentId, FeeDto feeDto) {
        Optional<User> optionalStudent=userrepository.findById(studentId);
        if(optionalStudent.isPresent())
        {
            Fee fee=new Fee();
            fee.setAmount(feeDto.getAmount());
            fee.setMonth(fee.getMonth());
            fee.setDescription(fee.getDescription());
            fee.setCreatedDate(new Date());
            fee.setGivenBy(fee.getGivenBy());
            fee.setUser(optionalStudent.get());
            Fee paidFee=feeRepository.save(fee);
            FeeDto paidfeeDto=new FeeDto();
            paidfeeDto.setId(paidFee.getId());
            return paidfeeDto;

        }

        return null;
    }

    @Override
    public List<StudentLeaveDto> getAllAppliedLeaves() {
        return studentLeaveRepository.findAll().stream().map(StudentLeave::getStudentLeaveDto).collect(Collectors.toList());
    }

    @Override
    public StudentLeaveDto changeLeaveStatus(Long leaveId, String status) {
        Optional<StudentLeave> optionalStudentLeave=studentLeaveRepository.findById(leaveId);
        if(optionalStudentLeave.isPresent())
        {
            StudentLeave studentLeave= optionalStudentLeave.get();
            if(Objects.equals(status,"Approve"))
            {
                StudentLeave.setStudentLeaveStatus(StudentLeaveStatus.Approved);
            }
            else{
                StudentLeave.setStudentLeaveStatus(StudentLeaveStatus.Disapproved);
            }
            StudentLeave updatedStudentLeave=studentLeaveRepository.save(studentLeave);
            StudentLeaveDto updatedStudentLeaveDto=new StudentLeaveDto();
            updatedStudentLeaveDto.setId(updatedStudentLeaveDto.getId());
            return updatedStudentLeaveDto;
        }
        return null;
    }

}

