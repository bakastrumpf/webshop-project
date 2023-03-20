package com.iktpreobuka.project.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.project.entities.EOfferStatus;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.repositories.OfferRepository;


@RestController
@RequestMapping(path = "/project/offers")
public class OfferController {
	
	
	@Autowired
	private OfferRepository offerRepository;
	
	
	// TODO 3.2: metoda getDB() koja vraća listu svih ponuda
	private List<OfferEntity> getDB() {
		List<OfferEntity> offers = new ArrayList<OfferEntity>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 5);
		
//		OfferEntity o1 = new OfferEntity();
//		OfferEntity o2 = new OfferEntity();
//		OfferEntity o3 = new OfferEntity();
		
		/*
		 * OfferEntity(Integer id, String offerName, String offerDesc, LocalDate offerCreated, LocalDate offerExpires,
			double regularPrice, double discountPrice, String imagePath, Integer availableOffers, Integer boughtOffers,
			EOfferStatus offerStatus)
		 */
		
		OfferEntity o1 = new OfferEntity(1,"2 tickets for Killers concert", "Enjoy!!!", new Date(),cal.getTime(),100000.00, 6500.00, " ", 10, 0, EOfferStatus.WAIT_FOR_APPROVING);
		OfferEntity o2 = new OfferEntity(2, "VIVAX 24LE76T2", "Don't miss this fantastic offer!", new Date(),cal.getTime(), 200000.00, 16500.00, " ", 5, 0, EOfferStatus.WAIT_FOR_APPROVING);
		OfferEntity o3 = new OfferEntity(3, "Dinner for two in Aqua Doria", "Excellent offer", new Date(), cal.getTime(), 6000.00, 3500.00, " ", 4, 0, EOfferStatus.WAIT_FOR_APPROVING);
		
		offers.add(o1);
		offers.add(o2);
		offers.add(o3);
		return offers;
		}
	

	
	// TODO 3.3: REST endpoint koji vraća listu svih ponuda
	// putanja: /project/offers
	@RequestMapping(method = RequestMethod.GET, path = "/getAll")
	public List<OfferEntity> getAll(){
		return (List<OfferEntity>) offerRepository.findAll();
	}
	


	// TODO 3.4: REST endpoint koji omogućava dodavanje nove ponude
	// putanja: /project/offers
	// vraća dodatu ponudu
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public OfferEntity createNewOffer(@RequestBody OfferEntity newOffer) {
		List<OfferEntity> offers = getDB();
		newOffer.setId(new Random().nextInt());
		newOffer.setOfferName(newOffer.getOfferName());
		newOffer.setOfferDesc(newOffer.getOfferDesc());
		newOffer.setOfferCreated(newOffer.getOfferCreated());
		newOffer.setOfferExpires(newOffer.getOfferExpires());
		newOffer.setRegularPrice(newOffer.getRegularPrice());
		newOffer.setDiscountPrice(newOffer.getDiscountPrice());
		newOffer.setImagePath(newOffer.getImagePath());
		newOffer.setAvailableOffers(newOffer.getAvailableOffers());
		newOffer.setOfferStatus(newOffer.getOfferStatus());
		offers.add(newOffer);
		return newOffer;
	}
	
	
	
	// TODO 3.5: REST endpoint koji omogućava izmenu postojeće ponude
	// putanja: /project/offers/{id}
	// vraća NULL ako je prosleđen ID koji ne pripada nijednoj ponudi
	// vraća popdatke ponude sa izmenjenim vrednostima
	// N.B. ne menjati vrednost atributa OFFER STATUS
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public OfferEntity modifyOffer(@RequestBody OfferEntity modifiedOffer, @PathVariable Integer id) {
		for (OfferEntity oe : getDB()) {
			if (oe.getId().equals(id)) {
				if (modifiedOffer.getOfferName() != null) {
					oe.setOfferName(modifiedOffer.getOfferName());
				}
				if (modifiedOffer.getOfferDesc() != null) {
					oe.setOfferDesc(modifiedOffer.getOfferDesc());
				}
				if (modifiedOffer.getOfferCreated() != null) {
					oe.setOfferCreated(modifiedOffer.getOfferCreated());
				}
				if (modifiedOffer.getOfferExpires() != null) {
					oe.setOfferExpires(modifiedOffer.getOfferExpires());
				}
				if (modifiedOffer.getRegularPrice() != 0) {
					oe.setRegularPrice(modifiedOffer.getRegularPrice());
				}
				if (modifiedOffer.getDiscountPrice() != 0) {
					oe.setDiscountPrice(modifiedOffer.getDiscountPrice());
				}
				if (modifiedOffer.getImagePath() != null) {
					oe.setImagePath(modifiedOffer.getImagePath());
				}
			}
			return modifiedOffer;
		}
		return null;
	}
	
	
	
	// TODO 3.6: REST endpoint koji omogućava brisanje postojeće ponude
	// putanja: /project/offers/{id}
	// vraća NULL ako je prosleđen ID koji ne pripada nijednoj kategoriji
	// vraća podatke o obrisanoj ponudi
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public OfferEntity deleteOffer (@PathVariable Integer id) {
		List<OfferEntity> offers = getDB();
		Iterator<OfferEntity> itoff = offers.iterator();
		while (itoff.hasNext()) {
			OfferEntity offent = itoff.next();
			if (offent.getId().equals(id)) {
				itoff.remove();
				return offent;
			}
		}
		return null;
	}
	
	
	
	// TODO 3.7: REST endpoint koji vraća ponudu po vrednosti prosleđenog ID-a
	// putanja: /project/offers/{id}
	// vraća NULL ako je prosleđen ID koji ne pripada nijednoj kategoriji
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public OfferEntity getById (@PathVariable Integer id) {
		for (OfferEntity oe : getDB()) {
			if (oe.getId().equals(id))
				return oe;
		}
		return null;
	}
	

	
	// TODO 3.8: REST endpoint koji omogućava promenu vrednosti atributa OFFER STATUS postojeće ponude
	// putanja: /project/offers/changeOffer/{id}/status/{status}
	// vraća NULL ako je prosleđen ID koji ne pripada nijednoj kategoriji
	@RequestMapping(method = RequestMethod.PUT, value = "/changeOffer/{id}/status/{status}")
	public OfferEntity modifyOfferStatus (@PathVariable Integer id,  @PathVariable EOfferStatus status) {
		for (OfferEntity oe : getDB()) {
			if (oe.getId().equals(id)) {
				oe.setOfferStatus(status);
			}
			return oe;
		}
		return null;
	}


	
	// TODO 3.9: REST endpoint koji omogućava pronalazak svih ponuda čija se akcijska cena nalazi u odgovarajućem rasponu
	// putanja: /project/offers/findByPrice/{lowerPrice}/and/{upperPrice}
	@RequestMapping(method = RequestMethod.GET, value = "/findByPrice/{lowerPrice}/and/{upperPrice}")
	public OfferEntity getOfferByRange (@PathVariable double lowerPrice, @PathVariable double upperPrice) {
		for (OfferEntity oer : getDB()) {
			
			
			
			
			
			return oer;
		}
		return null;
	}


}
