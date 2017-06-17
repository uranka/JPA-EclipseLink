package com.jelena.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.jelena.business.Product;



public class ProductDB {
	
	public static Product getProductById(long productId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Product product = em.find(Product.class, productId); 			
			return product;
		}
		finally{
			em.close();
		}
	}
	

    public static void insert(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(product);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

}
