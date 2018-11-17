package controllers;

import static constants.MessageConstants.DELETED_SUCCESSFULLY;
import static constants.MessageConstants.NO_RECORDS_FOUND;
import static play.libs.Json.toJson;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import models.Accessory;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.AccessoryService;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and
 * {@code flash()}.
 */
public class AccessoryController extends BaseController<Accessory> {

	private final AccessoryService accessoryService;

	@Inject
	public AccessoryController(AccessoryService accessoryService) {
		super(Accessory.class);
		this.accessoryService = accessoryService;
	}

	@Transactional
	public Result addAccessory() throws JsonProcessingException {
		try {
			Accessory accessory = asJson(request().body().asJson());
			return ok(toJson(accessoryService.add(accessory)));
		} catch (Exception e) {
			return badRequest(ExceptionUtils.getRootCauseMessage(e));
		}
	}

	@Transactional
	public Result getAccessories() {
		return ok(toJson(accessoryService.list(null)));
	}

	@Transactional
	public Result getAccessoryById(Integer id) {
		return ok(toJson(accessoryService.list(id)));
	}

	@Transactional
	public Result deleteAccessory(Integer id) {
		int deleted = accessoryService.delete(id);
		if (deleted > 0) {
			return ok(toJson(DELETED_SUCCESSFULLY));
		} else {
			return badRequest(NO_RECORDS_FOUND);
		}
	}
}
