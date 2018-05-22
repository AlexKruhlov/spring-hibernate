package ua.rafael.hibernate.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rafael.hibernate.dao.ContactDao;
import ua.rafael.hibernate.model.Contact;


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
  @Transactional
  public Contact save(Contact contact) {
    sessionFactory.getCurrentSession().saveOrUpdate(contact);
    LOG.info("Contact was successfully saved with id = " + contact.getId());
    return contact;
  }

  @Override
  @Transactional
  public void delete(Contact contact) {
    sessionFactory.getCurrentSession().delete(contact);
    LOG.info("Contact was successfully deleted with id = " + contact.getId());
  }
}
