package com.orphan.common.validator;

import com.orphan.common.annotation.Identification;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentificationValidator implements ConstraintValidator<Identification, String> {
    @Override
    public boolean isValid(String identification, ConstraintValidatorContext context) {
        if (identification == null || identification.isEmpty()) return false;
        return identification.matches("\\d{9}");
    }
}
