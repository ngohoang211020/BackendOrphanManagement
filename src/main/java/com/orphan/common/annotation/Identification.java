package com.orphan.common.annotation;

import com.orphan.common.constants.ValidationConstants;
import com.orphan.common.validator.IdentificationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdentificationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Identification {
    String message() default ValidationConstants.IDENTIFICATION_PATTERN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
