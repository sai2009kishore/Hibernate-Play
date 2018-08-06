package controllers;

import static play.libs.Json.toJson;

import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.City;
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
			return redirect(routes.PersonController.index());
		}, ec.current());
	}

	public CompletionStage<Result> getCities() {
		CompletionStage<Stream<City>> stream = cityService.list();
		return stream.thenApplyAsync(cityStream -> {
			return ok(toJson(cityStream.collect(Collectors.toList())));
		}, ec.current());
	}

}
