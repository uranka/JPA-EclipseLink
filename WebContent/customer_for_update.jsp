<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Customer data</title>    
</head>
<body>
  <h1>Update customer</h1>
       
    <p><i>${message}</i></p>
   
    <!-- ide na servlet ciji url pattern je /customers, action=update_customer -->
    <form action="customers" method="post">
    
        <input type="hidden" name="action" value="update_customer">   
             
        <label>Email:</label>
        <input type="email" name="email" value="${customer.email}" 
               required><br>
               
        <label>First Name:</label>
        <input type="text" name="firstName" value="${customer.firstName}" 
               required><br>
               
        <label>Last Name:</label>
        <input type="text" name="lastName" value="${customer.lastName}"  
               required><br>    
                           
        <input type="submit" value="Update">
    </form>
</body>
</html>    
    