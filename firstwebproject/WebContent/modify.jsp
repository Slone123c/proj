<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    String path=request.getContextPath();
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=path%>/DoUpdateCustomerServlet">
	<input type="text" name="customer_sin_number" readonly="readonly" value="${customer.customer_sin_number}">
	<input type="password" name="pwd" value="${customer.pwd}">
	<input type="text" name="full_name" value="${customer.full_name}">
	<input type="text" name="customer_address" value="${customer.customer_address}">
	<input type="submit" value="confirm">
	</form>
</body>
</html>