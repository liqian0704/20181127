package com.yeepay.g3.ymf.pay.controller.ymfbill.util;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidateUtil {

    public Set<ConstraintViolation<Object>> validateDTOproperties(Object obj) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(obj, new Class[0]);

        return violations;
    }
}
