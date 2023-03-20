package com.iktpreobuka.project.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@JsonView(Views.Private.class)
	private OfferEntity offer;
	@JsonView(Views.Private.class)
	private UserEntity user;
	@JsonView(Views.Admin.class)
	private BillEntity bill;

	public VoucherEntity() {}

	public VoucherEntity(Integer id, LocalDate expiryDate, Boolean isUsed) {
		super();
		this.id = id;
		this.expiryDate = expiryDate;
		this.isUsed = isUsed;
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

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
