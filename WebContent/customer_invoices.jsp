<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:forEach var="invoice"  items="${invoices}">                
         <p>number: ${invoice.invoiceNumber}, processed: ${invoice.processed}</p>
     </c:forEach>           
</body>
</html>    
    