package com.iktpreobuka.project.entities;

/* 3.1 
 * u paketu com.iktpreobuka.project.entities kreirati klasu BillEntity sa sledećim atributima:
• id – automatski generisan surogatni ključ (celi broj),
• paymentMade - da li je novac uplaćen (true ili false),
• paymentCanceled - da li je kupovina otkazana (true ili false),
• billCreated - datum kada je račun napravljen.
 1.2 
 */

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

@Entity
public class BillEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	protected Integer id;
	
	@Column
	@JsonView(Views.Admin.class)
	protected Boolean paymentMade;
	
	@Column
	@JsonView(Views.Admin.class)
	protected Boolean paymentCancelled;
	
	@Column
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy")
	@JsonView(Views.Public.class)
	protected LocalDate billCreated;
	
	// TODO 3.4 povezati ponudu i račun
	// račun predstavlja kupovinu jedne ponude
	// jedna ponuda se može nalaziti na više računa
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "offer")
	@JsonView(Views.Private.class)
	private OfferEntity offer;
	
	
	// TODO 3.5 povezati korisnika i račun
	// račun predstavlja kupovinu jedne ponude od strane jednog kupca
	// jedan korisnik može imati više računa
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	@JsonView(Views.Private.class)
	private UserEntity user;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "category")
	@JsonView(Views.Admin.class)
	private CategoryEntity category;
	
	// da li mi ovo treba? Da li je ovo USER odozgo?
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "buyer")
	private UserEntity buyer;
	
	@Version
	private Integer version;

	
	public BillEntity() {
		super();
	}


	public BillEntity(Integer id, Boolean paymentMade, Boolean paymentCancelled, LocalDate billCreated,
			OfferEntity offer, UserEntity user, CategoryEntity category, UserEntity buyer, Integer version) {
		super();
		this.id = id;
		this.paymentMade = paymentMade;
		this.paymentCancelled = paymentCancelled;
		this.billCreated = billCreated;
		this.offer = offer;
		this.user = user;
		this.category = category;
		this.buyer = buyer;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Boolean getPaymentMade() {
		return paymentMade;
	}


	public void setPaymentMade(Boolean paymentMade) {
		this.paymentMade = paymentMade;
	}


	public Boolean getPaymentCancelled() {
		return paymentCancelled;
	}


	public void setPaymentCancelled(Boolean paymentCancelled) {
		this.paymentCancelled = paymentCancelled;
	}


	public LocalDate getBillCreated() {
		return billCreated;
	}


	public void setBillCreated(LocalDate billCreated) {
		this.billCreated = billCreated;
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


	public CategoryEntity getCategory() {
		return category;
	}


	public void setCategory(CategoryEntity category) {
		this.category = category;
	}


	public UserEntity getBuyer() {
		return buyer;
	}


	public void setBuyer(UserEntity buyer) {
		this.buyer = buyer;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}

	
	
	

}
