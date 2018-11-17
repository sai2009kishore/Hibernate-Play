package controllers;

import java.util.HashMap;
import java.util.Map;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	public Result index() {
		Map<String, String> status = new HashMap<>();
		status.put("status", "up");
		return ok(Json.toJson(status));
	}
}
