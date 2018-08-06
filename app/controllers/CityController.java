package controllers;

import static play.libs.Json.toJson;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.City;
import play.db.jpa.Transactional;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.CityRepository;
import services.CityService;

public class CityController extends Controller {

	private final CityRepository cityRepository;
	private final HttpExecutionContext ec;
	private final CityService cityService;
	private final ObjectMapper mapper = new ObjectMapper();

	@Inject
	public CityController(CityService cityService, CityRepository cityRepository, HttpExecutionContext ec) {
		this.cityRepository = cityRepository;
		this.cityService = cityService;
		this.ec = ec;
	}

	public Result index() {
		return ok(views.html.index.render());
	}

	public CompletionStage<Result> addCity() throws JsonProcessingException {
		City city = mapper.treeToValue(request().body().asJson(), City.class);
		return cityRepository.add(city).thenApplyAsync(p -> {
			return ok("City Added Successuflly");
		}, ec.current()).exceptionally(e -> badRequest(e.getMessage()));
	}

    @Transactional
    public Result getCities() {
    	return ok(toJson(cityService.list()));
    }

}
