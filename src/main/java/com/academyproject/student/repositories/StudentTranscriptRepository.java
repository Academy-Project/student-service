package com.academyproject.student.repositories;

import com.academyproject.student.entities.StudentTranscript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTranscriptRepository extends JpaRepository<StudentTranscript, Long> {
}
