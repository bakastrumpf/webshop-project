package com.iktpreobuka.project.controllers;
/*
 * 3.3 u paketu com.iktpreobuka.project.controllers napraviti klasu BillController sa REST endpoint-om koji vraća listu svih računa
• putanja /project/bills
 */

/*
VALIDACIJA
1.5 Izmeniti kontroler tako da koristi BillDTO prilikom pravljenja novog Bill-a.
 */

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.iktpreobuka.project.controllers.utils.RESTError;
import com.iktpreobuka.project.entities.dto.BillDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.repositories.BillRepository;
import com.iktpreobuka.project.repositories.CategoryRepository;
import com.iktpreobuka.project.repositories.OfferRepository;
import com.iktpreobuka.project.repositories.UserRepository;
import com.iktpreobuka.project.services.BillService;
import com.iktpreobuka.project.services.OfferService;

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
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private OfferService offerService;

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<Iterable<BillEntity>>(billRepository.findAll(), HttpStatus.OK);
	}

//	@RequestMapping(method = RequestMethod.GET, path = "/")
//	public List<BillEntity> getAll() {
//		return (List<BillEntity>) billRepository.findAll();
//	}


	
	// TODO: svu logiku iz kontrolera prebaciti u servis!!! 
	
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
	
	// first version
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
	
	// DB version
//	@RequestMapping(path = "/{offerId}/buyer/{buyerId}", method = RequestMethod.POST)
//	public BillEntity addBill(@PathVariable Integer offerId, 
//			@PathVariable Integer buyerId, 
//			@RequestBody @DateTimeFormat(iso = ISO.DATE) BillEntity newBill) {
//		if (!userRepository.existsById(buyerId))
//			return null;
//		UserEntity buyer = userRepository.findById(buyerId).get();
//		if (!newBill.isPaymentMade())
//			return null;
//		BillEntity bill = new BillEntity();
//		bill.setOffer(offerService.changeAvailableBoughtOfferBuy(offerId));
//		bill.setBuyer(buyer);
//		bill.setPaymentMade(true);
//		bill.setPaymentCancelled(false);
//		bill.setBillCreated(newBill.getBillCreated());
//		
//		return billRepository.save(bill);
//	}
	
	
	// DB
//	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
//	public BillEntity modifyBill(@PathVariable Integer id, @RequestBody @DateTimeFormat(iso = ISO.DATE) BillEntity newBill){
//		if (!billRepository.existsById(id))
//			return null;
//		BillEntity bill = billRepository.findById(id).get();
//		if(newBill.getBuyer() != null)
//			bill.setBuyer(newBill.getBuyer());
//		if(newBill.getOffer() != null)
//			bill.setOffer(newBill.getOffer());
//		if(newBill.isPaymentCancelled() == true) {
//			bill.setPaymentCancelled(true);
//			bill.setOffer(offerService.changeAvailableBoughtOfferCancelled(bill.getOffer().getId()));
//		} else
//			bill.setPaymentCancelled(false);
//		if(newBill.getBillCreated() != null)
//			bill.setBillCreated(newBill.getBillCreated());
//		
//		return billRepository.save(bill);
//		
//	}
	
	// LJC
//	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
//	public BillEntity modifyBill(@PathVariable Integer id, 
//			@DateTimeFormat(pattern = "dd-MM-yyyy")	@RequestBody BillEntity changeBill) {
//		if (billRepository.existsById(id)) {
//			BillEntity bill = billRepository.findById(id).get();
//			Date billCreated = new Date();
//			bill.setBillCreated(billCreated);
//			bill.setCategory(Validation.setIfNotNull(bill.getCategory(), changeBill.getCategory()));
//			bill.setOffer(Validation.setIfNotNull(bill.getOffer(), changeBill.getOffer()));
//			bill.setPaymentCancelled(bill.getPaymentCancelled());
//			bill.setPaymentMade(bill.getPaymentMade());
//			bill.setUser(Validation.setIfNotNull(bill.getUser(), changeBill.getUser()));
//			billRepository.save(bill);
//			if (bill.getPaymentCancelled()) {
//				bill.getOffer().setAvailableOffers(bill.getOffer().getAvailableOffers() +1);
//				bill.getOffer().setBoughtOffers(bill.getOffer().getBoughtOffers() -1);
//				offerRepository.save(bill.getOffer());
//			}
//			return bill;
//		}
//		return null;
//	}
	
	
	// DB
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public BillEntity deleteBill(@PathVariable Integer id) {
		if (!billRepository.existsById(id))
			return null;
		BillEntity bill = billRepository.findById(id).get();
		billRepository.delete(bill);
		return bill;
	}
	
	// LJC
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public BillEntity removeBill(@PathVariable Integer id) {
		if (billRepository.existsById(id)) {
			BillEntity bill = billRepository.findById(id).get();
			billRepository.delete(bill);
			return bill;
		}
		return null;
	}
	
	
	// LJC
