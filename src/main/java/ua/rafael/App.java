package ua.rafael;

import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rafael.config.HibernateConfig;
import ua.rafael.dao.ContactDao;
import ua.rafael.model.Contact;
import ua.rafael.model.ContactTelDetail;
import ua.rafael.model.Hobby;

public class App {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
    ContactDao contactDao = context.getBean(ContactDao.class);
    System.out.println("FIND ALL:\n" + contactDao.findAll());
    System.out.println("\nFIND ALL WITH DETAIL:\n" + contactDao.findAllWithDetail());
    System.out.println("\nIs FIND ALL equal to FIND ALL WITH DETAIL: "
        + contactDao.findAll().toString().equals(contactDao.findAllWithDetail().toString()));
    System.out.println("\nFIND BY ID 1: " + contactDao.findById(1));

    System.out.println("\nSAVE new contact");
    Contact contact = new Contact();
    contact.setFirstName("Dmitriy");
    contact.setLastName("Stoinless");
    contact.setBirthDate(new Date());
    contact.add(new ContactTelDetail("mobile", "222222"));
    contact.add(new Hobby("Reading"));
    contactDao.save(contact);
    System.out.println("FIND BY ID 4:" + contactDao.findById(4));
  }
}
