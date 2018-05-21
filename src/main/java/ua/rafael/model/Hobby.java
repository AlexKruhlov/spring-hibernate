package ua.rafael.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "hobby")
@Getter
@Setter
@ToString(exclude = "contacts")
public class Hobby implements Serializable {

	@Id
	@Column(name = "hobby_id")
	private String hobbyId;

	@ManyToMany
	@JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name = "hobby_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
	private Set<Contact> contacts = new HashSet<>();
}
