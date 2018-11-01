package repositories;

import javax.inject.Named;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import models.Vehicle;
import play.db.jpa.JPAApi;

@Named
@Singleton
public class VehicleRepository extends AbstractRepository<Vehicle> {

	@Inject
	public VehicleRepository(JPAApi jpaApi) {
		super(jpaApi, Vehicle.class);
	}
}
