package ua.rafael.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("ua/rafael/config/hibernate.properties")
@ComponentScan("ua.rafael")
public class HibernateConfig {
	public static String DRIVER = "hibernate.connection.driver_class";
	public static String URL = "hibernate.connection.url";
	public static String USER_NAME = "hibernate.connection.username";
	public static String PASSWORD = "hibernate.connection.password";

	public static String MODEL_PACKAGES_TO_SCAN = "ua.rafael";

	public static String HBM2DDL = "hibernate.hbm2ddl.auto";
	public static String DIALECT = "hibernate.dialect";
	public static String IDENTFIERS = "hibernate.globally_quoted_identifiers";

	@Autowired
	Environment env;

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(MODEL_PACKAGES_TO_SCAN);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(env.getProperty(DRIVER));
		dataSource.setUrl(env.getProperty(URL));
		dataSource.setUsername(env.getProperty(USER_NAME));
		dataSource.setPassword(env.getProperty(PASSWORD));
		return dataSource;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty(HBM2DDL, env.getProperty(HBM2DDL));
				setProperty(DIALECT, env.getProperty(DIALECT));
				setProperty(IDENTFIERS, env.getProperty(IDENTFIERS));
			}
		};
	}
}
