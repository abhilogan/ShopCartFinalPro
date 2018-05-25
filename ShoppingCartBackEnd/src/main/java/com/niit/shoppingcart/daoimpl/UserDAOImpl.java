package com.niit.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;


//another annotations..

@Transactional
@Repository("userDAO") // will create instance of UserDAOImpl and the name will userDAOImpl
public class UserDAOImpl implements UserDAO {

	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	// first - inject hibernate session .

	@Autowired // session factory is automatically injected in this class
	private SessionFactory sessionFactory;

	@Autowired
	private User user;

	public boolean save(User user) {
		log.debug("Starting of the save method");
		// STORE IN THE DATA BASE

		try {
			user.setRole('C');
			user.setRegisteredDate(new java.util.Date());
			sessionFactory.getCurrentSession().save(user);
			log.debug("Ending of the save method");
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean upadte(User user) {
		log.debug("Starting of the update method");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(user);
			log.debug("Ending of the update method");
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			log.error("error occoured in update method " + e.getMessage());
			return false;
		}
	}

	public User get(String emailID) {
		log.debug("Starting of the get method");
		// it will set the record based onemailID and store in User class
		return sessionFactory.getCurrentSession().get(User.class, emailID);

	}

	public boolean delete(String emailID) {
		log.debug("Starting of the delete method");
		try {
			user = get(emailID);
			if (user == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(user);
			log.debug("Ending of the delete method");

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("error occoured in delete method " + e.getMessage());
			return false;
		}

	}

	public List<User> list() {
		log.debug("Starting of the list method");
		return sessionFactory.getCurrentSession().createQuery("from User").list();

	}
	
	public User validate(String emailID , String password)
	{
		log.debug("Starting of the validate method");
		log.info(" user " + emailID + " trying to login");
		user = (User) sessionFactory.getCurrentSession().createCriteria(User.class)
		.add(Restrictions.eq("emailID", emailID))
		.add(Restrictions.eq("pwd", password)).uniqueResult();
		
		
		return user;
	}
	
	
	
	
	
	
}
