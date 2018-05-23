package ua.rafael.jpa.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
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

  public static String DIALECT = "hibernate.dialect";
  public static String MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
  public static String FETCH_SIZE = "hibernate.jdbc.fetch_size";
  public static String BATCH_SIZE = "hibernate.jdbc.batch_size";
  public static String SHOW_SQL = "hibernate.show_sql";

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
  public JpaTransactionManager transactionManager(EntityManagerFactory managerFactoryBean) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(managerFactoryBean);;
    return jpaTransactionManager;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPackagesToScan(new String[] {"ua.rafael.jpa.model"});
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setJpaProperties(jpaProperties());
    return entityManagerFactoryBean;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  private Properties jpaProperties() {
    return new Properties() {
      {
        setProperty(DIALECT, env.getProperty(DIALECT));
        setProperty(MAX_FETCH_DEPTH, env.getProperty(MAX_FETCH_DEPTH));
        setProperty(FETCH_SIZE, env.getProperty(FETCH_SIZE));
        setProperty(BATCH_SIZE, env.getProperty(BATCH_SIZE));
        setProperty(SHOW_SQL, env.getProperty(SHOW_SQL));
      }
    };
  }
}
