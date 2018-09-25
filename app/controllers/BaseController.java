package controllers;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.mvc.Controller;

public class BaseController<T> extends Controller {

	private final ObjectMapper mapper = new ObjectMapper();
	Class<T> tClass;

	@Inject
	public BaseController(Class<T> tClass) {
		this.tClass = tClass;
	}

	public T asJson(JsonNode jsonNode) throws JsonProcessingException {
		return mapper.treeToValue(jsonNode, tClass);
	}
}
