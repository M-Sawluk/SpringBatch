package com.michal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.michal"})
public class PrimaryEntityManager {


    @Bean
    public EntityManager entityManager() throws ClassNotFoundException {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    @Primary
    public EntityManagerFactory entityManagerFactory() throws ClassNotFoundException {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto","create");
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.michal");   // <- package for entities
        emf.setPersistenceUnitName("primaryPersistenceUnit");
        emf.setJpaProperties(properties);
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws ClassNotFoundException {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    @Primary
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setPassword("1235sawluk");
        dataSource.setUrl("jdbc:mysql://localhost/springbatch");
        dataSource.setUsername("Michal_DB");
        dataSource.setDriverClass((Class<Driver>) Class.forName("com.mysql.jdbc.Driver"));

        return dataSource;
    }
}