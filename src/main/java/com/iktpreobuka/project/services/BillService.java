package com.iktpreobuka.project.services;

import java.time.LocalDate;
import java.util.List;

import com.iktpreobuka.project.entities.BillEntity;

public interface BillService {
	
	public BillEntity addBill(BillEntity bill, Integer offerId, Integer buyerId);
	
	public BillEntity updateBill(Integer id, BillEntity bill);

	boolean areActiveBills(Integer categoryId);

	boolean activeBills();

	boolean areBillsActiveByCategory(Integer categoryId);

	public List<BillEntity> getAllBillsByDate(LocalDate startDate, LocalDate endDate);

	public List<BillEntity> findAllByOfferCategoryId(Integer categoryId);

	public List<BillEntity> findAllBillsByBuyer(Integer buyerId);

}
