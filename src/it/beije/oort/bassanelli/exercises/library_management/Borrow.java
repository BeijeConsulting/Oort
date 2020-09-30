package it.beije.oort.bassanelli.exercises.library_management;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrow")
public class Borrow {

	public static final String TABLE_BORROW = Borrow.class.getSimpleName();
	public static final String FIELD_ID = "id";
	public static final String FIELD_BOOK = "book_id";
	public static final String FIELD_USER = "user_id";
	public static final String FIELD_DATE_OF_START = "date_of_start";
	public static final String FIELD_DATE_OF_END = "date_of_end";
	public static final String FIELD_NOTE = "note";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private int id;
	
	@Column(name = FIELD_BOOK)
	private int bookId;
	
	@Column(name = FIELD_USER)
	private int userId;
	
	@Column(name = FIELD_DATE_OF_START)
	private LocalDate dateOfStart;
	
	@Column(name = FIELD_DATE_OF_END)
	private LocalDate dateOfEnd;
	
	@Column(name = FIELD_NOTE)
	private String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getDateOfStart() {
		return dateOfStart;
	}

	public void setDateOfStart(LocalDate dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public LocalDate getDateOfEnd() {
		return dateOfEnd;
	}

	public void setDateOfEnd(LocalDate dateOfEnd) {
		this.dateOfEnd = dateOfEnd;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String toString() {
		return this.toString("ID;BOOK;USER;START;END;NOTE");
	}
	
	public String toString(String pattern) {

		String[] fields = pattern.split(";", -1);

		StringBuilder builder = new StringBuilder("[ ");

		for (int i = 0; i < fields.length; i++) {

			switch (fields[i].toUpperCase()) {
			case "ID":
				builder.append("ID : ").append(this.id).append(" ");
				break;
			case "BOOK":
				builder.append("BOOK : ").append(this.bookId).append(" ");
				break;
			case "USER":
				builder.append("USER : ").append(this.userId).append(" ");
				break;
			case "START":
				builder.append("START : ").append(this.dateOfStart).append(" ");
				break;
			case "END":
				builder.append("END : ").append(this.dateOfEnd).append(" ");
				break;
			case "NOTE":
				builder.append("NOTE : ").append(this.note).append(" ");
				break;
			}
		}
		
		return builder.append("]").toString();
	}
	
	
	
}
