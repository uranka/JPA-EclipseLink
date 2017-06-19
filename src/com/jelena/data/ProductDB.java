package com.jelena.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.jelena.business.Customer;
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

    public static List<Product> getAllProducts() {    	
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p from Product p";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);

        List<Product> products;
        try {
        	products = q.getResultList();
            if (products == null || products.isEmpty())
            	products = null;
        } 
        finally {
            em.close();
        }
        return products;
  } 
    
    
    public static void update (Product product) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
        	em.merge(product);
        	trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void delete (Product product) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();     
        try {
        	em.remove(em.merge(product));
        	trans.commit();
        }catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }    
    
}
