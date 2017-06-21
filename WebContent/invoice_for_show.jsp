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
  <h1>Invoice</h1>
  <h3>Customer</h3>
    <p>Email: ${customer.email}</p>
	<p>First Name: ${customer.firstName}</p>    
	<p>Last Name: ${customer.lastName}</p> 
	
       Invoice number: ${invoice.invoiceNumber} <br/>     
        
        <c:choose>
            <c:when test="${invoice.processed==true}">
            	is processed: true            	
            </c:when>
            <c:otherwise>
            	is processed: false
            </c:otherwise>
        </c:choose>     <br/>             
        
        <fmt:formatDate value="${invoice.invoiceDate}" pattern="yyyy-MM-dd"  var="dat"/>
        Invoice date: ${dat}<br/><br/>
        Line items: <br/>
        <c:forEach var="lineItem" items="${invoice.lineItems}" varStatus="count">
        ${lineItem.product.productId} - ${lineItem.product.name} - ${lineItem.quantity} <br/>
        </c:forEach>   
   
</body>
</html>  