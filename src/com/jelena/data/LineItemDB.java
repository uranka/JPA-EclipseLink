package com.jelena.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.jelena.business.LineItem;
import com.jelena.business.Product;

public class LineItemDB {
	
	public static List<LineItem> getLineItemsByProduct (Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();   
		String qString = "SELECT li FROM LineItem li " + 
						"WHERE li.product = :p";
		TypedQuery<LineItem> q = em.createQuery(qString, LineItem.class);
		q.setParameter("p", product);
		
		List<LineItem> lineItems;
		try {
			lineItems = q.getResultList();
			if (lineItems == null || lineItems.isEmpty()) 
				lineItems = null;
		}
		finally {
			em.close();
		}
		return lineItems;		
	}
	
	// checks if line items with given product exist
	public static boolean lineItemsExist (Product product) {
		return getLineItemsByProduct(product) != null;
	/*	
		if (getLineItemsByProduct(product) == null) {
			return false;
		}
		else {
			return true;
		}*/
	}

}
