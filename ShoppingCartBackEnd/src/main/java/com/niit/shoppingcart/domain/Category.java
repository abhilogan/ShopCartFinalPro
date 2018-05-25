package com.niit.shoppingcart.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

//markup the java class as a bean or say component so that the component-scanning
//mechanism of spring can add into the application context

@Component
@Entity//it mark the class as an entity bean
@Table// alow to specify the details of the table that will be used to persist the entity in the data base.
public class Category {

	@Id
	private String id;

	private String name;

	private String description;

	// Eager= Fetch all at a time
	// lazy= do not fetch ,fetch only when categories get products when
	// user click on category

	// @OneToMany= one category may have n number of products

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Set<Product> products;

	////////////////////////////////////

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
