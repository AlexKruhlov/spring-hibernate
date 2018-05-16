package ua.rafael.model;

import static javax.persistence.CascadeType.ALL;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contact")
@Getter
@Setter
@ToString
public class Contact implements Serializable {

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

	@OneToMany(mappedBy = "contact", cascade = ALL, orphanRemoval = true)
	private Set<ContactTelDetail> contactTelDetails = new HashSet<>();

	@ManyToMany()
	@JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name = "contact_id"), inverseJoinColumns = @JoinColumn(name = "hobby_id"))
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
