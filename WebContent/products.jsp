<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Products</title>    
</head>
<body>
  <h1>Products</h1>
	<table border="1">
		<tr>
			<th>product id</th>
			<th>product name</th>
			<th>unit price</th>
			<th>update</th>
			<th>delete</th>			
		</tr>
		<c:forEach var="product"  items="${products}">  
			<tr>              
         		<td>${product.productId}</td>
         		<td>${product.name}</td>
         		<td>${product.unitPrice}</td>          		
          		<td><a href="products?action=display_product&amp;id=${product.productId}">update</a></td>
          		<td><a href="products?action=delete_product&amp;id=${product.productId}">delete</a></td>
        	</tr>        
     	</c:forEach>
     </table>          
</body>
</html>