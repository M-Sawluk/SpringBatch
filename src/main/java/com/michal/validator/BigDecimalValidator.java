package com.michal.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BigValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BigDecimalValidator {

     String message() default "Too big number";
   
     Class<?>[] groups() default {};  
   
     Class<? extends Payload>[] payload() default {};
}  