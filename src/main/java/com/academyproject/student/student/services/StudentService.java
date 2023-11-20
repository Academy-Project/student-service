package com.academyproject.student.student.services;

import com.academyproject.student.common.exceptions.NotFoundException;
import com.academyproject.student.common.services.ValidationService;
import com.academyproject.student.student.entities.Student;
import com.academyproject.student.student.repositories.StudentRepository;
import com.academyproject.student.student.request.CreateStudentRequest;
import com.academyproject.student.student.request.UpdateStudentRequest;
import com.academyproject.student.student.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepo;
    private final ValidationService validationService;

    public List<StudentResponse> getListStudents() {
        var students = studentRepo.findAll();

        return students.stream().map(this::handleConvertToStudentResponse).toList();

    }

    public StudentResponse save(CreateStudentRequest studentRequest) {

        // validation
        validationService.validate(studentRequest);

        /* ensure nim is unique */
        if (studentRepo.existsById(studentRequest.getId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Id already exists");
        }

        Student rawStudent = Student.builder()
                .id(studentRequest.getId())
                .nim(studentRequest.getNim())
                .name(studentRequest.getName())
                .phone(studentRequest.getPhone())
                .address(studentRequest.getAddress())
                .phone(studentRequest.getPhone())
                .build();

        var student = studentRepo.save(rawStudent);

        return handleConvertToStudentResponse(student);
    }

    public StudentResponse findById(String id) {
        Student student = findStudentById(id);

        return handleConvertToStudentResponse(student);

    }

    public StudentResponse update(String id, UpdateStudentRequest studentRequest) {
        validationService.validate(studentRequest);

        var student = findStudentById(id);

        student.setNim(studentRequest.getNim());
        student.setName(studentRequest.getName());
        student.setAddress(studentRequest.getAddress());
        student.setPhone(studentRequest.getPhone());
        student = studentRepo.save(student);

        return handleConvertToStudentResponse(student);
    }

    public void deleteById(String id) {
        studentRepo.deleteById(id);
    }

    public Student findStudentById(String id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student"));
    }

    private StudentResponse handleConvertToStudentResponse(Student student) {

        return StudentResponse.builder()
                .id(student.getId())
                .nim(student.getNim())
                .name(student.getName())
                .phone(student.getPhone())
                .address(student.getAddress())
                .totalSks(student.calculateTotalSks())
                .phone(student.getPhone())
                .build();
    }
}
