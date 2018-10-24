package repositories;

import javax.inject.Named;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import models.Person;
import play.db.jpa.JPAApi;

@Named
@Singleton
public class PersonRepository extends AbstractRepository<Person> {

	@Inject
	public PersonRepository(JPAApi jpaApi) {
		super(jpaApi, Person.class);
	}
}
