package com.iktpreobuka.project.controllers;

public class VoucherController {

    // VALIDACIJA
    // 1.7
    // Izmeniti kontroler tako da koristi VoucherDTO prilikom pravljenja novog Voucher-a.

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
	
	
	
	// TODO 4.9 kreirati REST endpoint za pronalazak svih vaučera koji nisu istekli
	// putanja /project/vouchers/findNonExpiredVoucher



    // TODO	• 2.2 potrebno je u metodama kontrolera kreiranim na prethodnim časovima promeniti povratne vrednosti
    //• koristiti klasu ResponseEntity<T>
    //• u slučaju HTTP 200 vratiti telo odgovora i kod 200
    //• u slučaju greške vratiti odgovarajući kod i poruku greške
    // projekti: serijalizacija i validacija
}
