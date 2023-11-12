package com.academyproject.student.student_transcript.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UpdateStudentTranscriptRequest {
    private Integer sks;
    private String semester;
    private Double ipk;
}
