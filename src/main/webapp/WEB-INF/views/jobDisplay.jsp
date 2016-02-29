<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@page import="edu.ben.template.model.JobPosting"%>
<%@page import="edu.ben.template.model.User"%>

<%
	User currentUser = (User) request.getAttribute("currentUser");
%>
<%
	JobPosting currentJob = (JobPosting) request.getAttribute("currentJob");
%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=currentJob.getName()%></title>

<jsp:include page="header.jsp" />
</head>
<body>

	<jsp:include page="navBar.jsp"/>
	<div class = "container">
	<div align="center" style="padding-center: 100px;">
		<div class="well well-lg">
			<div class="row" align="left" style="padding-left: 50px;">
				<h1><%=currentJob.getName()%></h1>
				<hr>
				<h3>
					Location:
					<%=currentJob.getCompany()%></h3>
			</div>
			<div class="row" align="left" style="padding-left: 50px;">
				<h4>
					Posted by:
					<%=currentJob.getPoster().getFirstName()%> <%=currentJob.getPoster().getLastName()%>
				</h4>
				<h4>Contact: <%=currentJob.getPoster().getEmail()%></h4>
				<br>
				<hr>
				<h4>Description: </h4>
			</div>
			<div class="row" align="left" style="padding-left: 125px;">
			<h5> <%=currentJob.getDescription()%></h5>
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