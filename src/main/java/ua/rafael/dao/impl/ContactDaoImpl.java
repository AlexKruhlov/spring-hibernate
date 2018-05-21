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
  @Transactional(readOnly = true)
  public List<Contact> findAllWithDetail() {
    return sessionFactory.getCurrentSession().getNamedQuery(Contact.FIND_ALL_WITH_DETAIL).list();
  }

  @Override
  @Transactional(readOnly = true)
  public Contact findById(Integer id) {
    return (Contact) sessionFactory.getCurrentSession().getNamedQuery(Contact.FIND_BY_ID)
        .setParameter("id", id).uniqueResult();
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
