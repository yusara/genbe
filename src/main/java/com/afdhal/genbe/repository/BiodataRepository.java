package com.afdhal.genbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afdhal.genbe.model.entity.Biodata;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, Integer> {
    Biodata findAllByPersonIdPerson(Integer idPerson);
}
