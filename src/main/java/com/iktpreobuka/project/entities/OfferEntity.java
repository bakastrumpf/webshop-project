package com.iktpreobuka.project.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;

import jakarta.validation.constraints.Size;
 
/*
1.3
U klasi OfferEntity u okviru projekta dodati odgovarajuća ograničenja
• nijedan od atributa ne sme imati null vrednost 
(bez atributa status, datum kreiranja i datum isticanja ponude)
• atribut offerDescription mora imati između 5 i 20 karaktera
• atributi availableOffers i buyedOffers ne mogu imati vrednost manju od 0
• atributi regularPrice i actionPrice ne mogu imati vred. manju od 1
*/

@Entity
public class OfferEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private String offerName;
	
	@Column(nullable = false)
	@JsonView(Views.Public.class)
	@Size(min = 5, max = 20, message = "Offer description must contain between {min} and {max} characters.")
	private String offerDesc;
	
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy")
	@JsonView(Views.Public.class)
	private Date offerCreated;
	
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy")
	@JsonView(Views.Public.class)
	private Date offerExpires;
	
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	@Size(min = 1)
	private double regularPrice;
	
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	@Size(min = 1)
	private double discountPrice;
	
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private String imagePath;
	
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	@Size(min = 0)
	private Integer availableOffers;
	
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	@Size(min = 0)
	private Integer boughtOffers;
	
	@JsonView(Views.Public.class)
	private EOfferStatus offerStatus;
	
	// • 2.1 povezati ponudu i kategoriju
	// • jedna ponuda pripada tačno jednoj kategoriju, dok jedna kategorija ima više ponuda koje joj pripadaju
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "category")
	@JsonIgnore
	private CategoryEntity category;
	
	// • 2.2 povezati korisnika i ponudu
	// • korisnik može da kreira više ponuda, a jednu ponudu kreira tačno jedan korisniks
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator")
	private UserEntity creator;
	
	// TODO 3.4 povezati ponudu i račun
	// račun predstavlja kupovinu jedne ponude
	// jedna ponuda se može nalaziti na više računa
	@OneToMany(mappedBy = "offer", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BillEntity> bill;
	
	// TODO 4.4 povezati ponudu i vaučer
	// vaučer predstavlja račun na kome je uplata novca izvršena, pa poput računa, vaučer se odnosi na kupovinu jedne ponude
	// jedna ponuda se može nalaziti na više vaučera
	@OneToMany(mappedBy = "offer", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<VoucherEntity> voucher;
	
	
	public OfferEntity() {
		super();
	}


	public OfferEntity(Integer id, String offerName,
			@Size(min = 5, max = 20, message = "Offer description must contain between {min} and {max} characters.") String offerDesc,
			Date offerCreated, Date offerExpires, @Size(min = 1) double regularPrice,
			@Size(min = 1) double discountPrice, String imagePath, @Size(min = 0) Integer availableOffers,
			@Size(min = 0) Integer boughtOffers, 
			EOfferStatus offerStatus, 
			CategoryEntity category, 
			UserEntity creator,
			List<BillEntity> bill, 
			List<VoucherEntity> voucher) {
		super();
		this.id = id;
		this.offerName = offerName;
		this.offerDesc = offerDesc;
		this.offerCreated = offerCreated;
		this.offerExpires = offerExpires;
		this.regularPrice = regularPrice;
		this.discountPrice = discountPrice;
		this.imagePath = imagePath;
		this.availableOffers = availableOffers;
		this.boughtOffers = boughtOffers;
		this.offerStatus = offerStatus;
		this.category = category;
		this.creator = creator;
		this.bill = bill;
		this.voucher = voucher;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getOfferName() {
		return offerName;
	}


	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}


	public String getOfferDesc() {
		return offerDesc;
	}


	public void setOfferDesc(String offerDesc) {
		this.offerDesc = offerDesc;
	}


	public Date getOfferCreated() {
		return offerCreated;
	}


	public void setOfferCreated(Date offerCreated) {
		this.offerCreated = offerCreated;
	}


	public Date getOfferExpires() {
		return offerExpires;
	}


	public void setOfferExpires(Date offerExpires) {
		this.offerExpires = offerExpires;
	}


	public double getRegularPrice() {
		return regularPrice;
	}


	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}


	public double getDiscountPrice() {
		return discountPrice;
	}


	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public Integer getAvailableOffers() {
		return availableOffers;
	}


	public void setAvailableOffers(Integer availableOffers) {
		this.availableOffers = availableOffers;
	}


	public Integer getBoughtOffers() {
		return boughtOffers;
	}


	public void setBoughtOffers(Integer boughtOffers) {
		this.boughtOffers = boughtOffers;
	}


	public EOfferStatus getOfferStatus() {
		return offerStatus;
	}


	public void setOfferStatus(EOfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}


	public CategoryEntity getCategory() {
		return category;
	}


	public void setCategory(CategoryEntity category) {
		this.category = category;
	}


	public UserEntity getUser() {
		return creator;
	}


	public void setUser(UserEntity creator) {
		this.creator = creator;
	}


	public List<BillEntity> getBill() {
		return bill;
	}


	public void setBill(List<BillEntity> bill) {
		this.bill = bill;
	}


	public List<VoucherEntity> getVoucher() {
		return voucher;
	}


	public void setVoucher(List<VoucherEntity> voucher) {
		this.voucher = voucher;
	}

	

	

}