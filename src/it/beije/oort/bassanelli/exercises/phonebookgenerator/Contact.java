package it.beije.oort.bassanelli.exercises.phonebookgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Contact {

	private String name;
	private String surname;
	private String mobile;
	private String email;
	private List<String> alias = new ArrayList<String>();

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
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	public String toString() {
		return this.toString("NOME;COGNOMET;TELEFONO;EMAIL");
	}

	public String toString(String pattern) {

		String[] fields = pattern.split(";", -1);

		StringBuilder builder = new StringBuilder("CONTACT [ ");

		for (int i = 0; i < fields.length; i++) {

			switch (fields[i].toUpperCase()) {
			case "NOME":
				builder.append("NAME: ").append(this.name).append(" ");
				break;
			case "COGNOME":
				builder.append("SURNAME: ").append(this.surname).append(" ");
				break;
			case "TELEFONO":
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
