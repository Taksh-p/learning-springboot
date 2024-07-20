package com.example.apis.apilearning.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PACKAGE})
@Constraint(validatedBy = EmployeeRoleValidator.class)
public @interface EmployeeRoleValidationAnnotation {

    String message() default "The role either ADMIN or USER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
