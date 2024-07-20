package com.example.apis.apilearning.advices;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApiResponse<T> {

    private LocalDate timeStamp;
    private T data;
    private ApiError apiError;


    public ApiResponse() {
        this.timeStamp = LocalDate.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }
}
