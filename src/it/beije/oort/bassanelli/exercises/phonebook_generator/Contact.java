package it.beije.oort.bassanelli.exercises.phonebook_generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phonebook")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	@ElementCollection
	@CollectionTable(name = "alias_contact")
	@Column(name = "alias")
	private List<String> alias;

	public Contact() {
	}

	public Contact(String name, String surname, String mobile) {
		this(name, surname, mobile, "");
	}

	public Contact(String name, String cognome, String mobile, String email) {
		this.name = name;
		this.surname = cognome;
		this.mobile = mobile;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(String id) {
		setId(Integer.parseInt(id));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAlias() {
		if (alias == null) {
			alias = new ArrayList<String>();
		}
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	public String toString() {
		return this.toString("NOME;COGNOME;TELEFONO;EMAIL");
	}

	public String toString(String pattern) {

		String[] fields = pattern.split(";", -1);

		StringBuilder builder = new StringBuilder("[ ");

		for (int i = 0; i < fields.length; i++) {

			switch (fields[i].toUpperCase()) {
			case "ID":
				builder.append("ID: ").append(this.id).append(" ");
				break;
			case "NOME":
			case "NAME":
				builder.append("NAME: ").append(this.name).append(" ");
				break;
			case "COGNOME":
			case "SURNAME":
				builder.append("SURNAME: ").append(this.surname).append(" ");
				break;
			case "TELEFONO":
			case "MOBILE":
				builder.append("MOBILE: ").append(this.mobile).append(" ");
				break;
			case "EMAIL":
				builder.append("EMAIL: ").append(this.email).append(" ");
				break;
			case "ALIAS":
				builder.append("ALIAS: ").append(this.alias.toString()).append(" ");
				break;
			}
		}

		return builder.append("]").toString();
	}

	public String toCsvRow() {
		return toCsvRow("NOME;COGNOME;TELEFONO;EMAIL", false);
	}

	public String toCsvRow(String pattern, boolean useRandom) {

		String[] fields = pattern.split(";", -1);

		Random r = new Random();
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < fields.length; i++) {

			switch (fields[i].toUpperCase()) {
			case "ID":
				builder.append(this.id);
				break;
			case "NOME":
				if (useRandom) {

					if (r.nextInt(5) + 1 == 1) {
						builder.append(this.name);
					}

				} else {
					builder.append(this.name);
				}
				break;
			case "COGNOME":
				if (useRandom) {

					if (r.nextInt(5) + 1 == 1) {
						builder.append(this.surname);
					}

				} else {
					builder.append(this.surname);
				}
				break;
			case "TELEFONO":
				builder.append(this.mobile);
				break;
			case "EMAIL":
				builder.append(this.email);
				break;
			case "ALIAS":
				builder.append(this.alias.toString());
				break;
			}

			builder.append(";");
		}

		builder.deleteCharAt(builder.length() - 1);
		builder.append("\n");

		return builder.toString();
	}
}
