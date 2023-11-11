package com.academyproject.student.response;

import java.util.List;

public class StructureResponse<T> {
    private String message;
    private T data;
    private List<String> errors;

    // send response data with message
    public StructureResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    // send response data only
    public StructureResponse(T data) {
        this.data = data;
    }

    // send response message only
    public StructureResponse(String message) {
        this.message = message;
    }

    // send error response
    public StructureResponse(List<String> errors) {
        this.errors = errors;
    }
}
