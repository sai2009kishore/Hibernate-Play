package services;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import com.google.inject.Inject;

import models.Person;
import repositories.PersonRepository;

public class PersonService {

	private final PersonRepository personRepository;
	
	@Inject
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	public CompletionStage<Stream<Person>> list() {
		return personRepository.list();
	}

}
