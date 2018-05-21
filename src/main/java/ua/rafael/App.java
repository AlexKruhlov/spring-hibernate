package ua.rafael;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rafael.config.HibernateConfig;
import ua.rafael.dao.ContactDao;

public class App {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
    ContactDao contactDao = context.getBean(ContactDao.class);
    System.out.println("FIND ALL:\n" + contactDao.findAll());
    System.out.println("\nFIND ALL WITH DETAIL:\n" + contactDao.findAllWithDetail());
    System.out.println("\nIs FIND ALL equal to FIND ALL WITH DETAIL: "
        + contactDao.findAll().toString().equals(contactDao.findAllWithDetail().toString()));
    System.out.println("\nFIND BY ID 1: " + contactDao.findById(1));
  }
}
