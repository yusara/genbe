package com.afdhal.genbe.controller;

import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afdhal.genbe.model.dto.MessageDto;
import com.afdhal.genbe.model.dto.PersonDto;
import com.afdhal.genbe.model.entity.Biodata;
import com.afdhal.genbe.model.entity.Person;
import com.afdhal.genbe.repository.BiodataRepository;
import com.afdhal.genbe.repository.PersonRepository;
import com.afdhal.genbe.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private BiodataRepository biodataRepository;
	
	
//	@Autowired
//    private ModelMapper modelMapper;

	@Autowired
	private PersonService personService;

	
	
//	@Autowired
//	public PersonController(PersonRepository personRepository, BiodataRepository biodataRepository) {
//		this.personRepository = personRepository;
//		this.biodataRepository = biodataRepository;
//	}

	@GetMapping
	public List<PersonDto> get() {
		List<Person> personList = personRepository.findAll();
		List<PersonDto> kategoriDtoList = personList.stream().map(this::convertToDto).collect(Collectors.toList());
		return kategoriDtoList;
	}
	
//	@GetMapping("/{nik}")
//	public MessageDto getByNik(@PathVariable ) {
//		
//		
//		String status = "true";
//		String message = "success";
//		return messages(status,message);
//	}
	
	@PostMapping
	public PersonDto insertPerson(@RequestBody PersonDto personDto) {
		Person person = convertToEntityPerson(personDto);
		personRepository.save(person);
		return convertToDto(person);
	}
	
	@PostMapping("/input")
	public MessageDto insert(@RequestBody PersonDto personDto) {
//		MessageDto message = new MessageDto();
		LocalDate now = LocalDate.now();
		Date birthSqlDate = personDto.getTgl();
		LocalDate birthDate = birthSqlDate.toLocalDate();
		Period age = Period.between(birthDate,now);
		
		if (personDto.getNik().length() == 16 && age.getYears()<30) {
			Person person = convertToEntityPerson(personDto);
			personRepository.save(person);
			
			Biodata biodata = convertToEntityBiodata(personDto,person.getIdPerson());
			biodataRepository.save(biodata);
			
			String status = "true";
			String message = "data berhasil masuk";
			return messages(status,message);
		}
		String status = "false";
		String message = "data gagal masuk, jumlah digit nik tidak sama dengan 16";
		return messages(status,message);
	}

	private Person convertToEntityPerson(PersonDto dtoPerson) { 
		Person person = new Person();
//		person.setIdPerson(dtoPerson.getIdPerson());
		person.setNikPerson(dtoPerson.getNik());
		person.setNamaPerson(dtoPerson.getName());
		person.setAlamatPerson(dtoPerson.getAddress());
		return person;
	}
	
	private Biodata convertToEntityBiodata(PersonDto dtoPerson, Integer idPerson) {		
		Biodata biodata = new Biodata();
		biodata.setHpBio(dtoPerson.getHp());
		biodata.setTglBio(dtoPerson.getTgl());
		biodata.setTtlBio(dtoPerson.getTempatLahir());
		
		if (personRepository.findById(idPerson).isPresent()) { 
			Person person = personRepository.findById(idPerson).get();
			biodata.setPerson(person);
		}

		return biodata;
	}

	private PersonDto convertToDto(Person person) {
		PersonDto dto = new PersonDto();
//		dto.setIdPerson(person.getIdPerson());
		dto.setNik(person.getNikPerson());
		dto.setName(person.getNamaPerson());
		dto.setAddress(person.getAlamatPerson());
		return dto;
	}
	
	private MessageDto messages(String message, String status) {
		MessageDto messages = new MessageDto();
		
		messages.setStatus(status);
		messages.setMessages(message);
		return messages;
	}
	
}
