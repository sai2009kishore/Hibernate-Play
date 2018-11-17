package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import enums.VehicleType;

@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {

	@Column(name = "vehicle_type")
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;

	public Vehicle() {

	}

	public Vehicle(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
}
