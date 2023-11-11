package com.academyproject.student.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {
    private String nim;
    private String name;
    private String address;
    private String phone;
}
