<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Customer data</title> 
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />     
</head>
<body>
  <h1>Join our email list</h1>
    <p>To join our email list, enter your name and email address below.</p>       
    <p><i>${message}</i></p>
   
    <!-- ide na servlet ciji url pattern je /customers, action=add_customer -->
    <form action="customers" method="post">
    
        <input type="hidden" name="action" value="add_customer">   
             
        <label>Email:</label>
        <input type="email" name="email" value="${customer.email}" 
               required><br>
               
        <label>First Name:</label>
        <input type="text" name="firstName" value="${customer.firstName}" 
               required><br>
               
        <label>Last Name:</label>
        <input type="text" name="lastName" value="${customer.lastName}"  
               required><br>    
                           
        <input type="submit" value="Join Now">
    </form>
</body>
</html>    
    