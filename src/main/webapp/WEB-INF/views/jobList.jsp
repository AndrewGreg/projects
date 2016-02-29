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
<title>Jobs</title>
<jsp:include page="header.jsp" />
<link rel="stylesheet" type="text/css" href="/content/css/lists.css">
</head>
<body>

	<jsp:include page="navBar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col col-xs-12">
				<div class="page-header">
					<h2 style="padding-left: 100px;">Jobs List</h2>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div id="itemList">
			<input class="search" placeholder="Search" />
			<ul class="list">
				<%
					for (int i = 0; i < jobPostings.size(); i++) {

						String name = jobPostings.get(i).getName() != null ? jobPostings.get(i).getName() : "";
						String company = jobPostings.get(i).getCompany() != null ? jobPostings.get(i).getCompany() : "";
						String id = Long.toString(jobPostings.get(i).getId());
				%>
				<li>
					<h4 class="primary">
						<a href="/jobDisplay/<%=id%>"><%=name%></a>
					</h4>
					<p class="secondary"><%=company%></p>
				</li>

				<%
					}
				%>
			</ul>
		</div>
		<script src="/content/jQuery/list.js"></script>
		<script src="/content/jQuery/listSearch.js"></script>

	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>