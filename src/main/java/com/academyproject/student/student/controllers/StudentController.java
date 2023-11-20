package com.academyproject.student.student.controllers;

import com.academyproject.student.common.resource.ResponseHandler;
import com.academyproject.student.student.request.CreateStudentRequest;
import com.academyproject.student.student.request.UpdateStudentRequest;
import com.academyproject.student.student.response.StudentResponse;
import com.academyproject.student.student.services.StudentService;
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
    public ResponseEntity<Object> create(@RequestBody CreateStudentRequest studentRequest) {
        StudentResponse studentResponse = studentService.save(studentRequest);

        return ResponseHandler.generateResponse(
                "Successfully create new student",
                studentResponse, HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object>
    update(@PathVariable String id, @RequestBody UpdateStudentRequest studentRequest) {
        StudentResponse studentResponse = studentService.update(id, studentRequest);

        return ResponseHandler.generateResponse(
                "Successfully update student",
                studentResponse, HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public void show(@PathVariable String id) {
        var studentResponse = studentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        studentService.deleteById(id);
        return ResponseHandler.successResponse("Successfully delete student", HttpStatus.OK);
    }
}
