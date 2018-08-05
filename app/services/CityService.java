package services;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import com.google.inject.Inject;

import models.City;
import repositories.CityRepository;

public class CityService {

	private final CityRepository cityRepository;
	
	@Inject
	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}
	public CompletionStage<Stream<City>> list() {
		return cityRepository.list();
	}
}
