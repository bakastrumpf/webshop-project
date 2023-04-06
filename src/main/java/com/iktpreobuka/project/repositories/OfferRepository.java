package com.iktpreobuka.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.iktpreobuka.project.entities.OfferEntity;

public interface OfferRepository extends CrudRepository <OfferEntity, Integer>{
	
	public List<OfferEntity> findByPriceBetween(double lowerPrice, double upperPrice);

}
