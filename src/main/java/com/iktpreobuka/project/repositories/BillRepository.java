package com.iktpreobuka.project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.BillEntity;

	// TODO 3.2 u paketu com.iktpreobuka.project.repositories napraviti interfejs BillRepository

public interface BillRepository extends CrudRepository <BillEntity, Integer> {
	
	public List<BillEntity> findByBuyerId(Integer buyerId);
	
	public List<BillEntity> findByOfferCategoryId(Integer categoryId);
	public List<BillEntity> findAllByOfferCategoryId(Integer categoryId);
	
	public List<BillEntity> findByBillCreatedBetween(LocalDate startDate, LocalDate endDate);

}
