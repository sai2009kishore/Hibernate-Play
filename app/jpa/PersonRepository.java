package jpa;

import javax.inject.Named;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import models.Person;
import play.db.jpa.JPAApi;

@Named
@Singleton
public class PersonRepository extends JPARepository<Person> {

	@Inject
	public PersonRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Person.class);
	}
}
