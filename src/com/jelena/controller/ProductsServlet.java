package com.jelena.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.jelena.business.Product;
import com.jelena.data.ProductDB;

public class ProductsServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException  {
		
		
		HttpSession session = request.getSession();					
		String url="/index.jsp";	
		
		String action = request.getParameter("action");	
		if(action.equals("display_empty_product")) {			
			Product product = new Product();
        	session.setAttribute("product", product);
			url="/product_for_update.jsp";
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
		
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException  {
		doPost(request, response);
	}	

}
