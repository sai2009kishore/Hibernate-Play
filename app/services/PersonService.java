package services;

import java.util.List;

import com.google.inject.Inject;

import models.Person;
import repositories.PersonRepository;

public class PersonService {

	private final PersonRepository personRepository;
	
	@Inject
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	public List<Person> list(Integer id) {
		return personRepository.list(id);
	}

}
