package com.iktpreobuka.project.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.entities.VoucherEntity;
import com.iktpreobuka.project.entities.dto.VoucherDTO;
import com.iktpreobuka.project.repositories.VoucherRepository;
import com.iktpreobuka.project.security.Views;
import com.iktpreobuka.project.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/project/vouchers")
public class VoucherController {

    @Autowired
    private VoucherEntity voucherEntity;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherService voucherService;

    // VALIDACIJA
    // 1.7
    // Izmeniti kontroler tako da koristi VoucherDTO prilikom pravljenja novog Voucher-a.

	// TODO 4.3 u paketu com.iktpreobuka.project.controllers napraviti klasu VoucherController sa REST endpoint-om koji vraća listu svih vaučera
	// putanja /project/vouchers
    @GetMapping
    public List<VoucherEntity> getAll() {
        return (List<VoucherEntity>) voucherRepository.findAll();
    }
	
	// TODO 4.6 kreirati REST endpoint-ove za dodavanje, izmenu i brisanje vaučera
	// putanja /project/vouchers/{offerId}/buyer/{buyerId}
	// NAPOMENA: samo korisnik sa ulogom ROLE_CUSTOMER se može naći kao kupac na vaučeru 
	// (u suprotnom ne dozvoliti kreiranje vaučera)
	// putanja /project/vouchers/{id} (izmena)
	// putanja /project/vouchers/{id} (brisanje)
	@PostMapping(path = "/{offerId}/buyer/{buyerId}")
    public ResponseEntity<?> createNewVoucher(@PathVariable Integer offerId,
                                              @PathVariable Integer buyerId,
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  @RequestBody VoucherDTO voucher) throws Exception {
        return VoucherService.createNewVoucher(offerId, buyerId, voucher);
    }

    // izmena
    @PutMapping(path = "/{id}")
    public VoucherEntity changeVoucher(@PathVariable Integer id,
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                        @RequestBody VoucherEntity changeVoucher) throws Exception {
        if (VoucherRepository.existsById(id)) {
            VoucherEntity voucher = voucherRepository.findById(id).get();
            if (changeVoucher.getExpiryDate() != null)
                voucher.setExpiryDate(changeVoucher.getExpiryDate());
            if (changeVoucher.getIsUsed() != null)
                voucher.setIsUsed(changeVoucher.getIsUsed());
            return voucherRepository.save(voucher);
        }
        return null;
    }

    // brisanje
    @DeleteMapping(path = "/{id}")
    public VoucherEntity removeVoucher(@PathVariable Integer id) throws Exception {
        if (voucherRepository.existsById(id)) {
            VoucherEntity voucher = voucherRepository.findById(id).get();
            voucherRepository.delete(voucher);
            return voucher;
        }
        return null;
    }
	
	
	// TODO 4.7 kreirati REST endpoint za pronalazak svih vaučera određenog kupca
	// putanja /project/vouchers/findByBuyer/{buyerId}
	@GetMapping(path = "/findByBuyer/{buyerId}")
    public List<VoucherEntity> findVouchersByBuyer(@PathVariable Integer buyerId) {
        return voucherRepository.findByBuyerId(buyerId);
    }
	
	
	// TODO 4.8 kreirati REST endpoint za pronalazak svih vaučera određene ponude
	// putanja /project/vouchers/findByOffer/{offerId}
    @GetMapping(path = "/findByOffer/{offerId}")
    public List<VoucherEntity> findVouchersByoffer(@PathVariable Integer offerId) {
        return voucherRepository.findByOfferId(offerId);
    }
	
	
	// TODO 4.9 kreirati REST endpoint za pronalazak svih vaučera koji nisu istekli
	// putanja /project/vouchers/findNonExpiredVoucher
    @GetMapping(path = "/findNonExpiredVoucher")
    public List<VoucherEntity> findNonExpiredVoucher(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam VoucherEntity date) {
        return VoucherRepository.findByExpiryDate(date);
    }


    // TODO	• 2.2 potrebno je u metodama kontrolera kreiranim na prethodnim časovima promeniti povratne vrednosti
    //• koristiti klasu ResponseEntity<T>
    //• u slučaju HTTP 200 vratiti telo odgovora i kod 200
    //• u slučaju greške vratiti odgovarajući kod i poruku greške
    // projekti: serijalizacija i validacija

    @RequestMapping(method = RequestMethod.GET, path = "/public")
    @JsonView(Views.Public.class)
    public List<VoucherEntity> getAllPublic() {
        return (List<VoucherEntity>) voucherRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/private")
    @JsonView(Views.Private.class)
    public List<VoucherEntity> getAllPrivate() {
        return (List<VoucherEntity>) voucherRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/admin")
    @JsonView(Views.Admin.class)
    public List<VoucherEntity> getAllAdmin() {
        return (List<VoucherEntity>) voucherRepository.findAll();
    }
}
