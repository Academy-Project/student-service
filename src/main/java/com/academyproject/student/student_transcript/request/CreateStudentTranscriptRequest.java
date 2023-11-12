package com.academyproject.student.student_transcript.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreateStudentTranscriptRequest {
    @NotBlank
    private String nim;

    @NotNull
    @Min(0)
    private Integer sks;

    @NotBlank
    private String semester;

    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "4.0", inclusive = false)
    private Double ipk;
}
