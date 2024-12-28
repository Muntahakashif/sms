package com.sms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sms.dto.StudentLeaveDto;
import com.sms.enums.StudentLeaveStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class StudentLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String body;
    private Date date;
    private StudentLeaveStatus studentLeaveStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        subject = subject;
    }

    public String getBody() {
        return body;
    }

    public static void setBody(String body) {
        body = body;
    }

    public Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        date = date;
    }

    public StudentLeaveStatus getStudentLeaveStatus() {
        return studentLeaveStatus;
    }

    public static void setStudentLeaveStatus(StudentLeaveStatus studentLeaveStatus) {
        studentLeaveStatus = studentLeaveStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        user = user;
    }

    public StudentLeaveDto getStudentLeaveDto() {
        StudentLeaveDto studentLeaveDto = new StudentLeaveDto();
        studentLeaveDto.setId(id);
        studentLeaveDto.setBody(body);
        studentLeaveDto.setSubject(subject);
        studentLeaveDto.setDate(date);
        studentLeaveDto.setStudentLeaveStatus(studentLeaveStatus);
        studentLeaveDto.setUserid(user.getId());
        return studentLeaveDto;

    }
}
