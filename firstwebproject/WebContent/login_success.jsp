<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Loginsuccess</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Language" content="ch-cn">
</head>
<body>
<%

%>
	<h4>Welcome,<%out.print(session.getAttribute("uname").toString());%>.<h4>
</body>
</html>
