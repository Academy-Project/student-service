package com.academyproject.student.student_transcript.controllers;

import com.academyproject.student.common.resource.ResponseHandler;
import com.academyproject.student.student_transcript.request.CreateStudentTranscriptRequest;
import com.academyproject.student.student_transcript.request.UpdateStudentTranscriptRequest;
import com.academyproject.student.student_transcript.services.StudentTranscriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students/transcripts")
@RequiredArgsConstructor
public class StudentTranscriptController {
    private final StudentTranscriptService studentTranscriptService;

    @GetMapping
    public ResponseEntity<Object> getList() {
        return ResponseHandler.responseWithoutMessage(studentTranscriptService.getList(), HttpStatus.OK);
    }

    @GetMapping("/student/{nim}")
    public ResponseEntity<Object> getListByStudent(String nim) {
        return ResponseHandler.responseWithoutMessage(studentTranscriptService.getListByStudent(nim), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object>
    create(@RequestBody CreateStudentTranscriptRequest studentRequest) {
        var studentResponse = studentTranscriptService.save(studentRequest);

        return ResponseHandler.generateResponse(
                "Student transcript created", studentResponse, HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object>
    update(@PathVariable Long id, @RequestBody UpdateStudentTranscriptRequest studentRequest) {
        var studentResponse = studentTranscriptService.update(id, studentRequest);

        return ResponseHandler.generateResponse(
                "Student transcript updated", studentResponse, HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        var response = studentTranscriptService.findById(id);

        return ResponseHandler.responseWithoutMessage(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        studentTranscriptService.deleteById(id);
        return ResponseHandler.successResponse(
                "Student transcript deleted", HttpStatus.OK
        );
    }
}
