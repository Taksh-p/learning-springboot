package com.example.apis.apilearning.advices;


import com.example.apis.apilearning.exceptions.ResourcesNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourcesNotFound.class)
    public ResponseEntity<ApiResponse<?>> employeeNotFoundException(ResourcesNotFound exception){

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();

        return buildErrorResponse(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> globalException(Exception exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();

        return buildErrorResponse(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponse(ApiError apiError) {

        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException exception){

        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Internal server error")
                .subError(errors)
                .build();
        return buildErrorResponse(apiError);
    }


}
