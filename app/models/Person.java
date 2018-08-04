package models;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;

    public String name;
}
