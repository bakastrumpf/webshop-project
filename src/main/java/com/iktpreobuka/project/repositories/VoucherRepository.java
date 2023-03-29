package com.iktpreobuka.project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.VoucherEntity;

// TODO 4.2 • 4.2 u paketu com.iktpreobuka.project.repositories napraviti interfejs VoucherRepository

public interface VoucherRepository extends CrudRepository<VoucherEntity, Integer>{

}
