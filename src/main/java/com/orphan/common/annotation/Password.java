package com.orphan.common.annotation;

import com.orphan.common.validator.IdentificationValidator;
import com.orphan.common.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Password cần độ dài từ 8 - 15 kí tự, " +
            "Có ít nhất 1 kí tự thường, 1 kí tự viết hoa và 1 chữ số," +
            "Có 1 trong các kí tự đặc biệt sau (! # $ @ _ + , ? . - )";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
