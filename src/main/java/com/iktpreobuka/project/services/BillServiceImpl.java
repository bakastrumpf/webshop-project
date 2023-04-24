package com.iktpreobuka.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.repositories.BillRepository;
import com.iktpreobuka.project.repositories.OfferRepository;
import com.iktpreobuka.project.repositories.UserRepository;

import jakarta.validation.Validation;

@Service
public class BillServiceImpl implements BillService {
	
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
	}
	
	// TODO • 2.1 u servisu zaduženom za rad sa ponudama, napisati metodu koja za prosleđen ID ponude, vrši izmenu broja kupljenih/dostupnih ponuda

	
	// TODO • 2.4 u servisu zaduženom za rad sa računima, napisati metodu koja za prosleđene datume vraća račune koji se nalaze u datom periodu
	// • pozvati je u okviru metode BillController-a za pronalazak svih računa koji su kreirani u odgovarajućem vremenskom periodu
	
	
}
