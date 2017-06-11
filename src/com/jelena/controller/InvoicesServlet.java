package com.jelena.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jelena.business.Customer;
import com.jelena.business.Invoice;
import com.jelena.data.CustomerDB;
import com.jelena.data.InvoiceDB;

public class InvoicesServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException  {
		
		HttpSession session = request.getSession();					
		String url="/index.jsp";	
				
		String action = request.getParameter("action");	
		
		if(action.equals("display_empty_invoice")) {
			// get specified email
	        String emailAddress = request.getParameter("email");
	        // get customer for given email
	        Customer customer = CustomerDB.getCustomerByEmail(emailAddress);
	        // set as session attribute
	        session.setAttribute("customer", customer); 
	        
			Invoice invoice = new Invoice();
        	session.setAttribute("invoice", invoice);
			url="/invoice.jsp";
		}
		else if(action.equals("add_invoice")) {
			System.out.println("Adding invoice");
			boolean isProcessed;
			String isProcessedString = request.getParameter("isProcessed");
			// unchecked checkboxes are not part of the request.
			if (isProcessedString != null) {
				isProcessed = true;
			}
			else {
				isProcessed = false;
			}
			
			// getParameter returns the value of a request parameter as a String, 
			// or null if the parameter does not exist
			// datum vraca u obliku yyyy-mm-dd
			String invoiceDateString = request.getParameter("invoiceDate");
			System.out.println("invoiceDateString:" + invoiceDateString);
			
			// convert String to java.util.Date
			Date invoiceDate = null;
			try {
				invoiceDate = new SimpleDateFormat("yyyy-MM-dd").parse(invoiceDateString);
			} catch (ParseException e) {				
				e.printStackTrace();
			}
			
			 // get customer from session
			Customer customer  = (Customer)session.getAttribute("customer");
			
			Invoice invoice = new Invoice();
			invoice.setProcessed(isProcessed);
			invoice.setCustomer(customer);
			invoice.setInvoiceDate(invoiceDate);
			
			InvoiceDB.insert(invoice);		
			url="/index.jsp";
		}
		else if(action.equals("show_invoices")) {
			System.out.println("Showing customer's invoices");
			// get specified email
	        String emailAddress = request.getParameter("email");
	        // get customer for given email
	        Customer customer = CustomerDB.getCustomerByEmail(emailAddress);
	        request.setAttribute("customer", customer);
	        
	        // for given customer find all invoices
	        List<Invoice> invoices = InvoiceDB.getInvoicesByCustomer(customer);
	        // set list of invoices as attribute, and show it on customer_invoices.jsp
	        request.setAttribute("invoices", invoices);
	        
	        url="/customer_invoices.jsp";			
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
