package com.iktpreobuka.project.controllers;
/*
 * 3.3 u paketu com.iktpreobuka.project.controllers napraviti klasu BillController sa REST endpoint-om koji vraća listu svih računa
• putanja /project/bills
 */

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
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
	
@Autowired
private BillController billController;

@Autowired
private UserRepository userRepository;

@Autowired
private OfferRepository offerRepository;

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

//@RequestMapping(method = RequestMethod.GET) 

}
