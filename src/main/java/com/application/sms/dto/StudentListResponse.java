package com.application.sms.dto;

public class StudentListResponse {
    private Long studentId;
    private String fullName;
    private int age;

    public StudentListResponse(Long studentId, String fullName, int age) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.age = age;
    }

    // Getters
    public Long getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }
}