package ua.rafael.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contact_tel_detail")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "contact")
public class ContactTelDetail implements Serializable {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "tel_type")
  private String telType;

  @Column(name = "tel_number")
  private String telNumber;

  @ManyToOne
  @JoinColumn(name = "contact_id")
  private Contact contact;

  @Version
  @Column(name = "version")
  private int version;

  public ContactTelDetail(String telType, String telNumber) {
    this.telType = telType;
    this.telNumber = telNumber;
  }
}
