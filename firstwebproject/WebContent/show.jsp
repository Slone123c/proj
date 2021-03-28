<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Table</title>
</head>
<body>
<%
	//For displaying data
	session.getAttribute("list");
%>



<table>
	<tr>
		<td>customer_SIN_number</td>
		<td>fullname</td>
		<td>address</td>
	</tr>
	<!-- 
		for(String str:customerList){
		}
	 -->
	<c:forEach items="${list}" var="customer">
		<tr>
		<td>${customer.customer_sin_number}</td>
		<td>${customer.full_name}</td>
		<td>${customer.customer_address}</td>
	</tr>	
	</c:forEach>
	
	</table>
</body>
</html>
