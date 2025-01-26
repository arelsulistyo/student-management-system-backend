package com.application.sms.controller;

import com.application.sms.dto.ApiResponse;
import com.application.sms.dto.StudentCreateRequest;
import com.application.sms.dto.StudentListResponse;
import com.application.sms.model.Student;
import com.application.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class SmsController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ApiResponse<Student> createStudent(@RequestBody StudentCreateRequest request) {
        Student createdStudent = studentService.createStudent(request);
        return new ApiResponse<>(
            HttpStatus.CREATED.value(),
            "Student data created successfully",
            createdStudent
        );
    }

    @GetMapping
    public ApiResponse<List<StudentListResponse>> getAllStudents() {
        List<StudentListResponse> students = studentService.getAllStudents();
        return new ApiResponse<>(
            HttpStatus.OK.value(),
            "Students retrieved successfully",
            students
        );
    }
}
