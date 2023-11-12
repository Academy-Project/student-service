package com.academyproject.student.student_transcript.response;

import com.academyproject.student.student.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentTranscriptResource {
    private Long id;
    private Integer sks;
    private String semester;
    private Double ipk;

//    private Student student;
}
