package com.academyproject.student.common.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String attribute) {
        super(attribute + " not found");
    }
}
