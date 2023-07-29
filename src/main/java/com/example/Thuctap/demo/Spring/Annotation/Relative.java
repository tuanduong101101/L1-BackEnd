package com.example.Thuctap.demo.Spring.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.LOCAL_VARIABLE,ElementType.FIELD ,ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateRelative.class)
public @interface Relative {
    public String message() default "Quan hệ giữa tỉnh huyện xã không đúng !";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
