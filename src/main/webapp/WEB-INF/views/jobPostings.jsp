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
 
		<%
			for (int i = 0; i < jobPostings.size(); i++) {

				String name = jobPostings.get(i).getName() != null ? jobPostings.get(i).getName() : "";
				String company = jobPostings.get(i).getCompany() != null ? jobPostings.get(i).getCompany() : "";
				String description = jobPostings.get(i).getDescription() != null ? jobPostings.get(i).getDescription()
						: "";
				String poster = (jobPostings.get(i).getPoster() != null
						&& jobPostings.get(i).getPoster().getFirstName() != null
						&& jobPostings.get(i).getPoster().getLastName() != null)
								? jobPostings.get(i).getPoster().getFirstName() + " "
										+ jobPostings.get(i).getPoster().getLastName()
								: "";
				String contact = jobPostings.get(i).getPoster() != null
						&& jobPostings.get(i).getPoster().getEmail() != null ? jobPostings.get(i).getPoster().getEmail()
								: "";
		%>

		<div class="well well-lg">
			<div class="row">
				<h4><%=name%></h4>
				<h5>
					At:
					<%=company%></h5>
			</div>
			<div class="row">
				<h5>
					Posted by:
					<%=poster%>
				</h5>
				<h5><%=contact%></h5>
			</div>

			<p><%=description%></p>
		</div>
		<br>

		<%
			}
		%>

	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>