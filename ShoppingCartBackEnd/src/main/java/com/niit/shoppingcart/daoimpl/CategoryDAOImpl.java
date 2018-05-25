package com.niit.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

//another annotations..

@Transactional
@Repository("categoryDAO") // will create instance of CategoryDAOImpl and the name will categoryDAOImpl
public class CategoryDAOImpl implements CategoryDAO {

	// first - inject hibernate session .

	@Autowired // session factory is automatically injected in this class
	private SessionFactory sessionFactory;

	@Autowired
	private Category category;

	public boolean save(Category category) {
		// STORE IN THE DATA BASE

		try {
			
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean upadte(Category category) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(category);
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	public Category get(String emailID) {
		// it will set the record based onemailID and store in Category class
		return sessionFactory.getCurrentSession().get(Category.class, emailID);

	}

	public boolean delete(String emailID) {
		try {
			category = get(emailID);
			if (category == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(category);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public List<Category> list() {
		//return sessionFactory.getCurrentSession().createQuery("from Category").list();
		return (List<Category>)
				sessionFactory.getCurrentSession()
				.createCriteria(Category.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

	}
	
	
	
	
	
}
