package com.michal.batchMichal;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.excel.Sheet;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.core.io.InputStreamResource;

public class ExcelReader extends PoiItemReader<HeatOfCombustionDTO> {

    @BeforeStep
    public void load() {
        setResource((InputStreamResource) StaticStorage.get(BatchStaticStorageKey.INPUT));
    }

    @Override
    protected Sheet getSheet(int sheet) {
        return super.getSheet(0);
    }
}
