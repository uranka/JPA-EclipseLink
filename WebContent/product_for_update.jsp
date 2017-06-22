<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Product</title>  
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />   
</head>
<body>
  <h1>Product</h1>
    <!-- ide na servlet ciji url pattern je /products, action=update_product -->
    <form action="products" method="post">
    
        <input type="hidden" name="action" value="update_product">   
             
        product id: ${product.productId} <br/>       
               
        <label>Product name:</label>
        <input type="text" name="name" value="${product.name}" 
               required><br>
               
        <label>Unit price:</label>
        <input type="number" name="unitPrice" min="0" max="100000" step="0.01" value="${product.unitPrice}"  
               required><br>    
                                  
        <input type="submit" value="Update">
    </form>  
</body>
</html>