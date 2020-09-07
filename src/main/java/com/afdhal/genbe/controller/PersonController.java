package com.afdhal.genbe.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.afdhal.genbe.model.dto.DataDto2;
import com.afdhal.genbe.model.dto.Status2;
import com.afdhal.genbe.repository.PendidikanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	
	@Autowired
	private PendidikanRepository pendidikanRepository;

	@Autowired
	private PersonService personService;

	@GetMapping("/{nik}")
	public List<Object> getByNik(@PathVariable String nik) {
		List<Object> values = new ArrayList<>();
		MessageDto statusDto = new MessageDto();
		Status2 status2 = new Status2();
		if (nik.length() == 16) {
			if (!personRepository.findByNikPerson(nik).isEmpty()) {
				Person person = personRepository.findByNikPerson(nik).get(0);
				Integer id = person.getIdPerson();
				Biodata biodata = biodataRepository.findAllByPersonIdPerson(id);
				DataDto2 dataDto2 = convertToDto(person, biodata);
				// set status message
				status2.setStatus(true);
				status2.setMessage("success");
				status2.setData(dataDto2);
				values.add(status2);
			} else {
				statusDto.setStatus(true);
				statusDto.setMessage("data dengan nik " + nik + " tidak ditemukan");
				values.add(statusDto);
			}
		} else {
			statusDto.setStatus(false);
			statusDto.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
			values.add(statusDto);
		}
		return values;

	}

	@PostMapping
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
			
			Boolean status = true;
			String message = "data berhasil masuk";
			return messages(message,status);
		}
		Boolean status = false;
		String message = "data gagal masuk, jumlah digit nik tidak sama dengan 16";
		return messages(message,status);
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

//	private PersonDto convertToDto(Person person) {
//		PersonDto dto = new PersonDto();
////		dto.setIdPerson(person.getIdPerson());
//		dto.setNik(person.getNikPerson());
//		dto.setName(person.getNamaPerson());
//		dto.setAddress(person.getAlamatPerson());
//		return dto;
//	}


	
	private MessageDto messages(String message, Boolean status) {
		MessageDto messages = new MessageDto();
		
		messages.setStatus(status);
		messages.setMessage(message);
		return messages;
	}

	private DataDto2 convertToDto(Person person, Biodata biodata) {
		DataDto2 dataDto2 = new DataDto2();
		Integer id = person.getIdPerson();
		dataDto2.setNik(person.getNikPerson());
		dataDto2.setName(person.getNamaPerson());
		dataDto2.setAddress(person.getAlamatPerson());
		dataDto2.setHp(biodata.getHpBio());

		// convert date to String
//		DateFormat format = new SimpleDateFormat("dd-MMMMMMMMM-yyyy");
//		String date = format.format(biodata.getTglBio());
		dataDto2.setTgl(biodata.getTglBio());
		dataDto2.setTempatLahir(biodata.getTtlBio());

		// set Umur
//		LocalDate birthYear = biodata.getTglBio().toInstant().atZone(ZoneId.systemDefault())
//				.toLocalDate();
		LocalDate now = LocalDate.now();
		Date birthSqlDate = biodata.getTglBio();
		LocalDate birthDate = birthSqlDate.toLocalDate();
		LocalDate dateNow = LocalDate.now();
		Period p = Period.between(birthDate, dateNow);
		int umur = p.getYears();

		dataDto2.setUmur(umur);
		dataDto2.setPendidikanTerakhir(pendidikanRepository.cariPendidikanTerakhir(id));
		return dataDto2;
	}


}
