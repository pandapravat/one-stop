<%@page import="com.pravatpanda.onestop.bean.URLEntry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Entry</title>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<center>
<div class="divborder halfWidth">
<form action="<%=request.getContextPath()%>/processUpdate.nos"
	id="aForm" method="post">
<table>
	<%
		boolean isSplUser = false;
		URLEntry e = (URLEntry) request.getAttribute("URLENTRY");
		Boolean spluserB = (Boolean) session.getAttribute("SPLUSER");
		if(null != spluserB) isSplUser = spluserB.booleanValue();
	%>
	<thead><div class="heading">Update URL Entry</div></thead>
	<tr>
		<td>
		Id
		</td>
		<td><input type="text" name="id" value="<%=e.getId()%>"
			readonly="readonly" /></td>
	</tr>

	<tr>
		<td>
		Groupname
		</td>
		<td><input type="text" name="Gname" value="<%=e.getGroupName()%>"
			readonly="readonly" /><br>
		</td>
	</tr>

	<tr>
		<td>
		URL
		</td>
		<td><input type="text" name="URLname" value="<%=e.getUrl()%>" />
		</td>
	</tr>

	<tr>
		<td>
		Text
		</td>
			<td><input type="text" name="Textname" value="<%=e.getText()%>" />
		</td>
	</tr>

	<tr>
		<td>
			IsLink
		</td>
		<td><select name="IsLinkname">
		<%	if(e.isIslink()) {
			%>
				<option selected="selected">true</option>
				<option>false</option>
			<%
		} else {
			%>
				<option>true</option>
				<option selected="selected">false</option>
			<%
		}
			%>
		
			
		</select></td>
	</tr>
	<% if(isSplUser) { 
	%>
		<tr>
			<td>
				Private to Me
			</td>
			<td><select name="isSpl">
			<%	if(e.isSpl()) {
				%>
					<option selected="selected">true</option>
					<option>false</option>
				<%
			} else {
				%>
					<option>true</option>
					<option selected="selected">false</option>
				<%
			}
				%>
			
				
			</select></td>
		</tr>
	<%} %>
	<tr>
		<td align="center"><input type="submit"
			value="Update" id="B1" />
		</td>
		<td align="center">
			<input type="button" class="blueBut" value="Go Home" onclick="javascript:location.replace('<%=request.getContextPath() %>/showResult.nos')" />
		</td>
	</tr>
</table>

</form>
</div>
</center>
<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>