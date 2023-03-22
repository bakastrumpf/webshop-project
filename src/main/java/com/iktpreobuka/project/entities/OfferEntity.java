package com.iktpreobuka.project.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;
 
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
	
	

	public OfferEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public OfferEntity(Integer id, String offerName, String offerDesc, Date offerCreated, Date offerExpires,
			double regularPrice, double discountPrice, String imagePath, Integer availableOffers, Integer boughtOffers,
			EOfferStatus offerStatus) {
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

	

}