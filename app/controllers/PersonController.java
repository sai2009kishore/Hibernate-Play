package controllers;

import static play.libs.Json.toJson;

import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jpa.PersonRepository;
import models.Person;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
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
            return redirect(routes.PersonController.index());
        }, ec.current());
    }

    public CompletionStage<Result> getPersons() {
    	CompletionStage<Stream<Person>> stream = personService.list();
        return stream.thenApplyAsync(personStream -> {
            return ok(toJson(personStream.collect(Collectors.toList())));
        }, ec.current());
    }

}
