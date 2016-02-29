<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="edu.ben.template.model.Event"%>
<%@page import="edu.ben.template.model.User"%>

<%
	User currentUser = (User) request.getAttribute("currentUser");
%>
<%
	Event currentEvent = (Event) request.getAttribute("currentEvent");
%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=currentEvent.getName()%></title>

<jsp:include page="header.jsp" />
</head>
<body>

	<jsp:include page="navBar.jsp"/>
	<div class = "container">
	<div align="center" style="padding-center: 100px;">
		<div class="well well-lg">
			<div class="row" align="left" style="padding-left: 50px;">
				<h1><%=currentEvent.getName()%></h1>
				<hr>
				<h3>
					Date:
					<%=currentEvent.getDate()%></h3>
			</div>
			<div class="row" align="left" style="padding-left: 50px;">
				<h4>
					Posted by:
					<%=currentEvent.getPoster()%>
				</h4>
				<h4>Contact: <%=currentUser.getEmail()%></h4>
				<br>
				<hr>
				<h4>Description: </h4>
			</div>
			<div class="row" align="left" style="padding-left: 125px;">
			<h5> <%=currentEvent.getDescription()%></h5>
			</div>
		</div>
		<br>
	</div>
	</div>
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>