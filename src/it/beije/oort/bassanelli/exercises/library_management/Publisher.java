package it.beije.oort.bassanelli.exercises.library_management;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publisher")
public class Publisher {

	public static final String TABLE_PUBLISHER = Publisher.class.getSimpleName();
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_DESCRIPTION = "description";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;

	@Column(name = FIELD_NAME)
	private String name;

	@Column(name = FIELD_DESCRIPTION)
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return this.toString("ID;NAME;DESCRIPTION");
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
			case "DESCRIPTION":
				builder.append("DESCRIPTION : ").append(this.description).append(" ");
				break;
			}
		}
		
		return builder.append("]").toString();
	}
}
