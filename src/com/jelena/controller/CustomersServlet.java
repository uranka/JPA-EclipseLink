package com.jelena.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jelena.business.Customer;
import com.jelena.data.CustomerDB;



public class CustomersServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException  {
		
		HttpSession session = request.getSession();		
		String url="/index.jsp";	
				
		String action = request.getParameter("action");		
		System.out.println("action = " + action);
		
		if (action == null) {
			action="display_customers";  // default action
		}
		if(action.equals("display_customers")) {
			System.out.println("About to display customers");
			List<Customer> customers = CustomerDB.getAllCustomers();
			request.setAttribute("customers", customers);
			url="/index.jsp";
		}
		else if (action.equals("display_empty_customer")) {
			Customer customer = new Customer();
        	session.setAttribute("customer", customer);
			url="/customer.jsp";
		}
		else if (action.equals("add_customer")) {
			 // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            // store data in Customer object
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            
            // validate the parameters            
            String message;            
            if (CustomerDB.emailExists(customer.getEmail())) {            	
                message = "This email address already exists.<br>" +
                          "Please enter another email address.";
                url = "/index.jsp";
            }
            else {
                message = "Thanks for joining our email list";
                url = "/index.jsp";
                CustomerDB.insert(customer);
            }                                   
            
            // get current list of customers
            List<Customer> customers = CustomerDB.getAllCustomers();
            // set as request attribute
            request.setAttribute("customers", customers);            
            request.setAttribute("message", message);
		}
		else if(action.equals("display_customer")) {			
			// get specified email
	        String emailAddress = request.getParameter("email");
	        // get customer for given email
	        Customer customer = CustomerDB.getCustomerByEmail(emailAddress);
	        // set as session attribute
	        session.setAttribute("customer", customer);            
	        // forward to customer.jsp
	        url = "/customer_for_update.jsp";	        
		}
		else if (action.equals("update_customer")) {
	        // get customer from session
			Customer customer  = (Customer)session.getAttribute("customer");
	        // get new data from request
	        String email = request.getParameter("email");
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        // update customer
	        customer.setEmail(email);
	        customer.setFirstName(firstName);
	        customer.setLastName(lastName);                   
	        // update customer in database
	        CustomerDB.update(customer);
	            
	        // get current list of customers
	        List<Customer> customers =  CustomerDB.getAllCustomers(); 
	        // set as request attribute
	        request.setAttribute("customers", customers);   
	        // forward to index.jsp
	        url = "/index.jsp";			
		}
		else if (action.equals("delete_customer")) {
			// get specified email
	        String emailAddress = request.getParameter("email");
	        // get customer for given email
	        Customer customer = CustomerDB.getCustomerByEmail(emailAddress);	
	        // check if customer has invoices, if he does do not delete him
	        if (CustomerDB.invoicesExist(customer)) {
	        	String message = "Cannot delete customer. Please delete his invoices first.";
	        	request.setAttribute("message", message);	        	
	        }
	        else {	        
	        	CustomerDB.delete(customer);	        		       
	        }
	        List<Customer> customers =  CustomerDB.getAllCustomers(); 
 	        request.setAttribute("customers", customers);
	        url = "/index.jsp";	       	
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException  {
		doPost(request, response);
	}
}
