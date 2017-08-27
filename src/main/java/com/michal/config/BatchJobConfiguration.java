package com.michal.config;

import com.michal.batchMichal.*;
import com.michal.validator.HeatProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@SuppressWarnings("ALL")
@Configuration
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Import(BatchInfrastructureConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BatchJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job readFromCSV() {
        return jobBuilderFactory.get("importCSV")
                .start(validStep())
                .next(readFileStep())
                .build();
    }

    @Bean
    public Step readFileStep() {
        return stepBuilderFactory.get("importFileStep")
                .<HeatOfCombustionDTO, HeatOfCombustionDTO>chunk(10)
                .reader(reader())
                .processor(heatProcessor())
                .writer(writer())
                .build();
    }

    @Bean
    public Step validStep() {
        return stepBuilderFactory.get("validStep")
                .<HeatOfCombustionDTO, HeatOfCombustionDTO>chunk(10)
                .reader(reader())
                .processor(new HeatProcessor())
                .build();
    }

    @Bean
    public ItemWriter writer() {
        return new ExcelWriter();
    }

    @Bean
    public ItemReader reader() {
        PoiItemReader<HeatOfCombustionDTO> itemReader = new ExcelReader();
        itemReader.setRowMapper(rowMapper());
        itemReader.setLinesToSkip(1);
        return itemReader;
    }

    @Bean
    RowMapper<HeatOfCombustionDTO> rowMapper() {
        return new ExcelRowMapper();
    }

    @Bean
    ItemProcessor heatProcessor(){
        return new HeatProcessor();
    }
}