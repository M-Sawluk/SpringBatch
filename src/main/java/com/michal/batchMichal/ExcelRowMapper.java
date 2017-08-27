package com.michal.batchMichal;


import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import java.math.BigDecimal;
import java.util.Date;

public class ExcelRowMapper implements RowMapper<HeatOfCombustionDTO> {

    @Override
    public HeatOfCombustionDTO mapRow(RowSet rowSet) throws Exception {
        HeatOfCombustionDTO heatOfCombustionDTO = new HeatOfCombustionDTO();
        heatOfCombustionDTO.setGasPointName(rowSet.getColumnValue(0));
//        heatOfCombustionDTO.setDobaHandlowa(new Date(Long.parseLong(rowSet.getColumnValue(1))));
        heatOfCombustionDTO.setDobaHandlowa(rowSet.getColumnValue(1));
        heatOfCombustionDTO.setNominalne(new BigDecimal(rowSet.getColumnValue(2)));
        heatOfCombustionDTO.setSredniomiesieczne(new BigDecimal(rowSet.getColumnValue(3)));
        heatOfCombustionDTO.setOperatywne(new BigDecimal(rowSet.getColumnValue(4)));
        heatOfCombustionDTO.setZatwierdzone(new BigDecimal(rowSet.getColumnValue(5)));
        heatOfCombustionDTO.setAlias(rowSet.getColumnValue(6));
        heatOfCombustionDTO.setRowIndex(rowSet.getCurrentRowIndex());
        return heatOfCombustionDTO;
    }
}
