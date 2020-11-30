package com.globomantics.entity;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = -5605630022832932340L;	
	
	private Long id;	
	private String name;	
	private Integer version;	
	private Long quantity;
	
	public Product(Long id, String name, Integer version, Long quantity) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", version=" + version + ", quantity=" + quantity + "]";
	}

}
