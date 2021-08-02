<%@page import="java.util.Set"%>
<%@page import="com.pravatpanda.onestop.bean.URLEntry"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<%
if("glory".equals(request.getParameter("style"))){%>
	<LINK REL=StyleSheet HREF="styles/glory.css" TYPE="text/css" >
	<%
} else {%>
	<LINK REL=StyleSheet HREF="styles/basic.css" TYPE="text/css" >
	<%
}%>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="description" content="All urls" />
	<meta name="keywords" content="one-stop, one stop, useful urls" />
	<script type="text/javascript" src="./js/jquery.min.js"></script>
</head>
<body>
<%
    String successMsg = (String)request.getAttribute("SCSS_MSG");
    String errorMsg = (String)request.getAttribute("ERR_MSG");

	boolean isAdmin = false;
	Boolean authorised = (Boolean)request.getSession().getAttribute("AUTH");
	if(null != authorised) {
		isAdmin = authorised;
	}
	if(null != errorMsg) {
		%>
		<div class="errMsg"><%=errorMsg %></div>
		<%
	}
	if(null != successMsg) {
		%>
		<div class="successMsg"><%=successMsg %></div>
		<%
	}
%>
<div> 
		<div>
			<% 
			if(isAdmin) {
					String name = (String)request.getSession().getAttribute("USERNAME");
					%> 
						Welcome, <b><%=name %></b> [<a href="<%=request.getContextPath()%>/logout.nos">Logout</a>]
					<%
									
			} else {
				%>
					<a href="<%=request.getContextPath()%>/showLogin.nos">Admin Login</a>
				<%
			}
			%>
			
		</div>
		<!-- div style="position:absolute;left:10px;top:150px;width:100px;border:1px solid black;">
			<div>Choose A Style</div>
			<div>Choose A Style</div>
			<div>Choose A Style</div>
			<div>Choose A Style</div>
		</div-->
		<div class="center pageTitle">
			<div class="pageHead">Welcome To One-Stop</div>
			<div class="pageQuote">Your ultimate destination to all Url access !</div>
		</div>
		<form action="<%=request.getContextPath()%>/displayAddUI.nos" id="aForm">
			<div class="right">
				<div>
				<% if(isAdmin) { %>
					<input type="submit" class="blueBut" value="Add New Entry" id="B1" />
					<%}
					%>
					<input type="button" class="blueBut" value="Home" onclick="javascript:location.replace('<%=request.getContextPath() %>/showResult.nos')" />
				</div>
			</div>
		</form>
</div>
</body>
</html>