package cn.lastwhisper.validated.validator;

import cn.lastwhisper.validated.annotation.CannotHaveBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CannotHaveBlankValidator implements ConstraintValidator<CannotHaveBlank, String> {

    @Override
    public void initialize(CannotHaveBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //null 时不进行校验
        if (value != null && value.contains(" ")) {

            // 获取默认提示信息
            String defaultConstraintMessageTemplate = context.getDefaultConstraintMessageTemplate();
            System.out.println("default message :" + defaultConstraintMessageTemplate);
            // 禁用默认提示信息
            context.disableDefaultConstraintViolation();
            // 设置提示语
            context.buildConstraintViolationWithTemplate("can not contains blank").addConstraintViolation();
            return false;
        }
        return true;
    }
}