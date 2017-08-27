package com.michal.batchMichal;

import com.michal.validator.BigDecimalValidator;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class HeatOfCombustionDTO {
    Integer rowIndex;
    String gasPointName;
    String dobaHandlowa;
    BigDecimal nominalne;
    BigDecimal sredniomiesieczne;
    BigDecimal operatywne;
    BigDecimal zatwierdzone;
    String alias;
}
