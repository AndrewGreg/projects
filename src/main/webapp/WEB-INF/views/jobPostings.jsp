<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.ben.template.model.JobPosting"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JobPosting> jobPostings;
	if (request.getAttribute("jobPostings") != null) {
		jobPostings = (ArrayList<JobPosting>) request.getAttribute("jobPostings");
	} else {
		jobPostings = new ArrayList<JobPosting>();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job Postings</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="navBar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col col-xs-12">
				<div class="page-header">
					<h2 style="padding-left: 100px;">Job Postings</h2>
				</div>
			</div>
		</div>
	</div>

	<div class="container">

		<div class="well well-lg">
			<div class="row">
				<h4>Position</h4>
				<h5>At: Company</h5>
			</div>
			<div class="row">
				<h5>Posted by: Someone</h5>
				<h5>someone@ben.edu</h5>
			</div>

			<p>Description Lorem ipsum dolor sit amet, consectetur adipiscing
				elit, sed do eiusmod tempor incididunt ut labore et dolore magna
				aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
				laboris nisi ut aliquip ex ea commodo consequat.</p>
		</div>
		<br>

		<%
			for (int i = 0; i < jobPostings.size(); i++) {
		%>

		<div class="well well-lg">
			<div class="row">
				<h4><%=jobPostings.get(i).getName()%></h4>
				<h5>
					At:
					<%=jobPostings.get(i).getCompany()%></h5>
			</div>
			<div class="row">
				<h5>
					Posted by:
					<%=jobPostings.get(i).getPoster().getFirstName()%>
					<%=jobPostings.get(i).getPoster().getLastName()%>
				</h5>
				<h5><%=jobPostings.get(i).getPoster().getEmail()%></h5>
			</div>

			<p><%=jobPostings.get(i).getDescription()%></p>
		</div>
		<br>

		<%
			}
		%>

	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>