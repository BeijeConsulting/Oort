package it.beije.oort.bm.library;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book extends LibraryBean implements Comparable<Book>, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_book")
	private int id;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private int author;
	@Column
	private int publisher;
	@Column
	private String year;
	
	
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


	public int getAuthor() {
		return author;
	}


	public void setAuthor(int author) {
		this.author = author;
	}


	public int getPublisher() {
		return publisher;
	}


	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		if(year.length() != 4) throw new IllegalArgumentException("Year must be 4 digits long.");
		try {
			Integer.parseInt(year);
		}catch(NumberFormatException ex) {
			throw new IllegalArgumentException("Year must be a number!");
		}
		this.year = year;
	}


	@Override
	public int compareTo(Book b) {
		int comp = this.title.compareTo(b.getTitle());
		if(comp == 0) {
			if(this.publisher == b.getPublisher()) {
				comp = this.year.compareTo(getYear());
			}else if(this.publisher < b.getPublisher()) {
				comp = -1;
			}else {
				comp = 1;
			}
		}
		return comp;
	}

}
