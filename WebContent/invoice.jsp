<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Invoice data</title>    
</head>
<body>
 	<h1>Invoice data</h1>        
   
    <!-- ide na servlet ciji url pattern je /invoices, action=add_invoice -->
    <form action="invoices" method="post">
    
        <input type="hidden" name="action" value="add_invoice">        
		
		<p>Email: ${customer.email}</p>
		<p>First Name: ${customer.firstName}</p>    
		<p>Last Name: ${customer.lastName}</p>                 
             
        <label for="isProcessed">is processed:</label>
        <input type="checkbox" name="isProcessed" value="yes" id="isProcessed"><br/>    
        
        <label for="invoiceDate">Invoice date:</label>
        <input type="date" name="invoiceDate" id="invoiceDate"><br/>                  
                           
        <input type="submit" value="Add invoice">
    </form>
</body>
</html>    
    