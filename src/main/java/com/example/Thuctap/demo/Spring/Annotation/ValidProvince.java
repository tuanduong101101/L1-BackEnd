package com.example.Thuctap.demo.Spring.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

@Target({ElementType.LOCAL_VARIABLE,ElementType.FIELD ,ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateProvince.class)
public @interface ValidProvince {
    public String message() default "Provine không đã tồn tại !";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

}
