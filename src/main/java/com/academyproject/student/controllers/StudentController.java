package com.academyproject.student.controllers;

import com.academyproject.student.entities.Student;
import com.academyproject.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/")
    public Student create(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping("/{nim}")
    public Optional<Student> show(@PathVariable String nim) {
        return studentService.findByNim(nim);
    }

    @DeleteMapping("/{nim}")
    public void delete(@PathVariable String nim) {
        studentService.deleteByNim(nim);
    }
}
