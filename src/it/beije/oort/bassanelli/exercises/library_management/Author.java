package it.beije.oort.bassanelli.exercises.library_management;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class Author {

	public static final String TABLE_AUTHOR = Author.class.getSimpleName();
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_SURNAME = "surname";
	public static final String FIELD_DATE_OF_BIRTH = "date_of_birth";
	public static final String FIELD_DATE_OF_DEATH = "date_of_death";
	public static final String FIELD_BIOGRAPHY = "biography";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_NAME)
	private String name;
	
	@Column(name = FIELD_SURNAME)
	private String surname;
	
	@Column(name = FIELD_DATE_OF_BIRTH)
	private LocalDate dateOfBirth;
	
	@Column(name = FIELD_DATE_OF_DEATH)
	private LocalDate dateOfDeath;
	
	@Column(name = FIELD_BIOGRAPHY)
	private String biography;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public String toString() {
		return this.toString("ID;NAME;SURNAME;BIRTH;DEATH;BIOGRAPHY");
	}
	
	public String toString(String pattern) {

		String[] fields = pattern.split(";", -1);

		StringBuilder builder = new StringBuilder("[ ");

		for (int i = 0; i < fields.length; i++) {

			switch (fields[i].toUpperCase()) {
			case "ID":
				builder.append("ID : ").append(this.id).append(" ");
				break;
			case "NAME":
				builder.append("NAME : ").append(this.name).append(" ");
				break;
			case "SURNAME":
				builder.append("SURNAME : ").append(this.surname).append(" ");
				break;
			case "BIRTH":
				builder.append("DATE OF BIRTH : ").append(this.dateOfBirth).append(" ");
				break;
			case "DEATH":
				builder.append("DATE OF DEATH : ").append(this.dateOfDeath).append(" ");
				break;
			case "BIOGRAPHY":
				builder.append("BIOGRAPHY : ").append(this.biography).append(" ");
				break;
			}
		}

		return builder.append("]").toString();
	}
	
}
