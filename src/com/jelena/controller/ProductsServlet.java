package com.jelena.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jelena.business.Customer;
import com.jelena.business.Product;
import com.jelena.data.CustomerDB;
import com.jelena.data.LineItemDB;
import com.jelena.data.ProductDB;

public class ProductsServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException  {
		
		
		HttpSession session = request.getSession();					
		String url="/index.jsp";	
		
		String action = request.getParameter("action");		
		System.out.println("action = " + action);
		
		if (action == null) {
			action="display_products";  // default action
		}
		
		if (action.equals("display_products")){
			System.out.println("About to display products");
			List<Product> products = ProductDB.getAllProducts();
			request.setAttribute("products", products);
			url="/products.jsp";			
		}
		else if(action.equals("display_empty_product")) {			
			Product product = new Product();
        	session.setAttribute("product", product);
			//url="/product_for_update.jsp";
        	url="/product.jsp";
		}
		else if(action.equals("add_product")) {			
			 // get parameters from the request
            String name = request.getParameter("name");
            BigDecimal unitPrice = new BigDecimal (request.getParameter("unitPrice"));            
            
            // store data in Product object
            Product product = new Product();
            product.setName(name);
            product.setUnitPrice(unitPrice);
            
            ProductDB.insert(product);
            url = "/index.jsp";         // idi na products.jsp
           
		}
/*****************************************************************************/	
// to update product first display it to the user so that he can change data
// then update it in the database		
		else if (action.equals("display_product")) {
			// get product id
			long productId = Long.parseLong(request.getParameter("id"));
			// get product with that id
			Product product = ProductDB.getProductById(productId);
			// set it as attribute in the session
			session.setAttribute("product", product);
			// forward to product.jsp which shows that product data
			url="/product_for_update.jsp";			
		}
		else if (action.equals("update_product")) {
			// get product from session
			Product product = (Product)session.getAttribute("product");
			// get new data from request
			String name = request.getParameter("name");
			BigDecimal unitPrice = new BigDecimal (request.getParameter("unitPrice"));       
			// update product
			product.setName(name);
			product.setUnitPrice(unitPrice);
			// update product in database
			ProductDB.update(product);			
			url="/index.jsp";			
		}
/*****************************************************************************/	
		else if (action.equals("delete_product")) {
			// get product id
			long productId = Long.parseLong(request.getParameter("id"));
			Product product = ProductDB.getProductById(productId);
			// check if it can be deleted
			// product cannot be deleted if 
			// it is included in any invoice line item 
			if (LineItemDB.lineItemsExist(product)) {
				String message = " Cannot delete product. " +
						"Please delete all invoices on which product appears.";
				request.setAttribute("message", message);				
			}	
			else {
				ProductDB.delete(product);
			}
			url="/index.jsp";
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
