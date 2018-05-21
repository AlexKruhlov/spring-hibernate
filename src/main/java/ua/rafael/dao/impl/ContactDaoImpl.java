package ua.rafael.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rafael.dao.ContactDao;
import ua.rafael.model.Contact;

@Repository
public class ContactDaoImpl implements ContactDao {
  private static final Log LOG = LogFactory.getLog(ContactDaoImpl.class);

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  @Transactional(readOnly = true)
  public List<Contact> findAll() {
    return sessionFactory.getCurrentSession().createQuery("from Contact c").list();
  }

  @Override
  public List<Contact> findAllWithDetail() {
    return null;
  }

  @Override
  public Contact findById(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Contact save(Contact contact) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Contact contact) {
    // TODO Auto-generated method stub

  }

}
