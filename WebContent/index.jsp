 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Customers database</title>
</head>
<body>
	<h1>Customers</h1>
	<p>${message}</p>
	
	<table border="1">
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="customer"  items="${customers}">
			<tr>
				<td>${customer.firstName}</td>
				<td>${customer.lastName}</td>
				<td>${customer.email}</td>
				<td><a href="customers?action=display_customer&amp;email=${customer.email}">Update</a></td>
    			<td><a href="customers?action=delete_customer&amp;email=${customer.email}">Delete</a></td>
    			<td><a href="invoices?action=display_empty_invoice&amp;email=${customer.email}">Add invoice</a></td>
    			<td><a href="invoices?action=show_invoices&amp;email=${customer.email}">Show invoices</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- poziv doGet , action=null -->
	<p><a href="customers">Show all customers</a></p>  
	
	<!-- prikazi formu koja prikuplja podatke o musteriji -->
	<p><a href="customers?action=display_empty_customer">Add new customer</a></p> 
	
	<!-- prikazi formu koja prikuplja podatke o proizvodu -->
	<p><a href="products?action=display_empty_product">Add new product</a></p>
	
	
</body>
</html>