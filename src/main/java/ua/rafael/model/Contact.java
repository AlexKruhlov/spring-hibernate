package ua.rafael.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contact")
@NamedQueries({@NamedQuery(name = "Contact.findAllWithDetail",
    query = "SELECT DISTINCT c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h"),
    @NamedQuery(name = "Contact.findById",
        query = "select distinct c from Contact c left join fetch c.contactTelDetails left join fetch c.hobbies h where c.id=:id")})
@Getter
@Setter
@ToString
public class Contact implements Serializable {
  public static String FIND_ALL_WITH_DETAIL = "Contact.findAllWithDetail";
  public static String FIND_BY_ID = "Contact.findById";

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Temporal(DATE)
  @Column(name = "birth_date")
  private Date birthDate;

  @OneToMany(mappedBy = "contact", cascade = ALL, orphanRemoval = true, fetch = EAGER)
  private Set<ContactTelDetail> contactTelDetails = new HashSet<>();

  @ManyToMany(fetch = EAGER)
  @JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name = "contact_id"),
      inverseJoinColumns = @JoinColumn(name = "hobby_id"))
  private Set<Hobby> hobbies = new HashSet<>();

  @Version
  @Column(name = "version")
  private int version;

  public void add(ContactTelDetail contactTelDetail) {
    contactTelDetail.setContact(this);
    contactTelDetails.add(contactTelDetail);
  }

  public void revove(ContactTelDetail contactTelDetail) {
    contactTelDetails.remove(contactTelDetail);
  }
}
