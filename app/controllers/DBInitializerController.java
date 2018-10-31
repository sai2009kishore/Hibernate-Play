package controllers;

import javax.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.DBInitializerService;

public class DBInitializerController extends Controller {

	private DBInitializerService service;

	@Inject
	public DBInitializerController(DBInitializerService service) {
		this.service = service;
	}

	@Transactional
	public Result init(Boolean drop) {
		return ok(service.initializeDB(drop));
	}
}
