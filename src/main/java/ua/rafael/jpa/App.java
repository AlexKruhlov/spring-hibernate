package ua.rafael.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import ua.rafael.jpa.config.JpaConfig;

public class App {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JpaConfig.class);
    applicationContext.getBean(LocalContainerEntityManagerFactoryBean.class);
  }
}
