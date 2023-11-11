package com.academyproject.student.services;

import com.academyproject.student.entities.Student;
import com.academyproject.student.exceptions.NotFoundException;
import com.academyproject.student.repositories.StudentRepository;
import com.academyproject.student.request.CreateStudentRequest;
import com.academyproject.student.request.UpdateStudentRequest;
import com.academyproject.student.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepo;

    public List<StudentResponse> getListStudents() {
        var students = studentRepo.findAll();

        return students.stream().map(this::handleConvertToStudentResponse).toList();

    }

    public StudentResponse save(CreateStudentRequest studentRequest) {
        Student rawStudent = Student.builder()
                .nim(studentRequest.getNim())
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
                .phone(student.getPhone())
                .address(student.getAddress())
                .phone(student.getPhone())
                .build();
    }
}
