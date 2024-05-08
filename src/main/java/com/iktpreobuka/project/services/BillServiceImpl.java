package com.iktpreobuka.project.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iktpreobuka.project.controllers.utils.RESTError;
import com.iktpreobuka.project.entities.dto.BillDTO;
import com.iktpreobuka.project.entities.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.repositories.BillRepository;
import com.iktpreobuka.project.repositories.OfferRepository;
import com.iktpreobuka.project.repositories.UserRepository;

import jakarta.validation.Validation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BillServiceImpl implements BillService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	BillRepository billRepository;
	
	@Autowired 
	OfferRepository offerRepository;
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public BillEntity addBill(BillEntity bill, Integer offerId, Integer buyerId) {
		
		// VP
		
//		OfferEntity offer = Validation.validateEntity(offerId, offerRepository);
//		UserEntity buyer = Validation.validateEntity(buyerId, userRepository);
//		
//		bill.setOffer(offer);
//		bill.setBuyer(buyer);
//		
//		billRepository.save(bill);
//		
//		offerService.setNumberOfBoughtAndAvailableOffersByPaymentCancelled(offer.getId(), bill.getPaymentMade());
//		
//		offerRepository.save(offer);
//		
//		return bill;
		
		return null;
	}

	@Override
	public BillEntity updateBill(Integer id, BillEntity bill) {
		
		// VP
		
//		BillEntity billDB = Validation.validateEntity(id, billRepository);
//		
//		billDB.setBillCreated(Validation.setIfNotNUll(billDB.getBillCreated(), bill.getBillCreated()));
//		billDB.setBuyer(Validation.setIfNotNUll(billDB.getBuyer(), bill.getBuyer()));
//		billDB.setOffer(Validation.setIfNotNull(billDB.getOffer(), bill.getOffer()));
//		billDB.setPaymentCancelled(Validation.setIfNotNull(billDB.getPaymentCancelled(), bill.getPaymentCancelled()));
//		billDB.setPaymentMade(Validation.setIfNotNull(billDB.getPaymentMade(), bill.getPaymentMade()));
//		billDB.setVersion(Validation.setIfNotNull(billDB.getVersion(), bill.getVersion()));
//		
//		billRepository.save(billDB);
//		
//		offerService.setNumberOfBoughtAndAvailableOffersByPaymentCancelled(bill.getOffer().getId(), bill.getPaymentCancelled());
//		offerService.setNumberOfBoughtAndAvailableOffersByPaymentMade(id, bill.getPaymentMade());
//		
//		offerRepository.save(billDB.getOffer());
//		
//		return billDB;
		
		return null;
	}
	

	@Override
	public boolean areActiveBills(Integer categoryId) {
		
		// VP
		
		List<BillEntity> bills = billRepository.findByOfferCategoryId(categoryId);
		for(BillEntity bill : bills) {
			if(!bill.getPaymentMade() && !bill.getPaymentCancelled()) {
				return true;
			}
		}
		return false;

		// NM
		// List<BillEntity> bills = billRepository.findByOfferCategoryId(categoryId);
		// if (billRepository.findByOfferCategoryId(categoryId) != null)
		// for (BillEntity bill : bills) {
		// if (!bill.getPaymentMade() && !bill.getPaymentCancelled()) {
		// return true;
		// }
		// }
		// return false;
		// }

	}
	
	
	@Override
	public boolean activeBills() {
		String sql = "select b from BillEntity b where b.paymentMade=0 and b.paymentCancelled=0";
		Query query = em.createQuery(sql);
		List<BillEntity> retVals = query.getResultList();
		// return retVals;
		return true;
	}
	
	
	// da li postoje aktivni računi za datu kategoriju
	// SJ
	public boolean areBillsActiveByCategory(Integer categoryId) {
		List<BillEntity> bills = billRepository.findAllByOfferCategoryId(categoryId);
		for (BillEntity bill : bills) {
			if (!bill.getPaymentMade() && !bill.getPaymentCancelled()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<BillEntity> getAllBillsByDate(LocalDate startDate, LocalDate endDate) {
		return null;
	}

	@Override
	public List<BillEntity> findAllByOfferCategoryId(Integer categoryId) {
		return null;
	}

	@Override
	public List<BillEntity> findAllBillsByBuyer(Integer buyerId) {
		return null;
	}
	
	
	// TODO • 2.1 u servisu zaduženom za rad sa ponudama, napisati metodu koja
	//  za prosleđen ID ponude, vrši izmenu broja kupljenih/dostupnih ponuda


	public ResponseEntity<?> createBillWithOfferAndBuyer(@PathVariable Integer offerId,
														 @PathVariable Integer buyerId,
														 @DateTimeFormat(iso = ISO.DATE) @RequestBody BillDTO newBill,
														 BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		if (offerRepository.existsById(offerId)) {
			if (userRepository.existsById(buyerId)) {
				UserEntity user = userRepository.findById(buyerId).get();
				OfferEntity offer = offerService.changeAvailableOffers(offerId);
				BillEntity bill = new BillEntity();
				bill.setUser(user);
				bill.setOffer(offer);
				bill.setPaymentMade(true);
				bill.setPaymentCancelled(newBill.getPaymentMade());
				bill.setPaymentCancelled(newBill.getPaymentCancelled());
				bill.setBillCreated(newBill.getBillCreated());
				billRepository.save(bill);
				return new ResponseEntity<BillEntity>(bill, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Bill not found"), HttpStatus.NOT_FOUND);
	}

	private String createErrorMessage(BindingResult bindingResult) {
		return bindingResult.getAllErrors()
				.stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining(" \n"));
	}

	
	// TODO • 2.4 u servisu zaduženom za rad sa računima, napisati metodu koja
	//  za prosleđene datume vraća račune koji se nalaze u datom periodu
	// • pozvati je u okviru metode BillController-a
	// za pronalazak svih računa koji su kreirani u odgovarajućem vremenskom periodu
	

	@Override
	public List<BillEntity> cancelBillsWithExpiredOffer(Integer offerId) {
		List<BillEntity> bills = billRepository.findByOfferId(offerId);
		for (BillEntity bill : bills ) {
			bill.setPaymentCancelled(true);
			bill.setPaymentMade(false);
			billRepository.save(bill);
		}
		return bills;
	}

	// NM
	public ResponseEntity<?> generateReportByDate(@PathVariable String startDate, @PathVariable String endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate start = LocalDate.parse(startDate, formatter);
		LocalDate end = LocalDate.parse(endDate, formatter);
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setSumOfIncomes(0.00);
		reportDTO.setTotalNumberOfSoldOffers(0);
		reportDTO.setReportItemDTO(new ArrayList<ReportItem>());
		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
			ReportItem report = new ReportItem();
			List<BillEntity> bills = billRepository.findByBillCreated(date);
			report.setDate(date);
			int numOfOffers = 0;
			double income = 0;
			for (BillEntity bill : bills) {
				numOfOffers++;
				income += bill.getOffer().getDiscountPrice();
			}
			report.setNumberOfOffers(numOfOffers);
			report.setIncome(income);
			reportDTO.getReportItemDTO().add(Report);
			reportDTO.setSumOfIncomes(reportDTO.getSumOfIncomes() + reportDTO.getIncome());
			reportDTO.setTotalNumberOfSoldOffers(reportDTO.totalNumberOfSoldOffers) + report.getNumberOfOffers();
		}
		return new ResponseEntity<ReportDTO>(reportDTO, HttpStatus.OK);
	}
}
