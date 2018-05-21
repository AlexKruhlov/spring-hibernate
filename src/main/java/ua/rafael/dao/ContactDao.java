package ua.rafael.dao;

import java.util.List;
import ua.rafael.model.Contact;

public interface ContactDao {

  List<Contact> findAll();

  List<Contact> findAllWithDetail();

  Contact findById(Integer id);

  Contact save(Contact contact);

  void delete(Contact contact);
}
