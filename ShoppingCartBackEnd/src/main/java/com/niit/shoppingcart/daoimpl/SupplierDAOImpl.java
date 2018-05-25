package com.niit.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

//another annotations..

@Transactional
@Repository("supplierDAO") // will create instance of SupplierDAOImpl and the name will supplierDAOImpl
public class SupplierDAOImpl implements SupplierDAO {

	// first - inject hibernate session .

	@Autowired // session factory is automatically injected in this class
	private SessionFactory sessionFactory;

	@Autowired
	private Supplier supplier;

	public boolean save(Supplier supplier) {
		// STORE IN THE DATA BASE

		try {
		
			sessionFactory.getCurrentSession().save(supplier);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean upadte(Supplier supplier) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(supplier);
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	public Supplier get(String emailID) {
		// it will set the record based onemailID and store in Supplier class
		return sessionFactory.getCurrentSession().get(Supplier.class, emailID);

	}

	public boolean delete(String emailID) {
		try {
			supplier = get(emailID);
			if (supplier == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(supplier);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public List<Supplier> list() {
		return sessionFactory.getCurrentSession().createQuery("from Supplier").list();

	}
	

	
	
	
	
}
