package com.academyproject.student.student.entities;

import com.academyproject.student.student_transcript.entities.StudentTranscript;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @Column(nullable = false)
    private String nim;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "student")
    private List<StudentTranscript> studentTranscripts;

    public int calculateTotalSks() {
        return studentTranscripts.stream()
                .mapToInt(StudentTranscript::getSks)
                .sum();
    }
}
