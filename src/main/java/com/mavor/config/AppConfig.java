package com.mavor.config;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.lang.NonNull;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.mavor")
public class AppConfig implements WebMvcConfigurer {

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"); // In-memory database
    dataSource.setUsername("sa");
    dataSource.setPassword("");
    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan("com.mavor.models"); // Specify the package where your entity classes are located
    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

    // Configure JPA properties
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "update"); // Automatically create/update schema
    properties.setProperty("hibernate.show_sql", "true"); // Show SQL in logs
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect"); // Use H2 dialect
    em.setJpaProperties(properties);

    return em;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
  
  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  @Override
  public void configureMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
    converters.add(new MappingJackson2HttpMessageConverter());
  }

}
