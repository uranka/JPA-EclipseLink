package com.jelena.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jelena.business.Customer;
import com.jelena.business.Invoice;
import com.jelena.business.LineItem;
import com.jelena.business.Product;
import com.jelena.data.CustomerDB;
import com.jelena.data.InvoiceDB;
import com.jelena.data.ProductDB;

public class InvoicesServlet extends HttpServlet  {

	@Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException  {
		
		HttpSession session = request.getSession();					
		String url="/index.jsp";	
				
		// get action from request
		String action = request.getParameter("action");	
		
/*****************************************************************************/
// To add invoice first display empty form to enter invoice data
// then add it to the database				
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
			// get invoice from session
			Invoice invoice = (Invoice)session.getAttribute("invoice");			
			
			//get new data from request and set that data into invoice
			boolean isProcessed;
			String isProcessedString = request.getParameter("isProcessed");			
			if (isProcessedString != null) {
				isProcessed = true;
			}
			else {
				isProcessed = false;
			}						
			String invoiceDateString = request.getParameter("invoiceDate");
			System.out.println("invoiceDateString:" + invoiceDateString);
			
			// convert String to java.util.Date
			Date invoiceDate = stringToDateConverter(invoiceDateString);
			
			invoice.setProcessed(isProcessed);			
			invoice.setInvoiceDate(invoiceDate);	
			
			// get customer from session
			Customer customer = (Customer) session.getAttribute("customer");
			invoice.setCustomer(customer);
			
			int numberOfLineItems = Integer.parseInt(request.getParameter("numberOfLineItems"));			
			System.out.println("numberOfLineItems = "  + numberOfLineItems);
			List<LineItem> lineItems = new ArrayList<LineItem>();				
			for (int i = 0; i < numberOfLineItems; i++) {
				String pid = "productId" + i;
				String pq = "productQuantity" + i;
				int productId = Integer.parseInt(request.getParameter(pid));
				int productQuantity = Integer.parseInt(request.getParameter(pq));
				LineItem lineItem = new LineItem();
				lineItem.setProduct(ProductDB.getProductById(productId));
				lineItem.setQuantity(productQuantity);
				lineItems.add(lineItem);
			}
			invoice.setLineItems(lineItems);
			
			// check whether add line item or update submit button was pressed
			if (request.getParameter("addLineItem") != null) {
				System.out.println("adding line item");
				
				// calculate current total, ne racunaj onog praznog,
				// proveri da li ima elemenata u listi
				BigDecimal total = new BigDecimal(0);
				if  (!invoice.getLineItems().isEmpty()) {				
					for (LineItem li : invoice.getLineItems()) {
						li.updateTotalPrice();
						System.out.println("getTotalPrice=" + li.getTotalPrice());
						total = total.add(li.getTotalPrice());
						System.out.println("partial_total = " + total);
					}
				}
				System.out.println("total = " + total);
				session.setAttribute("total", total);				
				
				
				// add new empty line item to invoice
				LineItem lineItem = new LineItem();				
				invoice.addLineItem(lineItem);
				// set invoice into session
				session.setAttribute("invoice", invoice);
				
				// postavi sve producte u session
				List<Product> products = ProductDB.getAllProducts();
				session.setAttribute("products", products);				
				
				// forward to invoice page for further updating
				url="/invoice.jsp";	
			}
			else {
				System.out.println("adding invoice");
				// insert invoice into database
				InvoiceDB.insert(invoice);	
				// forward to index page
				url="/index.jsp";
			}
		}	
/***********************************************************************************/
		
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
/*****************************************************************************/		
		else if(action.equals("delete_invoice")) {
			System.out.println("Deleting invoice");
			Long invoiceNumber = Long.parseLong(request.getParameter("id"));
			System.out.println("Deleting invoice number" + invoiceNumber);
			Invoice invoice = InvoiceDB.getInvoiceById(invoiceNumber);
			InvoiceDB.delete(invoice);
			url="/index.jsp";
		}
/*****************************************************************************/		
		else if(action.equals("display_invoice")) {			
			Long invoiceNumber = Long.parseLong(request.getParameter("id"));
			System.out.println("Displaying invoice number " + invoiceNumber);
			Invoice invoice = InvoiceDB.getInvoiceById(invoiceNumber);
			session.setAttribute("invoice", invoice);
			
			BigDecimal total = new BigDecimal(0);
			for (LineItem li : invoice.getLineItems()) {
				li.updateTotalPrice();
				System.out.println("getTotalPrice=" + li.getTotalPrice());
				total = total.add(li.getTotalPrice());
				System.out.println("partial_total = " + total);
			}
			System.out.println("total = " + total);
			session.setAttribute("total", total);
			
			List<Product> products = ProductDB.getAllProducts();
			session.setAttribute("products", products);
			url="/invoice_for_update.jsp";
		}
		
