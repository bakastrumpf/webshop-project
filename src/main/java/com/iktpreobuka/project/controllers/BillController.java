package com.iktpreobuka.project.controllers;
/*
 * 3.3 u paketu com.iktpreobuka.project.controllers napraviti klasu BillController sa REST endpoint-om koji vraća listu svih računa
• putanja /project/bills
 */

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.repositories.BillRepository;
import com.iktpreobuka.project.repositories.OfferRepository;
import com.iktpreobuka.project.repositories.UserRepository;

//import com.iktpreobuka.dataaccess.entities.UserEntity;
//import com.iktpreobuka.dataaccess.repositories.AddressRepository;
//import com.iktpreobuka.dataaccess.repositories.UserRepository;

@RestController
@RequestMapping("/project/bills")
public class BillController {
	
//	@Autowired
//	private BillController billController;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public List<BillEntity> getAll() {
		return (List<BillEntity>) billRepository.findAll();
	}
	

	
	@RequestMapping(method = RequestMethod.POST) 
	public BillEntity createBill(@RequestParam LocalDate date, 
			@RequestParam Integer user) {
		BillEntity bill = new BillEntity();
		bill.setId(user);
		bill.setBillCreated(date);
		bill.setPaymentMade(null);
		bill.setPaymentCancelled(null);
		//BillRepository.save(bill);
		return bill;
	}


	
	// TODO 3.6 kreirati REST endpoint-ove za dodavanje, izmenu i brisanje računa
	// putanja /project/bills/{offerId}/buyer/{buyerId} (dodavanje)
	// putanja /project/bills/{id} (izmena i brisanje)
	
	@RequestMapping(path = "/{offerId}/buyer/{buyerId}", method = RequestMethod.POST)
	public BillEntity addBill(@PathVariable Integer offerId, @PathVariable Integer buyerId, @RequestBody BillEntity newBill) {
		if(offerRepository.existsById(offerId))
			if(userRepository.existsById(buyerId)) {
				newBill.setUser(userRepository.findById(buyerId).get());
				newBill.setOffer(offerRepository.findById(offerId).get());
				newBill.setBillCreated(LocalDate.now());
				return billRepository.save(newBill);
			}
		return null;
	}

	
	// TODO 3.7 kreirati REST endpoint za pronalazak svih računa određenog kupca
	// putanja /project/bills/findByBuyer/{buyerId}
	
	
	
	// TODO 3.8 kreirati REST endpoint za pronalazak svih računa određene kategorije
	// putanja /project/bills/findByCategory/{categoryId}
	
	
	
	// TODO 3.9 kreirati REST endpoint za pronalazak svih računa koji su kreirani u odgovarajućem vremenskom periodu
	// putanja /project/bills/findByDate/{startDate}/and/{endDate}
	
	
	
	// TODO 5.1 proširiti metodu za dodavanje računa tako da se smanji broj dostupnih ponuda ponude sa računa, 
	// odnosno poveća broj kupljenih
	
	
	
	// TODO 5.2 proširiti metodu za izmenu računa tako da ukoliko se račun proglašava otkazanim 
	// tada treba povećati broj	dostupnih ponuda ponude sa računa, 
	// odnosno smanjiti broj kupljenih
	
	
	
	// TODO • 2.2 u metodi za dodavanje računa u okviru BillController-a potrebno je za izmenu broja dostupnih/kupljenih ponuda
	// pozvati odgovarajuću metodu servisa zaduženog za rad sa ponudama
	
	
	
	// TODO • 2.3 u metodi za izmenu računa u okviru BillController-a potrebno je nakon što se račun proglasi otkazanim za
	// izmenu broja dostupnih/kupljenih ponuda pozvati odgovarajuću metodu servisa zaduženog za rad sa ponudama
	
	
	
	// TODO • 2.4 u servisu zaduženom za rad sa računima, napisati metodu koja za prosleđene datume vraća račune koji se nalaze u datom periodu
	// • pozvati je u okviru metode BillController-a za pronalazak svih računa koji su kreirani u odgovarajućem vremenskom periodu
	
	
	
	// TODO• 4.1 omogućiti kreiranje vaučera kada se atribut računa paymentMade postavi na true
	// • u okviru servisa zaduženog za rad sa vaučerima, napisati metodu koja vrši kreiranje vaučera na osnovu prosleđenog računa
	// • pozvati je u okviru metode za izmenu računa u BillController-u
	
}
