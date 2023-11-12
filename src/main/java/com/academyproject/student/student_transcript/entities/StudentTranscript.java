package com.academyproject.student.student_transcript.entities;

import com.academyproject.student.student.entities.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTranscript {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nim", referencedColumnName = "nim", nullable = false)
    private Student student;

    @Column(nullable = false)
    private Integer sks;

    @Column(nullable = false)
    private String semester;

    private Double ipk;
}
