package com.michal.validator;


import com.michal.batchMichal.HeatOfCombustionDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HeatOfCombustionValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return HeatOfCombustionDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        HeatOfCombustionDTO a = (HeatOfCombustionDTO) o;
        if(a.getNominalne().intValue()>10)
            errors.rejectValue("nominalne","Value was too big for row: " + a.getRowIndex().toString());
    }
}
