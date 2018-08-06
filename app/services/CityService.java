package services;

import java.util.List;

import com.google.inject.Inject;

import models.City;
import repositories.CityRepository;

public class CityService {

	private final CityRepository cityRepository;
	
	@Inject
	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}
	public List<City> list() {
		return cityRepository.list(null);
	}
}
