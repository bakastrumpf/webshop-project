package com.iktpreobuka.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.OfferEntity;

public interface OfferRepository extends CrudRepository <OfferEntity, Integer>{
	
	@Query("select o from OfferEntity o where o.regularPrice < :upperPrice and o.regularPrice > :lowerPrice")
	public List<OfferEntity> findByPriceBetween(Double lowerPrice, Double upperPrice);

}
