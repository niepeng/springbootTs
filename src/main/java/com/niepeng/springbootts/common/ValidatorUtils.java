package com.niepeng.springbootts.common;


import com.niepeng.springbootts.exception.MyException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorUtils {
    /**
     * 对输入的Object进行校验
     *
     * @param obj 输入Object
     */
    public static void validate(Object obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        if (!constraintViolations.isEmpty()) {
            StringBuffer strb = new StringBuffer();
            for (ConstraintViolation<Object> constrain : constraintViolations) {
                strb.append("[").append(constrain.getMessage()).append("]");
            }
            System.out.println(strb.toString());
            throw new MyException("validatorUtil error");
        }
    }
}
