package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accessory")
public class Accessory extends BaseEntity {

	@Column(name = "accessory_type")
	private String accessoryType;

	@ManyToMany(mappedBy = "accessories")
	@JsonIgnore
	private List<Person> persons;

	public String getAccessoryType() {
		return accessoryType;
	}

	public void setAccessoryType(String accessoryType) {
		this.accessoryType = accessoryType;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
