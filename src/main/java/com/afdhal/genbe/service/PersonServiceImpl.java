package com.afdhal.genbe.service;

import com.afdhal.genbe.model.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.afdhal.genbe.repository.*;

import javax.transaction.Transactional;
	
@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
    private BiodataRepository biodataRepository;
    
	@Autowired
	private PersonRepository personRepository;

	@Override
	public Person getMessage() {
		Person person = new Person();
		return person;
	}
	
	@Override
	public Person savePersonMaterBiodata(Person person) {
//		biodataReposi/tory.save(person.getBiodata());
		personRepository.save(person);
		return person;
	}
	
}
