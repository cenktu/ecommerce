package com.project.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "model_order")
public class ModelOrder {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", nullable = false)
	 private Long id;
	 
	 @ManyToOne(optional = false)
	 @JoinColumn(name = "user_id", nullable = false)
	 private ModelUser user;
	 
	 @ManyToOne(optional = false)
	 @JoinColumn(name = "address_id", nullable = false)
	 private Address address;
	 
	 @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
	 private List<ModelOrderQuantity> quantities = new ArrayList<>();
	 
	 public Long getId() {
		return id;
	}
	 public void setId(Long id) {
		this.id = id;
	}
	 public ModelUser getUser() {
		return user;
	}
	 public void setUser(ModelUser user) {
		this.user = user;
	}
	 public Address getAddress() {
		return address;
	}
	 public void setAddress(Address address) {
		this.address = address;
	}
	 public List<ModelOrderQuantity> getQuantities() {
		return quantities;
	}
	 public void setQuantities(List<ModelOrderQuantity> quantities) {
		this.quantities = quantities;
	}
}

