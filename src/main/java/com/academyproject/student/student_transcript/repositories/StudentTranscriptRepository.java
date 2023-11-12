package com.academyproject.student.student_transcript.repositories;

import com.academyproject.student.student_transcript.entities.StudentTranscript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTranscriptRepository extends JpaRepository<StudentTranscript, Long> {
}
