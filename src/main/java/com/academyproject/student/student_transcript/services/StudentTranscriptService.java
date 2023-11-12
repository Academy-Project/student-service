package com.academyproject.student.student_transcript.services;

import com.academyproject.student.common.exceptions.NotFoundException;
import com.academyproject.student.common.services.ValidationService;
import com.academyproject.student.student.entities.Student;
import com.academyproject.student.student.repositories.StudentRepository;
import com.academyproject.student.student_transcript.entities.StudentTranscript;
import com.academyproject.student.student_transcript.repositories.StudentTranscriptRepository;
import com.academyproject.student.student_transcript.request.CreateStudentTranscriptRequest;
import com.academyproject.student.student_transcript.request.UpdateStudentTranscriptRequest;
import com.academyproject.student.student_transcript.response.StudentTranscriptResource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StudentTranscriptService {

    private final StudentTranscriptRepository studentTranscriptRepository;
    private final ValidationService validationService;
    private final StudentRepository studentRepository;

    public List<StudentTranscript> getList() {
        return studentTranscriptRepository.findAll();
    }

    public List<StudentTranscriptResource> getListByStudent(String nim) {
        Student student = studentRepository.findById(nim)
                .orElseThrow(() -> new NotFoundException("Student"));
        var s = studentTranscriptRepository.findByStudent(student);
        return s.stream().map(this::toStudentTranscriptResource).toList();
    }

    public StudentTranscriptResource save(CreateStudentTranscriptRequest studentRequest) {
        validationService.validate(studentRequest);
        Student student = studentRepository.findById(studentRequest.getNim())
                .orElseThrow(() -> new NotFoundException("Student"));

        log.info("Student: {}", student);
        StudentTranscript studentTranscript = StudentTranscript.builder()
                .sks(studentRequest.getSks())
                .semester(studentRequest.getSemester())
                .ipk(studentRequest.getIpk())
//                .student(student)
                .build();
        studentTranscriptRepository.save(studentTranscript);

        return toStudentTranscriptResource(studentTranscript);
    }

    public StudentTranscriptResource update(Long id, UpdateStudentTranscriptRequest studentRequest) {
        validationService.validate(studentRequest);

        StudentTranscript studentTranscript = getStudentTranscript(id);

        studentTranscript.setSks(studentRequest.getSks());
        studentTranscript.setSemester(studentRequest.getSemester());
        studentTranscript.setIpk(studentRequest.getIpk());
        studentTranscriptRepository.save(studentTranscript);

        return toStudentTranscriptResource(studentTranscript);
    }

    public StudentTranscriptResource findById(Long id) {
        StudentTranscript studentTranscript = getStudentTranscript(id);
        return toStudentTranscriptResource(studentTranscript);
    }

    public void deleteById(Long id) {
        StudentTranscript studentTranscript = getStudentTranscript(id);
        studentTranscriptRepository.delete(studentTranscript);
    }

    private StudentTranscript getStudentTranscript(Long id) {
        return studentTranscriptRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student Transcript"));
    }

    private StudentTranscriptResource toStudentTranscriptResource(StudentTranscript studentTranscript) {
        return StudentTranscriptResource.builder()
                .sks(studentTranscript.getSks())
                .semester(studentTranscript.getSemester())
                .ipk(studentTranscript.getIpk())
                .student(studentTranscript.getStudent())
                .build();
    }
}
