package controllers;

import static play.libs.Json.toJson;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Person;
import play.db.jpa.Transactional;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.PersonRepository;
import services.PersonService;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and {@code flash()}.
 */
public class PersonController extends Controller {

    private final PersonRepository personRepository;
    private final HttpExecutionContext ec;
    private final PersonService personService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Inject
    public PersonController(PersonService personService, PersonRepository personRepository, HttpExecutionContext ec) {
        this.personRepository = personRepository;
        this.personService = personService;
        this.ec = ec;
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public CompletionStage<Result> addPerson() throws JsonProcessingException {
    	Person person = mapper.treeToValue(request().body().asJson(), Person.class);
        return personRepository.add(person).thenApplyAsync(p -> {
            return ok("Person Added Successfully");
        }, ec.current()).exceptionally(e -> badRequest(e.getMessage()));
    }

    @Transactional
    public Result getPersons() {
    	return ok(toJson(personService.list(null)));
    }

    @Transactional
    public Result getPersonById(Integer id) {
    	return ok(toJson(personService.list(id)));
    }
}
