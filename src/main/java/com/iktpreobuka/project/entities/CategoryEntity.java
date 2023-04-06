package com.iktpreobuka.project.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;

import jakarta.validation.constraints.Size;

/*
1.2 U klasi CategoryEntity u okviru projekta dodati odgovarajuća ograničenja
• atribut categoryName ne sme da ima null vrednost
• atribut categoryDescription ograničiti na maksimalno 50 karaktera
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoryEntity {
	
	@Id
	@JsonView(Views.Public.class)
	private Integer id;
	
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private String categoryName;
	
	@JsonView(Views.Public.class)
	@Size(max = 50, message = "Category description must not exceed 50 characters.")
	private String categoryDescription;
	
	// TODO 2.1 povezati ponudu i kategoriju
	// jedna ponuda pripada tačno jednoj kategoriju, dok jedna kategorija ima više ponuda koje joj pripadaju
	@OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	//@JoinColumn_?
	private List<OfferEntity> offer;
	
	
	
	public CategoryEntity() {
		super();
	}


	public CategoryEntity(Integer id, 
			String categoryName,
			@Size(max = 50, message = "Category description must not exceed 50 characters.") String categoryDescription,
			List<OfferEntity> offer) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.offer = offer;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getCategoryDescription() {
		return categoryDescription;
	}


	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}


	public List<OfferEntity> getOffer() {
		return offer;
	}


	public void setOffer(List<OfferEntity> offer) {
		this.offer = offer;
	}
	
	

}
