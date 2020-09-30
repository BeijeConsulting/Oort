package it.beije.oort.bm.library;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author implements Comparable<Author>, Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_author")
	private int id;
	@Column
	private String surname;
	@Column
	private String name;
	@Column
	private Date date_of_birth;
	@Column
	private Date date_of_death;
	@Column
	private String biography;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getDate_of_birth() {
		return date_of_birth;
	}


	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}


	public Date getDate_of_death() {
		return date_of_death;
	}


	public void setDate_of_death(Date date_of_death) {
		this.date_of_death = date_of_death;
	}


	public String getBiography() {
		return biography;
	}


	public void setBiography(String biography) {
		this.biography = biography;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("| ").append("id: ").append(id).append(" | ")
			.append("surname : ").append(this.surname).append(" | ")
			.append("name : ").append(this.name).append(" | ")
			.append("date of birth : ").append(this.date_of_birth).append(" | ")
			.append("date of death : ").append(this.date_of_death).append(" |\n")
			.append("\t| Biography : ").append(biography).append(" |");
		
		return builder.toString();
	}

	@Override
	public int compareTo(Author a) {
		int comp = a.surname.compareTo(a.getSurname());
		if(comp == 0) comp = this.name.compareTo(a.getName());
		return comp;
	}

}
