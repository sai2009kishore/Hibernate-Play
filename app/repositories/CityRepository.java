package repositories;

import com.google.inject.Inject;

import jpa.DatabaseExecutionContext;
import models.City;
import play.db.jpa.JPAApi;

public class CityRepository  extends JPARepository<City> {

	@Inject
	public CityRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, City.class);
	}
}
