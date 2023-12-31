package com.academyproject.student.student.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String nim;
    private String name;
    private String address;
    private String phone;
//    private Integer totalSks;
}
