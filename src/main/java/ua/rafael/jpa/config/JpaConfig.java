package ua.rafael.jpa.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("ua/rafael/jpa/config/jpa-config.properties")
@ComponentScan("ua.rafael.jpa")
public class JpaConfig {
  public static String DRIVER = "hibernate.connection.driver_class";
  public static String URL = "hibernate.connection.url";
  public static String USER_NAME = "hibernate.connection.username";
  public static String PASSWORD = "hibernate.connection.password";

  @Autowired
  private Environment env;

  @Bean
  DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(env.getProperty(DRIVER));
    dataSource.setUrl(env.getProperty(URL));
    dataSource.setUsername(env.getProperty(USER_NAME));
    dataSource.setPassword(env.getProperty(PASSWORD));
    return dataSource;
  }

  @Bean
  @Autowired
  public JpaTransactionManager transactionManager( managerFactoryBean) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.set;
    return null;
  }
  
  @Bean
  public AbstractEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =  new LocalContainerEntityManagerFactoryBean();
    return entityManagerFactory;
  }
}
