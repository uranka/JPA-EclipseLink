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
        
        
        <fmt:formatDate value="${invoice.invoiceDate}" pattern="yyyy-MM-dd"  var="dat"/>
        <label for="invoiceDate">Invoice date:</label>
        <input type="date" name="invoiceDate" id="invoiceDate" value="${dat}" ><br/>                   
        <!-- treba mi invoice datum u obliku yyyy-mm-dd da bi mi prikazao u date kontroli, a u kom ga ja imam
        imam u Date obliku, treba mi taj DAte u String yyyy-mm-dd da se pretvori -->
        
        <!-- proveri duzinu lineitema u invoicu, pa sve sto ima prikazi -->
        <c:if test="${fn: length(invoice.lineItems) gt 0}">      
        	<c:forEach var="lineItem" items="${invoice.lineItems}" varStatus="count">
        		<br/> product name: ${lineItem.product.name} - quantity: ${lineItem.quantity} <br/> 
        		product id: <input type="number" name="productId${count.index}" value="${lineItem.product.productId}"> 
        		product quantity: <input type="number" name="productQuantity${count.index}" value="${lineItem.quantity}">
        		<!--  
        		<a href="invoices?action=delete_line_item&amp;id=${lineItem.lineItemId}">delete line item</a> 
        		--> 
        		<input type="submit" value="Delete line item" name = "deleteLineItem${count.index}"><br/>        	      	
        	</c:forEach>
        </c:if>      
        <br/>   
        <input type="submit" value="Add line item" name = "addLineItem"><br/> 
        <br/> 
        <input type="hidden" name="numberOfLineItems" value=${fn: length(invoice.lineItems)} />                 
        <input type="submit" name="update" value="Update" >
    </form>
</body>
</html>    
    