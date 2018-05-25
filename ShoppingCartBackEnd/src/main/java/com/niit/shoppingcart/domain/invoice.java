package com.niit.shoppingcart.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity

public class invoice implements  Serializable
{
	@Id
	@GeneratedValue
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@OneToOne
	public User user;
	public String billTo;
	public String paymentMethod;
	public String shippedTo;
	public Date orderDate;
	public String getBillTo() {
		return billTo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getShippedTo() {
		return shippedTo;
	}
	public void setShippedTo(String shippedTo) {
		this.shippedTo = shippedTo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = new java.util.Date();
	}
	
	
	
	
}
