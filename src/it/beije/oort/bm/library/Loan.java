package it.beije.oort.bm.library;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "loan")
public class Loan implements Comparable<Loan>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_loan")
	private int id;
	@Column
	private int user;
	@Column
	private int book;
	@Column
	private String start_date;
	@Column
	private String end_date;
	@Column
	private String notes;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUser() {
		return user;
	}


	public void setUser(int user) {
		this.user = user;
	}


	public int getBook() {
		return book;
	}


	public void setBook(int book) {
		this.book = book;
	}


	public String getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date.toString();
	}


	public String getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date.toString();
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("| ").append("id: ").append(id).append(" | ")
			.append("user: ").append(this.user).append(" | ")
			.append("book : ").append(this.book).append(" |\n")
			.append("\t| Notes : ").append(this.notes).append(" |");
		return builder.toString();
	}

	@Override
	public int compareTo(Loan l) {
		return this.start_date.compareTo(l.getStart_date());
	}

}
