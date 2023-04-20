package com.iktpreobuka.project.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;

	/*
	 * 4.1 u paketu com.iktpreobuka.project.entities kreirati klasu VoucherEntity sa sledećim atributima:
• id – automatski generisan surogatni ključ (celi broj),
• expirationDate- datum dokad vaučer važi i
• isUsed- da li je vaučer iskorišćen (true ili false)
	 1.2
	 */

@Entity
public class VoucherEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	protected Integer id;
	
	@Column
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy")
	@JsonView(Views.Public.class)
	protected LocalDate expiryDate;
	
	@Column
	@JsonView(Views.Admin.class)
	protected Boolean isUsed;
	
	
	// TODO 4.4 povezati ponudu i vaučer
	// vaučer predstavlja račun na kome je uplata novca izvršena, pa poput računa, vaučer se odnosi na kupovinu jedne ponude
	// jedna ponuda se može nalaziti na više vaučera
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "offer")
	@JsonView(Views.Private.class)
	private OfferEntity offer;
	
	// TODO 4.5 povezati korisnika i vaučer
	// vaučer se odnosi na kupovinu jedne ponude od strane jednog korisnika
	// jedan korisnik može imati više vaučera
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	@JsonView(Views.Private.class)
	private UserEntity user;
	
	@JsonView(Views.Admin.class)
	private BillEntity bill;
	
	@Version
	private Integer version;

	public VoucherEntity() {
		super();
	}

	public VoucherEntity(Integer id, LocalDate expiryDate, Boolean isUsed, OfferEntity offer, UserEntity user,
			BillEntity bill, Integer version) {
		super();
		this.id = id;
		this.expiryDate = expiryDate;
		this.isUsed = isUsed;
		this.offer = offer;
		this.user = user;
		this.bill = bill;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public OfferEntity getOffer() {
		return offer;
	}

	public void setOffer(OfferEntity offer) {
		this.offer = offer;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public BillEntity getBill() {
		return bill;
	}

	public void setBill(BillEntity bill) {
		this.bill = bill;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
	

}
