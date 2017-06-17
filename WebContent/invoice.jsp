<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
             
        <label for="isProcessed">is processed:</label>
        <input type="checkbox" name="isProcessed" value="yes" id="isProcessed"><br/>    
        
        <label for="invoiceDate">Invoice date:</label>
        <input type="date" name="invoiceDate" id="invoiceDate"><br/>                  
        
        <!-- proveri duzinu lineitema u invoicu, pa sve sto ima prikazi -->
        <c:if test="${fn: length(invoice.lineItems) gt 0}">
        <!-- 
        	<c:forEach begin="0" end="${fn:length(invoice.lineItems)-1}" varStatus="status">
        	${status.count}
        	</c:forEach>
        -->
        <c:forEach var="lineItem" items="${invoice.lineItems}">
        	product name: ${lineItem.product.name} - quantity: ${lineItem.quantity} <br/>
        	      	 
        </c:forEach>
        </c:if>
        product id: <input type="number" name="productId" > 
        product quantity: <input type="number" name="productQuantity">  
                           
        <input type="submit" value="Add line item" name = "addLineItem"><br/> 
        <input type="submit" value="Add invoice" name = "addInvoice">
        
    </form>
</body>
</html>    
    