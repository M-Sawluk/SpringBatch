package com.michal.validator;

import com.michal.batchMichal.HeatOfCombustionDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import javax.validation.ValidationException;
import java.util.List;

public class HeatProcessor implements ItemProcessor<HeatOfCombustionDTO, HeatOfCombustionDTO> {

    @Override
    public HeatOfCombustionDTO process(HeatOfCombustionDTO heatOfCombustionDTO) throws Exception {
        BindingResult results = bindAndValidate(heatOfCombustionDTO);
        if (results.hasErrors())
            buildValidationException(results, heatOfCombustionDTO.getRowIndex());

        return heatOfCombustionDTO;
    }

    public BindingResult bindAndValidate(HeatOfCombustionDTO item) {
        DataBinder binder = new DataBinder(item);
        binder.setValidator(new HeatOfCombustionValidator());
        binder.validate();
        return binder.getBindingResult();

    }

    private void buildValidationException(BindingResult results, Integer row) {
        StringBuilder msg = new StringBuilder();
        FieldError fieldErrors = results.getFieldError();
        msg.append("Unsuccessful upload caused by row: " + row.toString()+",");
        msg.append("column name: " + fieldErrors.getField().toUpperCase());
        msg.append(" and column value: "+ fieldErrors.getRejectedValue());
        throw new ValidationException(msg.toString());
    }
}
