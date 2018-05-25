package com.niit.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.domain.Cart;

//another annotations..

@Transactional
@Repository("cartDAO") // will create instance of CartDAOImpl and the name will cartDAOImpl
public class CartDAOImpl implements CartDAO {

	// first - inject hibernate session .

	@Autowired // session factory is automatically injected in this class
	private SessionFactory sessionFactory;

	@Autowired
	private Cart cart;
	
	Logger log = LoggerFactory.getLogger(CartDAOImpl.class);

	public boolean save(Cart cart) {
		// STORE IN THE DATA BASE

		try {

			sessionFactory.getCurrentSession().save(cart);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean update(Cart cart) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(cart);
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	/*public Cart get(int emailID) {
		// it will set the record based onemailID and store in Cart class
		return sessionFactory.getCurrentSession().get(Cart.class, emailID);

	}

	public boolean delete(String emailID) {
		try {
			cart = get(emailID);
			if (cart == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(cart);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
*/
	public List<Cart> list(String emailID) {
		// return sessionFactory.getCurrentSession().createQuery("from Cart").list();
		return sessionFactory.getCurrentSession().createCriteria(Cart.class).add(Restrictions.eq("emailID", emailID))
				.list();

	}

	public boolean update(String emailID) {
		log.debug("Starting of the method update");
		log.debug("Going to palace order of " + emailID);
		String hql = "update Cart set status = 'O' where emailid='" + emailID + "'";

		log.info("The given query :" + hql);
		
		try 
		{
			sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
			log.debug("Ending of the method update");
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}

	
	public boolean delete(int id) {
		String hql = "delete from Cart where id='" + id + "'";
		try {
			sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public Cart get(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Cart.class, id);
	}
	
	public boolean deleteCart(String emailID) {

		String hql = "delete from Cart where emailID='" + emailID + "'";
		try {
			sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
