package com.jelena.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.jelena.business.Customer;
import com.jelena.business.Invoice;

public class InvoiceDB {

    public static void insert(Invoice invoice) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(invoice);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
  /* netestirana metoda */  
    public static List<Invoice> getInvoicesByEmail (String email) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();   
    	
    	Customer customer = CustomerDB. getCustomerByEmail(email);
    	
    	String qString = "SELECT i FROM Invoice i " +
    				"WHERE i.CUSTOMER_customer_id = :cid";
    	TypedQuery<Invoice> q = em.createQuery(qString, Invoice.class);
    	q.setParameter("cid", customer.getCustomerId());
	
    	List<Invoice> invoices;
        try {
        	invoices = q.getResultList();
            if (invoices == null || invoices.isEmpty())
            	invoices = null;
        } 
        finally {
            em.close();
        }
        return invoices;    	
    }
    
    public static List<Invoice> getInvoicesByCustomer (Customer customer) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();   
    	    	    
    	String qString = "SELECT i FROM Invoice i " +
    				"WHERE i.customer = :c";
    	TypedQuery<Invoice> q = em.createQuery(qString, Invoice.class);
    	q.setParameter("c", customer);
	
    	List<Invoice> invoices;
        try {
        	invoices = q.getResultList();
            if (invoices == null || invoices.isEmpty())
            	invoices = null;
        } 
        finally {
            em.close();
        }
        return invoices;    	
    }    
	

}
