<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Product data</title>
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />      
</head>
<body>
 	<h1>Product data</h1>        
   
    <!-- ide na servlet ciji url pattern je /products, action=add_product -->
    <form action="products" method="post">
    
        <input type="hidden" name="action" value="add_product">        
		
		Name:
		<input type="text" name="name" ><br/>
		Unit price:
  		<input type="number" name="unitPrice" min="0" max="100000" step="0.01" value="1"><br/>              
                           
        <input type="submit" value="Add product">
    </form>
</body>
</html>    
    