		else if(action.equals("update_invoice")) {
			// get invoice from session
			Invoice invoice = (Invoice)session.getAttribute("invoice");			
			
			//get new data from request and set that data into invoice
			boolean isProcessed;
			String isProcessedString = request.getParameter("isProcessed");			
			if (isProcessedString != null) {
				isProcessed = true;
			}
			else {
				isProcessed = false;
			}						
			String invoiceDateString = request.getParameter("invoiceDate");
			System.out.println("invoiceDateString:" + invoiceDateString);
			
			// convert String to java.util.Date
			Date invoiceDate = stringToDateConverter(invoiceDateString);
			
			invoice.setProcessed(isProcessed);			
			invoice.setInvoiceDate(invoiceDate);					
			
			int numberOfLineItems = Integer.parseInt(request.getParameter("numberOfLineItems"));			
			System.out.println("numberOfLineItems = "  + numberOfLineItems);
			List<LineItem> lineItems = new ArrayList<LineItem>();				
			for (int i = 0; i < numberOfLineItems; i++) {
				String pid = "productId" + i; 
				String pq = "productQuantity" + i;
				int productId = Integer.parseInt(request.getParameter(pid));
				int productQuantity = Integer.parseInt(request.getParameter(pq));				
				LineItem lineItem = new LineItem();
				lineItem.setProduct(ProductDB.getProductById(productId));
				lineItem.setQuantity(productQuantity);
				lineItems.add(lineItem);
			}
			invoice.setLineItems(lineItems);	
			
			// check whether add line item or update submit button was pressed
			if (request.getParameter("addLineItem") != null) {
				System.out.println("adding line item");
				// add new empty line item to invoice
				LineItem lineItem = new LineItem();				
				invoice.addLineItem(lineItem);
				// set invoice into session
				session.setAttribute("invoice", invoice);	
				// forward to invoice_for_update page for further updating
				url="/invoice_for_update.jsp";	
			}
			else if (request.getParameter("update") != null) {
				System.out.println("updating invoice");
				// save updated invoice into database
				InvoiceDB.update(invoice);	
				// forward to index page
				url="/index.jsp";						
			}
			else { // delete line item,  ukloni iz liste tog jednog za koga postoji
				// request parametar 
				// kada uradim update invoice obrisace se i taj line item
				System.out.println("deleting line item");
				for (int i = 0; i < numberOfLineItems; i++) {					
					String dli = "deleteLineItem" + i;
					if (request.getParameter(dli) != null) {
						System.out.println("found line item that i should remove, line item i = " + i);
						// remove lineItem with index i
						LineItem lineItem = lineItems.get(i);
						lineItems.remove(lineItem);	
						break;									
					}					
				}
				invoice.setLineItems(lineItems);
				// set invoice into session
				session.setAttribute("invoice", invoice);
				url="/invoice_for_update.jsp";
			}
		}	
/***********************************************************************************/			
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

// return date if conversion from string is possible, otherwise returns null	
	public static Date stringToDateConverter(String dateString){
		final String DATE_FORMAT = "yyyy-MM-dd";
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
		}
		catch(ParseException e) {
			e.printStackTrace();			
		}
		return date;
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException  {
		doPost(request, response);
	}
}
