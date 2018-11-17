package models;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@NamedQuery(name = "Person.fetchAll", query = "from Person")
public class Person extends BaseEntity {

	@Column(nullable = false)
	private String name;

	private int age;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "pinCode", column = @Column(name = "zip_code")) })
	private Address address;

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn
	@ElementCollection
	private List<Vehicle> vehicles;

	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "person_accessory", joinColumns = { @JoinColumn(name = "person_id") }, inverseJoinColumns = {
			@JoinColumn(name = "accessory_id") })
	private List<Accessory> accessories;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<Accessory> getAccessories() {
		return accessories;
	}

	public void setAccessories(List<Accessory> accessories) {
		this.accessories = accessories;
	}

}
