package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * @author zhupeiyou
 * @since 2020-07-02 12:58
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstant,Object> {

    @Override
    public void initialize(MyConstant myconstant) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
