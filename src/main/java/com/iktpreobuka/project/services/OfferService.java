package com.iktpreobuka.project.services;

public interface OfferService {
	
	public void setNumberOfBoughtAndAvailableOffersByPaymentCancelled (Integer id, boolean paymentCancelled);
	
	public void setNumberOfBoughtAndAvailableOffersByPaymentMade (Integer id, boolean paymentMade);

}
