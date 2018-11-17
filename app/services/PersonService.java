package services;

import com.google.inject.Inject;

import models.Person;
import repositories.PersonRepository;

public class PersonService extends AbstractService<Person> {

	private final PersonRepository personRepository;

	@Inject
	public PersonService(PersonRepository personRepository) {
		super(personRepository);
		this.personRepository = personRepository;
	}
}
