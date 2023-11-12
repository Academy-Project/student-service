package com.academyproject.student.student_transcript.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentTranscriptController {
//    private final StudentTranscriptService studentTranscriptService;
//
//    public List<StudentTranscript> getList() {
//        return studentTranscriptService.getList();
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?>
//    create(@RequestBody CreateStudentTranscriptRequest studentRequest) {
//        StudentTranscriptResource studentResponse = studentTranscriptService.save(studentRequest);
//
//        StructureResponse<StudentResponse> response = new StructureResponse<>(
//                "Successfully create new student"
//        );
//
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<StructureResponse<StudentTranscriptResource>>
//    update(@PathVariable Long id, @RequestBody UpdateStudentRequest studentRequest) {
//        StudentTranscriptResource studentResponse = studentTranscriptService.update(id, studentRequest);
//
//        StructureResponse<StudentTranscriptResource> response = new StructureResponse<>(
//                "Successfully update student", studentResponse
//        );
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public void show(@PathVariable Long id) {
//        studentTranscriptService.findById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        studentTranscriptService.deleteById(id);
//    }
}
