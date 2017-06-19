package com.jelena.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jelena.business.Customer;



public class CustomerDB {
	
	public static Customer getCustomerById(long customerId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Customer customer = em.find(Customer.class, customerId); 
			// find is not mutating operation, so it may be called any time
			// with or without transaction
			return customer;
		}
		finally{
			em.close();
		}
	}
	

    public static void insert(Customer customer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(customer);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void update (Customer customer) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
        	em.merge(customer);
        	trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void delete (Customer customer) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();     
        try {
        	em.remove(em.merge(customer));
        	trans.commit();
        }catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static Customer getCustomerByEmail(String email) {    	
		EntityManager em = DBUtil.getEmFactory().createEntityManager();		
		String qString = "SELECT c FROM Customer c " +
					"WHERE c.email = :email";
		
		TypedQuery<Customer> q = em.createQuery(qString, Customer.class);		
		q.setParameter("email", email);	
				
		Customer customer = null;
		try {			
			customer = q.getSingleResult();
		} catch (NoResultException e) {			
            System.out.println(e);            
        } finally {
            em.close();
        }
		return customer;
	}
    
    public static boolean emailExists(String email) {    	
    	Customer customer = getCustomerByEmail(email);
    	return customer != null;
    }
    
    public static List<Customer> getAllCustomers() {    	
    	  EntityManager em = DBUtil.getEmFactory().createEntityManager();
          String qString = "SELECT c from Customer c";
          TypedQuery<Customer> q = em.createQuery(qString, Customer.class);

          List<Customer> customers;
          try {
        	  customers = q.getResultList();
              if (customers == null || customers.isEmpty())
            	  customers = null;
          } 
          finally {
              em.close();
          }
          return customers;
    }
    
    public static boolean invoicesExist (Customer customer) {
    	if (InvoiceDB.getInvoicesByCustomer(customer) == null) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
}
