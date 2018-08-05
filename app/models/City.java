package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {
	
	@Id
	private int id;
	
	private String name;
	
	// Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void getName(String name) {
		this.name = name;
	}
	
	// Getters
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}
