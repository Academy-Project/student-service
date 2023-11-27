package com.academyproject.student.student.repositories;

import com.academyproject.student.student.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    long countById(Long id);
    
    boolean existsByNim(String nim);

    Optional<Student> findById(Long id);

    void deleteById(Long id);
}
