package com.application.sms.service;

import com.application.sms.model.Student;
import com.application.sms.dto.StudentListResponse;
import com.application.sms.dto.StudentCreateRequest;
import com.application.sms.dto.StudentUpdateRequest;
import com.application.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(StudentCreateRequest request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBirthDate(request.getBirthDate());
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, StudentUpdateRequest request) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));

        if (request.getFirstName() != null) {
            student.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            student.setLastName(request.getLastName());
        }
        if (request.getBirthDate() != null) {
            student.setBirthDate(request.getBirthDate());
        }

        return studentRepository.save(student);
    }

    public List<StudentListResponse> getAllStudents() {
        return studentRepository.findAll().stream()
            .map(student -> new StudentListResponse(
                student.getStudentId(),
                combineNames(student.getFirstName(), student.getLastName()),
                calculateAge(student.getBirthDate())
            ))
            .collect(Collectors.toList());
    }

    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(student);
    }

    private String combineNames(String firstName, String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            return firstName;
        }
        return firstName + " " + lastName;
    }

    private int calculateAge(Date birthDate) {
        if (birthDate == null) {
            return 0;
        }
        LocalDate birthLocalDate = birthDate.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        return Period.between(birthLocalDate, LocalDate.now()).getYears();
    }
}