package controllers;

import static constants.MessageConstants.DELETED_SUCCESSFULLY;
import static constants.MessageConstants.NO_RECORDS_FOUND;
import static play.libs.Json.toJson;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import models.Vehicle;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.VehicleService;

public class VehicleController extends AbstractController<Vehicle> {

	private final VehicleService vehicleService;

	@Inject
	public VehicleController(VehicleService vehicleService) {
		super(vehicleService, Vehicle.class);
		this.vehicleService = vehicleService;
	}

	public Result index() {
		return ok(views.html.index.render());
	}

	@Transactional
	public Result addVehicle() throws JsonProcessingException {
		try {
			Vehicle vehicle = asJson(request().body().asJson());
			return ok(toJson(vehicleService.add(vehicle)));
		} catch (Exception e) {
			return badRequest(ExceptionUtils.getRootCauseMessage(e));
		}
	}

	@Transactional
	public Result getVehicles() {
		return ok(toJson(vehicleService.list(null)));
	}

	@Transactional
	public Result getVehicleById(Integer id) {
		return ok(toJson(vehicleService.list(id)));
	}

	@Transactional
	public Result deleteVehicle(Integer id) {
		int deleted = vehicleService.delete(id);
		if (deleted > 0) {
			return ok(toJson(DELETED_SUCCESSFULLY));
		} else {
			return badRequest(NO_RECORDS_FOUND);
		}
	}
}
