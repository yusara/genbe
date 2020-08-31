package com.afdhal.genbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afdhal.genbe.model.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
