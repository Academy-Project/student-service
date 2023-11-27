package com.academyproject.student.student_transcript.repositories;

import com.academyproject.student.student.entities.Student;
import com.academyproject.student.student_transcript.entities.StudentTranscript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentTranscriptRepository extends JpaRepository<StudentTranscript, Long> {
    List<StudentTranscript> findByStudent(Student student);
}
