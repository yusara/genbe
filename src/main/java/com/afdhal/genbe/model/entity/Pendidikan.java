package com.afdhal.genbe.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_pendidikan")
public class Pendidikan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_pendidikan")
	private Integer idPend;

	@Column(name = "idperson")
	private Integer idPerson;

	@Column(name = "jenjang")
	private String jenjangPend;

	@Column(name = "institusi")
	private String institusiPend;

	@Column(name = "tahunmasuk")
	private String masukPend;

	@Column(name = "tahunlulus")
	private String lulusPend;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
	public Integer getIdPend() {
		return idPend;
	}

	public void setIdPend(Integer idPend) {
		this.idPend = idPend;
	}

	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	public String getJenjangPend() {
		return jenjangPend;
	}

	public void setJenjangPend(String jenjangPend) {
		this.jenjangPend = jenjangPend;
	}

	public String getInstitusiPend() {
		return institusiPend;
	}

	public void setInstitusiPend(String institusiPend) {
		this.institusiPend = institusiPend;
	}

	public String getMasukPend() {
		return masukPend;
	}

	public void setMasukPend(String masukPend) {
		this.masukPend = masukPend;
	}

	public String getLulusPend() {
		return lulusPend;
	}

	public void setLulusPend(String lulusPend) {
		this.lulusPend = lulusPend;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	

	
}
