package com.academyproject.student.services;

import com.academyproject.student.entities.Student;
import com.academyproject.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepo;

    public Student save(Student student) {
        return studentRepo.save(student);
    }

    public Optional<Student> findByNim(String nim) {
        return studentRepo.findById(nim);
    }

    public void deleteByNim(String nim) {
        studentRepo.deleteById(nim);
    }
}
