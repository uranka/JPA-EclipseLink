<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Invoice data</title>    
</head>
<body>
 	<h1>Invoice data</h1>        
   
    <!-- ide na servlet ciji url pattern je /invoices, action=add_invoice -->
    <form action="invoices" method="post">
    
        <input type="hidden" name="action" value="add_invoice">        
		
		<p>Email: ${customer.email}</p>
		<p>First Name: ${customer.firstName}</p>    
		<p>Last Name: ${customer.lastName}</p>                 

       <c:choose>
            <c:when test="${invoice.processed==true}">
            	<label for="isProcessed1">is processed:</label>
                <input type="checkbox" name="isProcessed" id="isProcessed1" 
                       checked/>
            </c:when>
            <c:otherwise>
            	<label for="isProcessed2">is processed:</label>
                <input type="checkbox" name="isProcessed" id="isProcessed2" 
                       />
            </c:otherwise>
        </c:choose>     <br/>                   
        
        <fmt:formatDate value="${invoice.invoiceDate}" pattern="yyyy-MM-dd"  var="dat"/>
        <label for="invoiceDate">Invoice date:</label>
        <input type="date" name="invoiceDate" id="invoiceDate" value="${dat}"><br/>                  
        
        <!-- proveri duzinu lineitema u invoicu, pa sve sto ima prikazi -->
        <c:if test="${fn: length(invoice.lineItems) gt 0}">       
        <c:forEach var="lineItem" items="${invoice.lineItems}" varStatus="count">
        	<br/>  
        	<!--      
        	product id: <input type="number" name="productId${count.index}" value="${lineItem.product.productId}"> 
        	--> 
			<select name = "productId${count.index}" >
        		<c:forEach var="product" items="${products}" >
        			<c:choose>
        				<c:when test ="${product.productId eq  lineItem.product.productId}">
        					<option value="${product.productId}" selected> 
        						${product.productId} - ${product.name}</option>
        						
        				</c:when>
        				<c:otherwise>
        				<option value="${product.productId}"> 
        						${product.productId} - ${product.name}</option>
        				</c:otherwise>
        			</c:choose>        			        			
        		</c:forEach>
        	</select>
        	product quantity: <input type="number" name="productQuantity${count.index}" value="${lineItem.quantity}">         	      	      	 
        </c:forEach>
        </c:if>
               
		<input type="hidden" name="numberOfLineItems" value=${fn: length(invoice.lineItems)} />
		<br/>                              
        <input type="submit" value="Add line item" name = "addLineItem"><br/> 
        <input type="submit" value="Add invoice" name = "addInvoice">
        
    </form>
</body>
</html>    
    