	package com.afdhal.genbe.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_person")
	private Integer idPerson;

	@Column(name = "nik")
	private String nikPerson;

	@Column(name = "nama")
	private String namaPerson;

	@Column(name = "alamat")
	private String alamatPerson;
	
	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	public String getNikPerson() {
		return nikPerson;
	}

	public void setNikPerson(String nikPerson) {
		this.nikPerson = nikPerson;
	}

	public String getNamaPerson() {
		return namaPerson;
	}

	public void setNamaPerson(String namaPerson) {
		this.namaPerson = namaPerson;
	}

	public String getAlamatPerson() {
		return alamatPerson;
	}

	public void setAlamatPerson(String alamatPerson) {
		this.alamatPerson = alamatPerson;
	}
	
}
