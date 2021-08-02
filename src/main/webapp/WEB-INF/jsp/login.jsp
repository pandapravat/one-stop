<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authenticate</title>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>

<center>
<div class="divborder halfWidth">
<form action="<%=request.getContextPath()%>/processLogin.nos" method="post">
<table>
	<thead><div class="heading">Login to nielsen one stop</div></thead>
	<tr><td>User Name</td><td><input type="text" name="user" /></td></tr>
	<tr><td>Password</td><td><input type="password" name="pass" /></td></tr>
	<tr><td><input type="submit" class="blueBut"  value="Submit"/></td><td><input type="reset" class="blueBut"  value="Clear"/></td></tr>
</table>
</form>
</div>
</center>
<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>