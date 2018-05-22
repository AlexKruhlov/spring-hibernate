package ua.rafael.hibernate.dao;

import java.util.List;
import ua.rafael.hibernate.model.Contact;

public interface ContactDao {

  List<Contact> findAll();

  List<Contact> findAllWithDetail();

  Contact findById(Integer id);

  Contact save(Contact contact);

  void delete(Contact contact);
}
