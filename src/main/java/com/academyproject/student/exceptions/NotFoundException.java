package com.academyproject.student.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String attribute) {
        super(attribute + " not found");
    }
}
