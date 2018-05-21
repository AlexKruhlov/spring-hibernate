package ua.rafael;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.rafael.config.HibernateConfig;
import ua.rafael.dao.ContactDao;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		System.out.println(context.getBean(ContactDao.class).findAll());
	}
}
