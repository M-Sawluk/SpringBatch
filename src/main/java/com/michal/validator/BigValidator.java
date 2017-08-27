package com.michal.validator;

import com.michal.batchMichal.HeatOfCombustionDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BigValidator implements ConstraintValidator<BigDecimalValidator, HeatOfCombustionDTO> {
   public void initialize(BigDecimalValidator constraint) {
   }

   public boolean isValid(HeatOfCombustionDTO obj, ConstraintValidatorContext context) {

      if(obj.getNominalne().intValue()>10)
         return false;
      else
         return true;
   }
}
