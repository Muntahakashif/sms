package com.sms.dto;

import com.sms.enums.StudentLeaveStatus;

import java.util.Date;

public class StudentLeaveDto {
    private Long id;
    private String subject;
    private String body;
    private Date date;
    private StudentLeaveStatus studentLeaveStatus;
    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StudentLeaveStatus getStudentLeaveStatus() {
        return studentLeaveStatus;
    }

    public void setStudentLeaveStatus(StudentLeaveStatus studentLeaveStatus) {
        this.studentLeaveStatus = studentLeaveStatus;
    }
}
