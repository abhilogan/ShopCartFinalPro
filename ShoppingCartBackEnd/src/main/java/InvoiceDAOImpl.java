

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.InvoiceDAO;
import com.niit.shoppingcart.domain.invoice;

@Repository("invoiceDAO")
@Transactional

public class InvoiceDAOImpl implements InvoiceDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	
	public boolean save(invoice invoice) {
		
		try
		{
			sessionFactory.getCurrentSession().save(invoice);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
	}


}
