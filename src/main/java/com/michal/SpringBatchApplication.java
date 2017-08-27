package com.michal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
@SpringBootApplication(exclude = {BatchAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class SpringBatchApplication {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

//	@Bean
//	Validator validator(){
//		return new LocalValidatorFactoryBean();
//	}

	@PostConstruct
	public void print(){
		Stream.of(applicationContext.getBeanDefinitionNames()).forEach(i->logger.info(i.toString()));
	}

}
