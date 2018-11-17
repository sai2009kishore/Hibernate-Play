package controllers;

import static play.libs.Json.toJson;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.AbstractService;

public class AbstractController<T> extends Controller {

	private final ObjectMapper mapper = new ObjectMapper();
	private final AbstractService<T> service;
	Class<T> tClass;

	@Inject
	public AbstractController(AbstractService<T> service, Class<T> tClass) {
		this.service = service;
		this.tClass = tClass;
	}

	public T asJson(JsonNode jsonNode) throws JsonProcessingException {
		return mapper.treeToValue(jsonNode, tClass);
	}

	@Transactional
	public Result executeNamedQuery(String name) {
		return ok(toJson(service.executeNamedQuery(name)));
	}
}
