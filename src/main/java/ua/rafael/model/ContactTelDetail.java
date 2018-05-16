package ua.rafael.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContactTelDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;

	private String telType;

	private String telNumber;

	@ManyToOne
	@JoinColumn(name = "contact_id")
	private Contact contact;

	@Version
	private int version;

	public ContactTelDetail(String telType, String telNumber) {
		this.telType = telType;
		this.telNumber = telNumber;
	}
}
