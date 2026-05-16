package com.example.apichallenge.ApiResponse;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponse<T>{
    private final String message;
    private final HttpStatus status;
    private final T data;

    public ApiResponse(String message,HttpStatus status,T data){
        this.message=message;
        this.status=status;
        this.data=data;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
