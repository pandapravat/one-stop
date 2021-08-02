<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Entry</title>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<form action="<%=request.getContextPath()%>/addNewEntry.nos" id="aForm" method="post">
<center>
<%
@SuppressWarnings("unchecked")
List<String> groupNames = (List<String>)request.getAttribute("GROUPNAMES");
boolean isSplUser = false;
Boolean spluserB = (Boolean) session.getAttribute("SPLUSER");
if(null != spluserB) isSplUser = spluserB.booleanValue();
%>
<div class="divborder halfWidth">
<table>
	<thead><div class="heading">Add URL Entry</div></thead>
	
	<tr>
		<td>Groupname</td>
		<td><select name="GnameLst">
		<option>Select A Group</option>
		<%
for(String aName:groupNames) {
%>
			<option><%=aName %></option>
<%
} 
%>
		</select>
		Or <input type="text" name="Gname" />
		</td>
	</tr>
	
	<tr>
		<td>URL </td>
		<td><input type="text" name="URLname" /> <br>
		</td>
	</tr>
	
	<tr>
		<td>Text  </td>
		<td><input type="text" name="Textname" /> <br>
		</td>
	</tr>
	
	<tr>
		<td>IsLink </td>
		<td><select name="IsLinkname">
			<option>true</option>
			<option>false</option>
		</select></td>
	</tr>
	<%
	if(isSplUser) {
	%>
		<tr>
			<td>Private To Me </td>
			<td><select name="isSpl">
				<option>true</option>
				<option selected="selected">false</option>
			</select></td>
		</tr>
	<%
	}
	%>
	<!-- tr>
		<td>Visible To Admin Only</td>
		<td><select name="visibleToAdminOnly">
			<option>false</option>
			<option>true</option>
		</select></td>
	</tr-->
	<tr>
		<td align="center">
			<input type="submit" value="Add URL" id="B1" />
		</td>
		<td align="center">
			<input type="button" class="blueBut" value="Go Home" onclick="javascript:location.replace('<%=request.getContextPath() %>/showResult.nos')" />
		</td>
	</tr>

</table>
</div>
</center>
</form>
<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>