package services;

import com.google.inject.Inject;

import models.Vehicle;
import repositories.VehicleRepository;

public class VehicleService extends AbstractService<Vehicle> {

	private final VehicleRepository vehicleRepository;

	@Inject
	public VehicleService(VehicleRepository vehicleRepository) {
		super(vehicleRepository);
		this.vehicleRepository = vehicleRepository;
	}
}
