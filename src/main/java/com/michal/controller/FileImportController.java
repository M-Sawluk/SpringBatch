package com.michal.controller;

import com.michal.batchMichal.FileData;
import com.michal.batchMichal.StaticStorage;
import com.michal.domain.GasPointEntity;
import com.michal.domain.HeatOfCombustionType;
import com.michal.domain.HeatOfCombustionTypeEntity;
import com.michal.domain.MeasureUnitVersionEntity;
import com.michal.repository.*;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.TransactionManager;
import java.io.IOException;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/api/import")

public class FileImportController {
    @Autowired
    private Job job;
    @Autowired
    private JobLauncher jobLauncher;

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

    @Autowired
    private PlatformTransactionManager transactionManager;

    @RequestMapping(method = RequestMethod.POST)
    public String asd(@RequestParam("excel")MultipartFile multipartFile) throws JobExecutionException, IOException {
        Stream.of(HeatOfCombustionType.values()).forEach(i->heatOfCombustionTypeEntRepo.save(new HeatOfCombustionTypeEntity(i)));
        gasPointRepo.save(new GasPointEntity("Lublin"));
        gasPointRepo.save(new GasPointEntity("Warszawa"));
        measureUnitRepo.save(new MeasureUnitVersionEntity("KWH"));
        StaticStorage.put(new FileData(multipartFile));
        JobExecution execution = jobLauncher.run(job, new JobParameters());

        ExitStatus exit = execution.getExitStatus();
        if(execution.getExitStatus().getExitCode().equals("FAILED")){
            return execution.getAllFailureExceptions().get(0).getMessage();
        }
        return "ok";
    }

}