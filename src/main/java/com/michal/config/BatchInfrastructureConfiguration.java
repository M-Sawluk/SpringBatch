package com.michal.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
public class BatchInfrastructureConfiguration{

@Bean
public JobOperator jobOperator(final JobLauncher jobLauncher, final JobExplorer jobExplorer,
                               final JobRepository jobRepository, final JobRegistry jobRegistry) throws Exception {
    return new SimpleJobOperator() {
        {
            setJobLauncher(jobLauncher);
            setJobExplorer(jobExplorer);
            setJobRepository(jobRepository);
            setJobRegistry(jobRegistry);
        }
    };
}

    @Bean
    public JobExplorerFactoryBean jobExplorer(@Qualifier("springbatchmeta") DataSource dataSource) throws Exception {
        return new JobExplorerFactoryBean() {
            {
                setDataSource(dataSource);
            }
        };
    }

    @Bean
    public MapJobRegistry jobRegistry() throws Exception {
        return new MapJobRegistry();
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegisterBeanPostProcess(final JobRegistry jobRegistry) throws Exception {
        return new JobRegistryBeanPostProcessor() {
            {
                setJobRegistry(jobRegistry);
            }
        };
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }

    @Bean
    public JobRepository jobRepository(@Qualifier("springbatchmeta") DataSource dataSource,@Qualifier("anotherTransactionManager") PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepo = new JobRepositoryFactoryBean();
        jobRepo.setDataSource(dataSource);
        jobRepo.setTransactionManager(transactionManager);
        jobRepo.setDatabaseType("MYSQL");
        return jobRepo.getObject();
    }


//    @Bean
//    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
//        SimpleJobLauncher launcher = new SimpleJobLauncher();
//        launcher.setJobRepository(jobRepository);
//        return launcher;
//    }
//
//    @Bean
//    public PlatformTransactionManager getTransactionManager() {
//        return new ResourcelessTransactionManager();
//    }
//
//    @Bean
//    public JobRepository jobRepository() throws Exception {
//        return new MapJobRepositoryFactoryBean(getTransactionManager()).getObject();
//    }
}