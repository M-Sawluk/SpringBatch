package com.michal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
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
        entityManagerFactoryRef = "anotherManagerFactory",
        transactionManagerRef = "anotherTransactionManager"
        )
public class EntityManagerConfig {

    @Autowired
    JpaVendorAdapter jpaVendorAdapter;

    @Value("classpath:schema.sql")
    private Resource schema;

    @Bean(name = "anotherEntityManager")
    public EntityManager entityManager() throws ClassNotFoundException {
        return entityManagerFactory().createEntityManager();
    }

    @Bean(name = "anotherManagerFactory")
    public EntityManagerFactory entityManagerFactory() throws ClassNotFoundException {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        properties.setProperty("hibernate.hbm2ddl.auto","create");
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(springbatchmeta());
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.michal.config");   // <- package for entities
        emf.setPersistenceUnitName("anotherPersistenceUnit");
        emf.setJpaProperties(properties);
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean(name="anotherTransactionManager")
    public PlatformTransactionManager transactionManager() throws ClassNotFoundException {
        return new JpaTransactionManager(entityManagerFactory());
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(schema);
        return populator;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() throws ClassNotFoundException {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(springbatchmeta());
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    @Bean(name = "springbatchmeta")
	public DataSource springbatchmeta() throws ClassNotFoundException {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setPassword("1235sawluk");
		dataSource.setUrl("jdbc:mysql://localhost/springbatchmeta");
		dataSource.setUsername("Michal_DB");
		dataSource.setDriverClass((Class<Driver>) Class.forName("com.mysql.jdbc.Driver"));

		return dataSource;
	}
}