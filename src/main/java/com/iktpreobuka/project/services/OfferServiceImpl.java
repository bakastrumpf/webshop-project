package com.iktpreobuka.project.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.repositories.CategoryRepository;
import com.iktpreobuka.project.repositories.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public void setNumberOfBoughtAndAvailableOffersByPaymentCancelled(Integer id, boolean paymentCancelled) {
		// VP
		OfferEntity offer = offerRepository.findById(id).orElse(null);
		if (offer == null) {
			return;
		}
		if (paymentCancelled) {
			offer.setBoughtOffers(offer.getBoughtOffers() -1);
			offer.setAvailableOffers(offer.getAvailableOffers() +1);
		}
	}

	@Override
	public void setNumberOfBoughtAndAvailableOffersByPaymentMade(Integer id, boolean paymentMade) {
		// VP
		OfferEntity offer = offerRepository.findById(id).orElse(null);
		if (offer == null) {
			return;
		}
		if (paymentMade) {
			offer.setBoughtOffers(offer.getBoughtOffers() +1);
			offer.setAvailableOffers(offer.getAvailableOffers() -1);
		}
	}
	
	
	@Override
	public boolean hasCategoryNonExpirationOffers(Integer categoryId) {
		
		// VP
//		List<OfferEntity> offers = offerRepository.findAllByCategoryId(categoryId);
//		LocalDate currentDate = LocalDate.now();
//		for(OfferEntity offer : offers) {
//			if(offer.getOfferExpires().isAfter(currentDate)) {
//				return true;
//			}
//		}
//		return false;

		return false;		
	}

	// DB
	@Override
	public OfferEntity changeAvailableBoughtOfferBuy(Integer id) {
		if (!offerRepository.existsById(id))
			return null;
		OfferEntity offer = offerRepository.findById(id).get();
		offer.setAvailableOffers(offer.getAvailableOffers() -1);
		offer.setBoughtOffers(offer.getBoughtOffers() +1);
		offerRepository.save(offer);
		return offer;
	}

	// DB
	@Override
	public OfferEntity changeAvailableBoughtOfferCancelled(Integer id) {
		if (!offerRepository.existsById(id))
			return null;
		OfferEntity offer = offerRepository.findById(id).get();
		offer.setAvailableOffers(offer.getAvailableOffers() +1);
		offer.setBoughtOffers(offer.getBoughtOffers() -1);
		offerRepository.save(offer);
		return offer;
	}

	@Override
	public boolean isOfferExpires(Integer categoryId) {
		if(categoryRepository.existsById(categoryId)) {
			List<OfferEntity> offers = offerRepository.findAllByCategoryId(categoryId);
			LocalDate now = LocalDate.now();
			for (OfferEntity offer : offers) {
				if (offer.getOfferExpires().isAfter(now))
					return true;					
				return false;
			}
		}
		return false;
	}
	
	
	// TODO • 3.3 ukoliko se ponuda proglasi isteklom potrebno je otkazati sve račune koji sadrže tu ponudu
	// • u okviru servisa zaduženog za rad sa računima napisati metodu koja otkazuje sve račune odgovarajuće ponude
	// • pozvati je u okviru metode za promenu statusa ponude u	OfferController-u

}
