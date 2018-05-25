package com.niit.shoppingcart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestConfiguration 
{
	//test weather the user instance is created or not
	
	public static void main(String[] args) 
	{
		//because we use annotations above the domain classes
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		//will scan the package and check weather any classes are there
		//inside this package
		context.scan("com.niit");
		
		//will clear the context and create new instances of that classes which are 
		//their in com.niit package with annotation
		context.refresh();
		
		//try to get instance of user class
		
		//it will return the instance if it is available in bean factory.
		//else it will through an error : NoSuchBeanDefinationException
		context.getBean("user");
		
		System.out.println("successfully created instance");
		
		
		
		
		
		
		
	}
}
