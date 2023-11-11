package com.academyproject.student.controllers;

import com.academyproject.student.entities.Student;
import com.academyproject.student.request.CreateStudentRequest;
import com.academyproject.student.request.UpdateStudentRequest;
import com.academyproject.student.response.StructureResponse;
import com.academyproject.student.response.StudentResponse;
import com.academyproject.student.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> getList() {
        return studentService.getListStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StructureResponse<StudentResponse>>
    create(@RequestBody CreateStudentRequest studentRequest) {
        StudentResponse studentResponse = studentService.save(studentRequest);

        StructureResponse<StudentResponse> response = new StructureResponse<>(
                "Successfully create new student", studentResponse
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{nim}")
    public ResponseEntity<StructureResponse<StudentResponse>>
    update(@PathVariable String nim, @RequestBody UpdateStudentRequest studentRequest) {
        StudentResponse studentResponse = studentService.update(nim, studentRequest);

        StructureResponse<StudentResponse> response = new StructureResponse<>(
                "Successfully update student", studentResponse
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{nim}")
    public void show(@PathVariable String nim) {
        var studentResponse = studentService.findByNim(nim);
    }

    @DeleteMapping("/{nim}")
    public void delete(@PathVariable String nim) {
        studentService.deleteByNim(nim);
    }
}
