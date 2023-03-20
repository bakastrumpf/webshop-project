package com.iktpreobuka.project.entities;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;

/*
1.2 U klasi CategoryEntity u okviru projekta dodati odgovarajuća ograničenja
• atribut categoryName ne sme da ima null vrednost
• atribut categoryDescription ograničiti na maksimalno 50 karaktera
 */

public class CategoryEntity {
	
	@JsonView(Views.Public.class)
	private Integer id;
	
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private String categoryName;
	
	@JsonView(Views.Public.class)
	//@Size(max = 50, message = "Category description must not exceed 50 characters.")
	private String categoryDescription;
	
	public CategoryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryEntity(Integer id, String categoryName, String categoryDescription) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
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
	

}
