package com.academyproject.student.student.services;

import com.academyproject.student.common.exceptions.NotFoundException;
import com.academyproject.student.student.entities.Student;
import com.academyproject.student.student.repositories.StudentRepository;
import com.academyproject.student.student.request.CreateStudentRequest;
import com.academyproject.student.student.request.UpdateStudentRequest;
import com.academyproject.student.student.response.StudentResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepo;
    private final Validator validator;

    public List<StudentResponse> getListStudents() {
        var students = studentRepo.findAll();

        return students.stream().map(this::handleConvertToStudentResponse).toList();

    }

    public StudentResponse save(CreateStudentRequest studentRequest) {

        // validation
        Set<ConstraintViolation<CreateStudentRequest>> constraintViolations = validator.validate(studentRequest);
        if (0 != constraintViolations.size()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        /* ensure nim is unique */
        if (studentRepo.existsById(studentRequest.getNim())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "NIM already exists");
        }

        Student rawStudent = Student.builder()
                .nim(studentRequest.getNim())
                .name(studentRequest.getName())
                .phone(studentRequest.getPhone())
                .address(studentRequest.getAddress())
                .phone(studentRequest.getPhone())
                .build();

        var student = studentRepo.save(rawStudent);

        return handleConvertToStudentResponse(student);
    }

    public StudentResponse findByNim(String nim) {
        Student student = handleFindByNim(nim);

        return handleConvertToStudentResponse(student);

    }

    public StudentResponse update(String nim, UpdateStudentRequest studentRequest) {
        var student = handleFindByNim(nim);

        student.setName(studentRequest.getName());
        student.setAddress(studentRequest.getAddress());
        student.setPhone(studentRequest.getPhone());
        student = studentRepo.save(student);

        return handleConvertToStudentResponse(student);
    }

    public void deleteByNim(String nim) {
        studentRepo.deleteById(nim);
    }

    private Student handleFindByNim(String nim) {
        return studentRepo.findById(nim)
                .orElseThrow(() -> new NotFoundException("Student"));
    }

    private StudentResponse handleConvertToStudentResponse(Student student) {
        return StudentResponse.builder()
                .nim(student.getNim())
                .name(student.getName())
                .phone(student.getPhone())
                .address(student.getAddress())
                .phone(student.getPhone())
                .build();
    }
}
