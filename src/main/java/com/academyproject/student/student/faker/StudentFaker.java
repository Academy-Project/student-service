package com.academyproject.student.student.faker;

import com.academyproject.student.student.entities.Student;
import com.academyproject.student.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StudentFaker {
    @Autowired
    private StudentRepository studentRepository;

    public void generateDummyData() {
        studentRepository.deleteAll();

        Student student = Student.builder()
                .nim("672021077")
                .name("Agung Prasetyo Nugroho")
                .address("Kec. Pabelan Kab. Semarang")
                .phone("0234234324323")
                .build();
        studentRepository.save(student);
    }
}
