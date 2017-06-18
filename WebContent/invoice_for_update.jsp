<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Invoice data</title>    
</head>
<body>
  <h1>Update invoice</h1>
       
    <p><i>${message}</i></p>
   
    <!-- ide na servlet ciji url pattern je /invoices, action=update_invoice -->
    <form action="invoices" method="post">
    
        <input type="hidden" name="action" value="update_invoice">                      
         
         
        invoice number: ${invoice.invoiceNumber} <br/>     
        
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
        
        <label for="invoiceDate">Invoice date:</label>
        <input type="date" name="invoiceDate" id="invoiceDate" 
        value="${invoice.invoiceDate}"><br/>
        <!-- trebao bi mi Date to String, value je datum u obliku yyyy-mm-dd -->               
        
        <!-- proveri duzinu lineitema u invoicu, pa sve sto ima prikazi -->
        <c:if test="${fn: length(invoice.lineItems) gt 0}">      
        	<c:forEach var="lineItem" items="${invoice.lineItems}" varStatus="count">
        		<br/> product name: ${lineItem.product.name} - quantity: ${lineItem.quantity} <br/> 
        		product id: <input type="number" name="productId${count.index}" value="${lineItem.product.productId}"> 
        		product quantity: <input type="number" name="productQuantity${count.index}" value="${lineItem.quantity}">         	      	
        	</c:forEach>
        </c:if>      
        <br/>   
        <input type="submit" value="Add line item" name = "addLineItem"><br/> 
        <br/> 
        <input type="hidden" name="numberOfLineItems" value=${fn: length(invoice.lineItems)} />                 
        <input type="submit" value="Update">
    </form>
</body>
</html>    
    