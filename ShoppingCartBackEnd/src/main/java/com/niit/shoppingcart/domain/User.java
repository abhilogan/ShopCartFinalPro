package com.niit.shoppingcart.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

//whenever it scan all the classes under particular  package,
//will create instance of the class.

@Component//to create instance; the instance is same as class name , but 
// first character will be small letter  --user
@Entity//to specify it is not normal class- it is data entity
@Table(name="user")//to specify to which database table we need to map this class
public class User 
{

	@Id//to specify it is primary key
	private String emailID ;
	
	private String name ;
	@Column(name="password")// to specify to map to a particular column in data base column
	private String pwd ;
	private String mobile ;
	private Character role ;
	private Date registeredDate ;
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Character getRole() {
		return role;
	}
	public void setRole(Character role) {
		this.role = 'C';
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = new java.util.Date();
	}
	
	
}
