package com.iktpreobuka.project.controllers;

public class VoucherController {
	
	// TODO 4.3 u paketu com.iktpreobuka.project.controllers napraviti klasu VoucherController sa REST endpoint-om koji vraća listu svih vaučera
	// putanja /project/vouchers
	
	// TODO 4.6 kreirati REST endpoint-ove za dodavanje, izmenu i brisanje vaučera
	// putanja /project/vouchers/{offerId}/buyer/{buyerId}
	// NAPOMENA: samo korisnik sa ulogom ROLE_CUSTOMER se može naći kao kupac na vaučeru 
	// (u suprotnom ne dozvoliti kreiranje vaučera)
	// putanja /project/vouchers/{id} (izmena)
	// putanja /project/vouchers/{id} (brisanje)
	
	
	
	// TODO 4.7 kreirati REST endpoint za pronalazak svih vaučera određenog kupca
	// putanja /project/vouchers/findByBuyer/{buyerId}
	
	
	
	// TODO 4.8 kreirati REST endpoint za pronalazak svih vaučera određene ponude
	// putanja /project/vouchers/findByOffer/{offerId}
	
	
	
	// 4.9 kreirati REST endpoint za pronalazak svih vaučera koji nisu istekli
	// putanja /project/vouchers/findNonExpiredVoucher

}
