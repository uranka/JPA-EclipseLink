<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Invoices</title>    
</head>
<body>
 	<h1>Customer invoices</h1>        
 
	<p>Email: ${customer.email}</p>
	<p>First Name: ${customer.firstName}</p>    
	<p>Last Name: ${customer.lastName}</p> 
	<p>Invoices:</p>
	<table border="1">
		<tr>
			<th>invoice number</th>
			<th>processed</th>
			<th>invoice date</th>
			<th>update</th>
			<th>delete</th>			
		</tr>
		<c:forEach var="invoice"  items="${invoices}">  
			<tr>              
         		<td>${invoice.invoiceNumber}</td>
         		<td>${invoice.processed}</td>
          		<td><fmt:formatDate value="${invoice.invoiceDate}" pattern="dd.MM.yyyy." /></td> 
          		<td>update</td>
          		<td><a href="invoices?action=delete_invoice&amp;id=${invoice.invoiceNumber}">delete</a></td>
        	</tr>        
     	</c:forEach>
     </table>           
</body>
</html>    
    