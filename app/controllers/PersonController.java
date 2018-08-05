package controllers;

import static play.libs.Json.toJson;

import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jpa.JPAPersonRepository;
import models.Person;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and {@code flash()}.
 */
public class PersonController extends Controller {

    private final JPAPersonRepository jpaPersonRepository;
    private final HttpExecutionContext ec;
    private final ObjectMapper mapper = new ObjectMapper();

    @Inject
    public PersonController(JPAPersonRepository jpaPersonRepository, HttpExecutionContext ec) {
        this.jpaPersonRepository = jpaPersonRepository;
        this.ec = ec;
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public CompletionStage<Result> addPerson() throws JsonProcessingException {
    	Person person = mapper.treeToValue(request().body().asJson(), Person.class);
        return jpaPersonRepository.add(person).thenApplyAsync(p -> {
            return redirect(routes.PersonController.index());
        }, ec.current());
    }

    public CompletionStage<Result> getPersons() {
        return jpaPersonRepository.list().thenApplyAsync(personStream -> {
            return ok(toJson(personStream.collect(Collectors.toList())));
        }, ec.current());
    }

}
