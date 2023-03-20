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
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "offer")
	@JsonView(Views.Private.class)
	private OfferEntity offer;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	@JsonView(Views.Private.class)
	private UserEntity user;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "category")
	@JsonView(Views.Admin.class)
	private CategoryEntity category;

	public BillEntity() {}

	public BillEntity(Integer id, Boolean paymentMade, 
			Boolean paymentCancelled, LocalDate billCreated) {
		super();
		this.id = id;
		this.paymentMade = paymentMade;
		this.paymentCancelled = paymentCancelled;
		this.billCreated = billCreated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean isPaymentMade() {
		return paymentMade;
	}

	public void setPaymentMade(Boolean paymentMade) {
		this.paymentMade = paymentMade;
	}

	public Boolean isPaymentCanceled() {
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

}
