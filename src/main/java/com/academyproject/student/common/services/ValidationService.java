package com.academyproject.student.common.services;

import com.academyproject.student.student.request.CreateStudentRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationService {
    @Autowired
    private Validator validator;

    public void validate(Object request) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
        if (0 != constraintViolations.size()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
