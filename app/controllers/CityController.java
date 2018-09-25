package controllers;

import static constants.MessageConstants.DELETED_SUCCESSFULLY;
import static constants.MessageConstants.NO_RECORDS_FOUND;
import static play.libs.Json.toJson;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import models.City;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.CityService;

public class CityController extends BaseController<City> {

	private final CityService cityService;

	@Inject
	public CityController(CityService cityService) {
		super(City.class);
		this.cityService = cityService;
	}

	@Transactional
	public Result addCity() throws JsonProcessingException {
		City city = asJson(request().body().asJson());
		try {
			return ok(toJson(cityService.add(city)));
		} catch (Exception e) {
			return badRequest(ExceptionUtils.getRootCauseMessage(e));
		}
	}

	@Transactional
	public Result getCities() {
		return ok(toJson(cityService.list(null)));
	}

	@Transactional
	public Result getCityById(Integer id) {
		return ok(toJson(cityService.list(id)));
	}

	@Transactional
	public Result deleteCity(Integer id) {
		int deleted = cityService.delete(id);
		if (deleted > 0) {
			return ok(toJson(DELETED_SUCCESSFULLY));
		} else {
			return badRequest(NO_RECORDS_FOUND);
		}
	}

}
