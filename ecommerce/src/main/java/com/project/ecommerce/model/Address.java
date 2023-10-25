package com.project.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Long id;
	
	@Column(name="addressLine1",nullable = false,length = 512)
	private String addressLine1;
	
	@Column(name="addressLine2",length = 512)
	private String addressLine2;
	
	@Column(name = "city",nullable = false)
	private String city;
	
	@Column(name = "county",nullable = false ,length =  75)
	private String country;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private ModelUser user;
	
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public ModelUser getUser() {
		return user;
	}
	public void setUser(ModelUser user) {
		this.user = user;
	}
}