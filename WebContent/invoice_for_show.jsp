<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Invoice data</title> 
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />   
</head>
<body>  

  <h1>Invoice ${invoice.invoiceNumber}</h1>
 
  <h3>Customer</h3>
 
    <p>Email: ${customer.email}</p>
	<p>First Name: ${customer.firstName}</p>    
	<p>Last Name: ${customer.lastName}</p> 	     
  
	<br/>
	<p>
        <c:choose>
            <c:when test="${invoice.processed==true}">
            	is processed: true            	
            </c:when>
            <c:otherwise>
            	is processed: false
            </c:otherwise>
        </c:choose>                
     </p>
     <fmt:formatDate value="${invoice.invoiceDate}" pattern="yyyy-MM-dd"  var="dat"/>
     <p>Invoice date: ${dat}</p>
   	 <br/>
        
        <p>Line items: </p>
        <table>
        	<tr>
        		<th>Product Id</th>
        		<th>Product Name</th>
        		<th>Unit Price($)</th>
        		<th>Quantity</th>
        	</tr>
        
        <c:forEach var="lineItem" items="${invoice.lineItems}" varStatus="count">
        	<tr>
        		<td>${lineItem.product.productId}</td>
        		<td>${lineItem.product.name}</td>
        		<td>${lineItem.product.unitPrice}</td>
        		<td>${lineItem.quantity}</td>
        	</tr>       
        </c:forEach>        
        </table>        
               
        <p id="total"><strong>TOTAL: ${total} $ </strong></p>
</body>
</html>  