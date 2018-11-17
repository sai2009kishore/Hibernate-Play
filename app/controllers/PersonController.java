package controllers;

import static constants.MessageConstants.DELETED_SUCCESSFULLY;
import static constants.MessageConstants.NO_RECORDS_FOUND;
import static play.libs.Json.toJson;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import models.Person;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.PersonService;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and
 * {@code flash()}.
 */
public class PersonController extends AbstractController<Person> {

	private final PersonService personService;

	@Inject
	public PersonController(PersonService personService) {
		super(personService, Person.class);
		this.personService = personService;
	}

	@Transactional
	public Result addPerson() throws JsonProcessingException {
		try {
			Person person = asJson(request().body().asJson());
			return ok(toJson(personService.add(person)));
		} catch (Exception e) {
			return badRequest(ExceptionUtils.getRootCauseMessage(e));
		}
	}

	@Transactional
	public Result getPersons() {
		return ok(toJson(personService.list(null)));
	}

	@Transactional
	public Result getPersonById(Integer id) {
		return ok(toJson(personService.list(id)));
	}

	@Transactional
	public Result updatePerson(Integer id) {
		return ok(toJson(personService.update(id)));
	}

	@Transactional
	public Result deletePerson(Integer id) {
		int deleted = personService.delete(id);
		if (deleted > 0) {
			return ok(toJson(DELETED_SUCCESSFULLY));
		} else {
			return badRequest(NO_RECORDS_FOUND);
		}
	}
}
