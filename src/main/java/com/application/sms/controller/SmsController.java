package com.application.sms.controller;

import com.application.sms.dto.ApiResponse;
import com.application.sms.dto.StudentCreateRequest;
import com.application.sms.dto.StudentListResponse;
import com.application.sms.dto.StudentUpdateRequest;
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

    @PutMapping("/{studentId}")
    public ApiResponse<Student> updateStudent(
        @PathVariable Long studentId,
        @RequestBody StudentUpdateRequest request
    ) {
        Student updatedStudent = studentService.updateStudent(studentId, request);
        return new ApiResponse<>(
            HttpStatus.OK.value(),
            "Student data updated successfully",
            updatedStudent
        );
    }

    @DeleteMapping("/{studentId}")
    public ApiResponse<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return new ApiResponse<>(
            HttpStatus.OK.value(),
            "Student deleted successfully",
            null
        );
    }
}
