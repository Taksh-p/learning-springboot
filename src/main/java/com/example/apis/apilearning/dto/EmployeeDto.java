package com.example.apis.apilearning.dto;


import com.example.apis.apilearning.annotations.EmployeeRoleValidationAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    @NotNull(message = "Please Enter your name")
    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, max = 10, message = "The length of the name should be in range of [3,10]")
    private String name;

    @NotBlank(message = "Name can not be blank")
    @Email(message = "Please check your email")
    private String email;

    @NotNull(message = "Age can not be null")
    @Min(message = "Minimum value is: 18 ", value = 18L)
    @Max(message = "Maximum value is: 50", value = 50L)
    private Integer age;

    @NotNull(message = "Date of joining is required")
    @PastOrPresent(message = "Employee Joining data was past or present can not be in future.")
    private LocalDate dateOfJoining;

    @NotNull(message = "Salary can not be null")
    @Positive(message = "Salary always in positive")
    @Digits(integer = 6, fraction = 3, message = "The salary can be in XXXXXX.YY format")
    private double salary;

    @NotNull(message = "The role of the employee is required")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "The role either be ADMIN or USER")
    @EmployeeRoleValidationAnnotation
    private String role;

    @JsonProperty("isActive")
    private boolean isActive;

}
