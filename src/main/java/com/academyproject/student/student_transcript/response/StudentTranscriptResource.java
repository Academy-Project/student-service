package com.academyproject.student.student_transcript.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTranscriptResource {
    private Long id;
    private Integer sks;
    private String semester;
    private Double ipk;
}
