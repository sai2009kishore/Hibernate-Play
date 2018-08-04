package models;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
	public Long id;

    public String name;
    //comment
}
