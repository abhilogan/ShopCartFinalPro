package com.niit.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Product;

//another annotations..

@Transactional
@Repository("productDAO") // will create instance of ProductDAOImpl and the name will productDAOImpl
public class ProductDAOImpl implements ProductDAO {

	// first - inject hibernate session .

	@Autowired // session factory is automatically injected in this class
	private SessionFactory sessionFactory;

	@Autowired
	private Product product;

	public boolean save(Product product) {
		// STORE IN THE DATA BASE

		try {

			sessionFactory.getCurrentSession().save(product);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean upadte(Product product) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(product);
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	public Product get(String emailID) {
		// it will set the record based onemailID and store in Product class
		return sessionFactory.getCurrentSession().get(Product.class, emailID);

	}

	public boolean delete(String emailID) {
		try {
			product = get(emailID);
			if (product == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(product);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();

	}

	public List<Product> search(String searchString) {

		String hql = "from Product where description like '%" + searchString + "%'";

		return sessionFactory.getCurrentSession().createQuery(hql).list();

	}


	public List<Product> search(String searchString, int maxPrice) 
	{
		String hql ="from Product where description like '%"
				+ searchString + "%'  and price < " +
				maxPrice;
		
	return	sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	
	public List<Product> search(String searchString, int minPrice, int maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

}