//	@RequestMapping(method = RequestMethod.DELETE, value = "/inactive")
//	public List<BillEntity> existInactive() {
//		List<BillEntity> inactive = billService.activeBills();
//		return inactive;
//	}

	
	// TODO 3.7 kreirati REST endpoint za pronalazak svih računa određenog kupca
	// putanja /project/bills/findByBuyer/{buyerId}
	// DB
	@RequestMapping(method = RequestMethod.GET, value = "/{buyerId}")
	public List<BillEntity> findBuyerBills(@PathVariable Integer buyerId) {
		return billRepository.findByBuyerId(buyerId);
	}
	
	// SJ
	@RequestMapping(method = RequestMethod.GET, value = "2/{buyerId}")
	public List<BillEntity> getAllBillsByBuyer(@PathVariable Integer buyerId) {
		return billService.findAllBillsByBuyer(buyerId);
	}

	//NM
	@RequestMapping(method = RequestMethod.GET, value = "3/{buyerId}")
	public ResponseEntity<?> findBillByBuyer(@PathVariable Integer buyerId) {
		if (billRepository.existsById(buyerId)) {
			Iterable<BillEntity> bills = billRepository.findByBuyerId(buyerId);
			return new ResponseEntity<>(bills, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "List of bills not found."), HttpStatus.NOT_FOUND);
	}
	
	// TODO 3.8 kreirati REST endpoint za pronalazak svih računa određene kategorije
	// putanja /project/bills/findByCategory/{categoryId}
	// DB
	@RequestMapping(method = RequestMethod.GET, value = "/{categoryId}")
	public List<BillEntity> findBillsByOfferCategory(@PathVariable Integer categoryId) {
		return billRepository.findByOfferCategoryId(categoryId);
	}
	
	//  SJ
	@RequestMapping(method = RequestMethod.GET, value = "/{categoryId2}")
	public List<BillEntity> findAllBillsBycategoryId(@PathVariable Integer categoryId) {
		return billService.findAllByOfferCategoryId(categoryId);
	}
	
	
	// TODO 3.9 kreirati REST endpoint za pronalazak svih računa koji su kreirani u odgovarajućem vremenskom periodu
	// putanja /project/bills/findByDate/{startDate}/and/{endDate}
	// DB
	@RequestMapping(method = RequestMethod.GET, value = "/findByDate/{startDate}/and/{endDate}")
	public List<BillEntity> findBillsBetween(@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate startDate, 
			@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate endDate){
		return billRepository.findByBillCreatedBetween(startDate, endDate);
	}
	
	// SJ
	@RequestMapping(method = RequestMethod.GET, value = "/findByDate2/{startDate}/and/{endDate}")
	public List<BillEntity> getAllBillsByDate(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
		return billService.getAllBillsByDate(startDate, endDate);
	}
	
	
	
	// TODO 5.1 proširiti metodu za dodavanje računa tako da se smanji broj dostupnih ponuda ponude sa računa, 
	// odnosno poveća broj kupljenih
	@PostMapping(path = "/{offerId}/buyer/{buyerId}")
	public ResponseEntity<?> createBillWithOfferAndBuyer(@PathVariable Integer offerId,
														 @PathVariable Integer buyerId,
														 @Valid @RequestBody BillDTO bill,
														 BindingResult bindingResult) throws Exception {
		return billService.createBillWithOfferAndBuyer(offerId, buyerId, bill, bindingResult);
	}
	
	
	// TODO 5.2 proširiti metodu za izmenu računa tako da
	//  ukoliko se račun proglašava otkazanim treba povećati broj dostupnih ponuda ponude sa računa,
	// i smanjiti broj kupljenih
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateBill(@PathVariable Integer id,
										@DateTimeFormat(iso = ISO.DATE) @Valid @RequestBody BillEntity updatedBill) throws Exception {
		return billService.updateBill(id, updatedBill);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> removeOneBill(@PathVariable Integer id) {
		if (billRepository.existsById(id)) {
			BillEntity bill = billRepository.findById(id).get();
			billRepository.delete(bill);
			return new ResponseEntity<>(bill, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Bill not found."), HttpStatus.NOT_FOUND);
	}
	
	// TODO • 2.2 u metodi za dodavanje računa u okviru BillController-a potrebno je za izmenu broja dostupnih/kupljenih ponuda
	// pozvati odgovarajuću metodu servisa zaduženog za rad sa ponudama
	
	
	
	// TODO • 2.3 u metodi za izmenu računa u okviru BillController-a potrebno je nakon što se račun proglasi otkazanim za
	// izmenu broja dostupnih/kupljenih ponuda pozvati odgovarajuću metodu servisa zaduženog za rad sa ponudama
	
	
	
	// TODO • 2.4 u servisu zaduženom za rad sa računima, napisati metodu koja za prosleđene datume vraća račune koji se nalaze u datom periodu
	// • pozvati je u okviru metode BillController-a za pronalazak svih računa koji su kreirani u odgovarajućem vremenskom periodu
	
	
	
	// TODO• 4.1 omogućiti kreiranje vaučera kada se atribut računa paymentMade postavi na true
	// • u okviru servisa zaduženog za rad sa vaučerima, napisati metodu koja vrši kreiranje vaučera na osnovu prosleđenog računa
	// • pozvati je u okviru metode za izmenu računa u BillController-u


	// TODO	• 2.2 potrebno je u metodama kontrolera kreiranim na prethodnim časovima promeniti povratne vrednosti
	//• koristiti klasu ResponseEntity<T>
	//• u slučaju HTTP 200 vratiti telo odgovora i kod 200
	//• u slučaju greške vratiti odgovarajući kod i poruku greške
	// projekti: serijalizacija i validacija


	// NM
	//TODO • 3.4 kreirati REST endpoint u klasi BillController za izveštaj za ukupnu prodaju po danima
	//• putanja /project/bills/generateReportByDate/{startDate}/and/{endDate}
	//• iskoristiti prethodno kreirane DTO-ve
	@GetMapping(path = "/generateReportByDate/{startDate}/and/{endDate}")
	public ResponseEntity<?> generateReportByDate(@PathVariable String startDate, @PathVariable String endDate){
		return billService.generateReportByDate(startDate, endDate);
	}


	// TODO	• 3.5 kreirati REST endpoint u klasi BillController za izveštaj o prodaji po kategoriji
	//• putanja /project/bills/generateReport/{startDate}/and/{endDate}/category/{categoryId}
	//• iskoristiti prethodno kreirane DTO-ve

}
