package com.afdhal.genbe.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "t_biodata")
public class Biodata {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_bio", nullable = false )
	private Integer idBio;

	@Column(name = "nohp")
	private String hpBio;

	@Column(name = "tanggal_lahir")
	private Date tglBio;

	@Column(name = "tempat_lahir")
	private String ttlBio;
	
	@OneToOne
    @JoinColumn(name = "id_person", nullable = false)
    private Person person;

	public Integer getIdBio() {
		return idBio;
	}

	public void setIdBio(Integer idBio) {
		this.idBio = idBio;
	}

	public String getHpBio() {
		return hpBio;
	}

	public void setHpBio(String hpBio) {
		this.hpBio = hpBio;
	}

	public Date getTglBio() {
		return tglBio;
	}

	public void setTglBio(Date tglBio) {
		this.tglBio = tglBio;
	}

	public String getTtlBio() {
		return ttlBio;
	}

	public void setTtlBio(String ttlBio) {
		this.ttlBio = ttlBio;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	
}
