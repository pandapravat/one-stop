<%@page import="com.nilesen.aw.onestop.bean.URLEntry"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Nielsen-One-Stop</title>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
<div class="container">
	<%

	boolean isAdmin = false;
	Boolean authorised = (Boolean)request.getSession().getAttribute("AUTH");
	if(null != authorised) {
		isAdmin = authorised;
	}
	Map<String, List<URLEntry>> urlinfos = (Map<String, List<URLEntry>>) request.getAttribute("GROUPS");
	Set<String> set = urlinfos.keySet();
	int i=0;

	for (String groupName : set) {
		i++;
	%>
	

		<div class="group" >
			<div class="grouptitle"><span class="indicator"> + </span> <%=groupName%></div>
			<div class="collapsible collapsed">
			<% List<URLEntry> allEntries = urlinfos.get(groupName);
				for (URLEntry anEntry : allEntries) {
					if (anEntry.isIslink()) { %>
						<ul id="<%=anEntry.getId()%>">
							<li class="70p"><a target="_blank" href="<%=anEntry.getUrl()%>"><%=anEntry.getText()%></a></li>
							<li><input type="button" class="blueBut" value="Show" onclick='createPopUp("<%=anEntry.getUrl()%>", "<%=anEntry.getId()%>")'/></li>
							<%
								if(isAdmin) {
							%>
							<li><input type="button" class="blueBut" value="Edit" name="Update" onclick="updateRecord(<%=anEntry.getId()%>, '<%=request.getContextPath()%>')"/></li>
							<li><input type="button" class="blueBut" value="Remove" name="Update" onclick="removeRecord(<%=anEntry.getId()%>, '<%=request.getContextPath()%>')"/></li>
							<%
								}
							%>
						</ul>
					<%
						} 
								else {
					%>
						<ul id="<%=anEntry.getId()%>">
							<li class="70p"><%=anEntry.getText()%></li>
							<li><input type="button" class="blueBut" value="Show" onclick='createPopUp("<%=anEntry.getUrl()%>", "<%=anEntry.getId()%>")'/></li>
							<% if(isAdmin) { %>
							<li><input type="button" class="blueBut" value="Edit" onclick="updateRecord(<%=anEntry.getId()%>, '<%=request.getContextPath()%>')"/></li>
							<li><input type="button" class="blueBut" value="Remove" name="Update" onclick="removeRecord(<%=anEntry.getId()%>, '<%=request.getContextPath()%>')"/></li>
							<%}
							%>
						</ul>
<%
					}
				}
%>
			</div>
		</div>
<%
	}
%>
<!-- div id="deevelopersList" ><span onclick="createPopUp('Pravat Kumar Panda\nNalinikanta Sarangi', 'deevelopersList')">Developers</span></div-->
</div>
<script type="text/javascript">
function removeRecord(id, contextPath) {
	var r = confirm("Are you sure?");
	if(r) {
		location.replace(contextPath + "/removeRecord.nos?Id=" +id);	
	}
}
function updateRecord(id, contextPath) {
	location.replace(contextPath + "/showUpdate.nos?Id=" +id);
}
function createPopUp(htmlMessage, popParent)
{
     var div = document.createElement('div');
     div.innerHTML = '<div class="popUp"><div><textarea  readonly="readonly">' + htmlMessage + '</textarea></div><div class="center"><img alt="Close" src="images/close.jpg" height=15 width=15 onclick="removePopUp(this.parentNode.parentNode.parentNode, this.parentNode.parentNode);" /></div></div>';
     if(document.getElementById(popParent).className != "appended") {
     	document.getElementById(popParent).appendChild(div.firstChild);
     	document.getElementById(popParent).className = "appended";
     } 
}

function removePopUp(parentNode, popUpNode) {
	parentNode.removeChild(popUpNode);
	parentNode.className = "";
}

$(document).ready(function() {
	  //hide the all of the element with class collapsible
	  $(".collapsible").hide();
	  //toggle the componenet with class collapsible
	  $(".grouptitle").click(function() {
	    	$(this).next(".collapsible").slideToggle(600, function() {
			    if($(this).hasClass("collapsed")) {
			    	$(this).removeClass("collapsed");
			    	$(this).addClass("expanded");
			    	$(this).prev(".grouptitle").children(".indicator").text(" - ");
			    } else {
			    	$(this).removeClass("expanded");
			    	$(this).addClass("collapsed");
			    	$(this).prev(".grouptitle").children(".indicator").text(" + ");
			    }
	    	});
	  });
});

</script>
<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>
