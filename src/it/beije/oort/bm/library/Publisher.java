package it.beije.oort.bm.library;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "publisher")
public class Publisher implements Comparable<Publisher>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_publisher")
	private int id;
	@Column
	private String name;
	@Column
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("| ").append("id: ").append(id).append(" | ")
			.append("name : ").append(this.name).append(" |\n")
			.append("\t| Description : ").append(this.description).append(" |");
		
		return builder.toString();
	}
	@Override
	public int compareTo(Publisher p) {
		return this.name.compareTo(p.getName());
	}

}
