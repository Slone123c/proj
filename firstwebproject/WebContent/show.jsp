<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Table</title>
</head>
<center>
<body style="text-align:center">
<%
	//For displaying data
	session.getAttribute("list");
%>
<div style="height:50px">
	
<hr/>
<table>
	<tr>
		<td>CUSTOMER_SIN_NUMBER</td>
		<td>FULL NAME</td>
		<td>ADDRESS</td>
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
		<td><a href="<%=path%>/UpdateCustomerServlet?customer_sin_number=${customer.customer_sin_number}">Modify</a></td>
		<td><a href="<%=path%>/DeleteCustomerBySinServlet?customer_sin_number=${customer.customer_sin_number}"onclick="if(confirm('Do you want to delete'+'${customer.customer_sin_number}'+'?')==false)return false">Delete</a></td>
	</tr>	
	</c:forEach>
	
	</table>
</body>
</center>
</html>
