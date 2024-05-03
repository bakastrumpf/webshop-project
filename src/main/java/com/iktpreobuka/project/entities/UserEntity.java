package com.iktpreobuka.project.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
• 1.1 u paketu com.iktpreobuka.project.entities napraviti klasu UserEntity sa sledećim atributima:
• id, first name, last name, username, password, email i user role
• user role može da ima sledeće vrednosti: ROLE_CUSTOMER, ROLE_ADMIN i ROLE_SELLER (koristiti enumeraciju), 
dok svi ostali atributi, sem id-a treba da budu tekstualnog tipa
	 */
/*
VALIDACIJA
1.1
U klasi UserEntity u okviru projekta dodati odgovarajuća ograničenja
• nijedan od atributa ne sme imati null vrednost (bez atributa role)
• atribut username mora imati između 5 i 20 karaktera
• atribut password ne sme imati manje od 5 karaktera i mora sadržati slova i brojeve
 */

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
	
	@Id
	@JsonProperty("ID")
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	protected Integer id;
	
	@JsonView(Views.Private.class)
	@Column(nullable = false)
	protected String firstName;
	
	@JsonView(Views.Private.class)
	@Column(nullable = false)
	protected String lastName;
	
	@JsonProperty("USERNAME")
	@JsonView(Views.Public.class)
	@NotBlank(message = "Username must not be blank nor null")
	@Size(min = 5, max = 20, message = "Last name must be string between {min} and {max}.")
	@Column(nullable = false)
	protected String username;
	
	@JsonView(Views.Private.class)
	@JsonIgnore
	@Column(nullable = false)
	@Min(value = 5, message = "Password must contain at least 5 characters")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
			message="Password must consist of letters and numbers.")
	protected String password;
	
	@JsonView(Views.Private.class)
	@Column(nullable = false)
	protected String email;
	
	@JsonView(Views.Admin.class)
	@Column(nullable = false)
	protected EUserRole euserRole;
	
	@Version
	private Integer version;
	
	@OneToMany(mappedBy = "creator", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserEntity> creator;
	
	// TODO 2.2 povezati korisnika i ponudu
	// korisnik može da kreira više ponuda, a jednu ponudu kreira tačno jedan korisnik
	@OneToMany(mappedBy = "creator", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<OfferEntity> offer;
	
	
	// TODO 3.5 povezati korisnika i račun
	// račun predstavlja kupovinu jedne ponude od strane jednog kupca
	// jedan korisnik može imati više računa
	@OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BillEntity> bill;
	
	// TODO 4.5 povezati korisnika i vaučer
	// vaučer se odnosi na kupovinu jedne ponude od strane jednog korisnika
	// jedan korisnik može imati više vaučera
	@OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<VoucherEntity> voucher;

		
	public UserEntity() {
		super();
	}


	
	public UserEntity(Integer id, 
			String firstName, 
			String lastName,
			@NotBlank(message = "Username must not be blank nor null") @Size(min = 5, max = 20, message = "Last name must be string between {min} and {max}.") String username,
			@Min(value = 5, message = "Password must contain at least 5 characters") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Password must consist of letters and numbers.") String password,
			String email, EUserRole euserRole, 
			List<UserEntity> creator, 
			List<OfferEntity> offer, 
			List<BillEntity> bill,
			List<VoucherEntity> voucher) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.euserRole = euserRole;
		this.creator = creator;
		this.offer = offer;
		this.bill = bill;
		this.voucher = voucher;
	}





	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public EUserRole getEuserRole() {
		return euserRole;
	}


	public void setEuserRole(EUserRole euserRole) {
		this.euserRole = euserRole;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public List<UserEntity> getCreator() {
		return creator;
	}


	public void setCreator(List<UserEntity> creator) {
		this.creator = creator;
	}


	public List<OfferEntity> getOffer() {
		return offer;
	}


	public void setOffer(List<OfferEntity> offer) {
		this.offer = offer;
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