package com.michal.batchMichal;

import com.michal.domain.*;
import com.michal.repository.*;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class ExcelWriter implements ItemWriter<HeatOfCombustionDTO> {

    @Autowired
    private GasPointRepo gasPointRepo;
    @Autowired
    private HeatOfCombustionRepo heatOfCombustionRepo;
    @Autowired
    private HeatOfCombustionTypeEntRepo heatOfCombustionTypeEntRepo;
    @Autowired
    private HeatOfCombustionValuesRepo heatOfCombustionValuesRepo;
    @Autowired
    private MeasureUnitRepo measureUnitRepo;



    @Override
    public void write(List<? extends HeatOfCombustionDTO> list) throws Exception {

        list.forEach(i->{
            GasPointEntity gasPointEntity = gasPointRepo.findByName(i.getGasPointName());
            MeasureUnitVersionEntity measureUnitVersionEntity = measureUnitRepo.findByAlias(i.getAlias());
            HeatOfCombustionTypeEntity heatOfCombustionTypeEntity = heatOfCombustionTypeEntRepo.findByName(HeatOfCombustionType.nominalne);
//
//            Stream.of(HeatOfCombustionType.values()).forEach(a->{
////                HeatOfCombustionEntity heatOfCombustionEntity = heatOfCombustionRepo
////                        .findByGasPointAndDateFromLessThanEqualAndDateToGreaterThanEqualAndHeatOfCombustionType(gasPointEntity,
////                                i.getDobaHandlowa(),i.getDobaHandlowa(),heatOfCombustionTypeEntity);
//
//
//                if(heatOfCombustionEntity==null){
//                    heatOfCombustionEntity = new HeatOfCombustionEntity();
//                    heatOfCombustionEntity.setGasPoint(gasPointEntity);
//                    heatOfCombustionEntity.setMeasureUnit(measureUnitVersionEntity);
//                    heatOfCombustionEntity.setDateFrom(i.getDobaHandlowa());
//                    heatOfCombustionEntity.setDateTo(i.getDobaHandlowa());
//                    heatOfCombustionEntity.setHeatOfCombustionType(heatOfCombustionTypeEntRepo.findByName(a));
//
//                    try {
//                        Method method = i.getClass().getMethod("get"+a.name(),new Class[]{});
//                        heatOfCombustionEntity.getHeatOfCombustionValues().add(new HeatOfCombustionValuesEntity(BigDecimal.valueOf((Integer)method.invoke(i)),i.getDobaHandlowa()));
//                    } catch (NoSuchMethodException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//                else{
//                    try {
//                        Method method = i.getClass().getMethod("get"+a.name(),new Class[]{});
//                        heatOfCombustionEntity.getHeatOfCombustionValues().add(new HeatOfCombustionValuesEntity(BigDecimal.valueOf((Integer)method.invoke(i)),i.getDobaHandlowa()));
//                    } catch (NoSuchMethodException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        });

    }



}

