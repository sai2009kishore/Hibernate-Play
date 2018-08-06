package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @Column(nullable = false)
    private String name;
    
    private int age;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private City city;
    
    // Setters
    public void setId(int id) {
    	this.id = id;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setAge(int age) {
    	this.age = age;
    }
    
    public void setCity(City city) {
    	this.city = city;
    }
    
    // Getters
    public int getId() {
    	return this.id;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public int getAge() {
    	return this.age;
    }
    
    public City getCity() {
    	return this.city;
    }
}
