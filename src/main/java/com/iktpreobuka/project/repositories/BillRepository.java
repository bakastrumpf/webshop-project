package com.iktpreobuka.project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.BillEntity;

	// TODO 3.2 u paketu com.iktpreobuka.project.repositories napraviti interfejs BillRepository

public interface BillRepository extends CrudRepository <BillEntity, Integer> {

}
