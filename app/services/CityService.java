package services;

import com.google.inject.Inject;

import models.City;
import repositories.CityRepository;

public class CityService extends AbstractService<City> {

	private final CityRepository cityRepository;

	@Inject
	public CityService(CityRepository cityRepository) {
		super(cityRepository);
		this.cityRepository = cityRepository;
	}
}
