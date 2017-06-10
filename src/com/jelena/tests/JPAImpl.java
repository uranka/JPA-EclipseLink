package com.jelena.tests;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jelena.business.Customer;
import com.jelena.data.CustomerDB;

public class JPAImpl {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-EclipseLink");
        System.out.println("Is opened connection :: "+ factory.createEntityManager().isOpen());
        
       
        Customer customer = new Customer();
        customer.setFirstName("Jeca");
        customer.setLastName("Gavanski");
        customer.setEmail("jgggg");
        CustomerDB.insert(customer);
        
        
        List<Customer> customers = CustomerDB.getAllCustomers();
        System.out.println(customers.get(0).getEmail());
        
	}

}
