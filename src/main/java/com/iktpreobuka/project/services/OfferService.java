package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.OfferEntity;

public interface OfferService {
	
	public void setNumberOfBoughtAndAvailableOffersByPaymentCancelled (Integer id, boolean paymentCancelled);
	
	public void setNumberOfBoughtAndAvailableOffersByPaymentMade (Integer id, boolean paymentMade);

	boolean hasCategoryNonExpirationOffers(Integer categoryId);
	
	public OfferEntity changeAvailableBoughtOfferBuy(Integer id);
	
	public OfferEntity changeAvailableBoughtOfferCancelled(Integer id);
	
	public boolean isOfferExpires(Integer categoryId);

}
