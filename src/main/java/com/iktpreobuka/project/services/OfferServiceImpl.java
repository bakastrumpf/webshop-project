package com.iktpreobuka.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.repositories.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {
	
	@Autowired
	OfferRepository offerRepository;

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
	
	// TODO • 3.3 ukoliko se ponuda proglasi isteklom potrebno je otkazati sve račune koji sadrže tu ponudu
	// • u okviru servisa zaduženog za rad sa računima napisati metodu koja otkazuje sve račune odgovarajuće ponude
	// • pozvati je u okviru metode za promenu statusa ponude u	OfferController-u

}
