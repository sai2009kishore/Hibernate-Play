package repositories;

import com.google.inject.Inject;

import models.City;
import play.db.jpa.JPAApi;

public class CityRepository extends AbstractRepository<City> {

	@Inject
	public CityRepository(JPAApi jpaApi) {
		super(jpaApi, City.class);
	}
}
