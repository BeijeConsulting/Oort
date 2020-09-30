package it.beije.oort.bassanelli.exercises.library_management;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	
	public static final String TABLE_BOOK = Book.class.getSimpleName();
	public static final String FIELD_ID = "id";
	public static final String FIELD_TITLE = "title";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_AUTHOR = "author_id";
	public static final String FIELD_PUBLISHER = "publisher_id";
	public static final String FIELD_YEAR = "year";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_TITLE)
	private String title;
	
	@Column(name = FIELD_DESCRIPTION)
	private String description;
	
	@Column(name = FIELD_AUTHOR)
	private int authorId;
	
	@Column(name = FIELD_PUBLISHER)
	private int publisherId;
	
	@Column(name = FIELD_YEAR)
	private int year; 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String toString() {
		return this.toString("ID;TITLE;DESCRIPTION;AUTHOR;PUBLISHER;YEAR");
	}
	
	public String toString(String pattern) {

		String[] fields = pattern.split(";", -1);

		StringBuilder builder = new StringBuilder("[ ");

		for (int i = 0; i < fields.length; i++) {

			switch (fields[i].toUpperCase()) {
			case "ID":
				builder.append("ID : ").append(this.id).append(" ");
				break;
			case "TITLE":
				builder.append("TITLE : ").append(this.title).append(" ");
				break;
			case "DESCRIPTION":
				builder.append("DESCRIPTION : ").append(this.description).append(" ");
				break;
			case "AUTHOR":
				builder.append("AUTHOR : ").append(this.authorId).append(" ");
				break;
			case "PUBLISHER":
				builder.append("PUBLISHER : ").append(this.publisherId).append(" ");
				break;
			case "YEAR":
				builder.append("YEAR : ").append(this.year).append(" ");
				break;
			}
		}
		
		return builder.append("]").toString();
	}
	
}
