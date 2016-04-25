<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="edu.ben.template.model.Job"%>
<%@page import="edu.ben.template.model.User"%>

<%
	User currentUser = (User) request.getAttribute("currentUser");
%>
<%
	Job currentJob = (Job) request.getAttribute("currentJob");
	String id = Long.toString(currentJob.getId());
	String googleLink = currentJob != null && currentJob.getGoogleMapsLink() != null
			? currentJob.getGoogleMapsLink() : "#";

	String posterName = currentJob != null && currentJob.getPoster() != null
			&& currentJob.getPoster().getFirstName() != null && currentJob.getPoster().getLastName() != null
					? currentJob.getPoster().getFirstName() + " " + currentJob.getPoster().getLastName()
					: "N/A";
	Long posterId = currentJob != null && currentJob.getPoster() != null ? currentJob.getPoster().getId() : -1L;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=currentJob.getName()%></title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>

	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper">
				<header class="page-heading clearfix ben-container">
					<h1 class="heading-title pull-left"><%=currentJob.getName()%></h1>
					<div class="breadcrumbs pull-right">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/Alumni-Tracker/">Home</a><i
								class="fa fa-angle-right"></i></li>
							<li><a href="/Alumni-Tracker/jobs">Jobs</a><i
								class="fa fa-angle-right"></i></li>
							<li class="current"><%=currentJob.getName()%></li>
						</ul>
					</div>
					<!--//breadcrumbs-->
				</header>
				<div class="page-content">
					<div class="row page-row">
						<div class="jobs-wrapper col-md-8 col-sm-7">
							<br>
							<%
								String hoursDisplay = "";
								if (currentJob.getHours() == 1) {
									hoursDisplay = "Full-time";
								} else {
									hoursDisplay = "Part-time";
								}
							%>
							<div class="box box-border page-row">
								<ul class="list-unstyled">
									<%
										if (currentJob != null && currentJob.getPoster() != null && currentJob.getPoster().getFirstName() != null
												&& currentJob.getPoster().getLastName() != null && currentJob.getPoster().getEmail() != null) {
									%>
									<li><strong>Posted By:</strong><%=posterName%></li>
									<li><strong>Location: </strong><a href="<%=googleLink%>"
										target="_blank"><i class="fa fa-map-marker"></i><%=currentJob.getLocation()%></a></li>

									<li><strong>Salary:</strong> <i
											class="fa fa-usd"></i><%=currentJob.getStart_salary()%>
										- <%=currentJob.getEnd_salary()%></li>

									<li><strong>Hours:</strong> <%=hoursDisplay%></li>
									<%-- <li style="color: white"><strong>Closing date:</strong> <%=//currentJob.getEndDate()%></li> --%>
									<li><strong>Contact:</strong> <%=currentJob.getPoster().getEmail()%></li>
								</ul>
							</div>
							<p>
								<%=currentJob.getDescription()%></p>
							<%
								}
							%>
						</div>
						<!-- /jobs wraper -->
						<aside
							class="page-sidebar  col-md-3 col-md-offset-1 col-sm-4 col-sm-offset-1">

							<section class="widget has-divider">
								<h3 class="section-heading text-highlight">Hiring?</h3>

								<%
									if (currentUser != null) {
								%>


								<a class="btn btn-theme" href="/Alumni-Tracker/createJobPosting"><i
									class="fa fa-plus"></i>Create Posting</a>


								<%
									} else {
								%>

								<p>You are viewing this page as a guest. If you want to
									create a new job posting, please log in.</p>
								<%
									}
								%>
							</section>
							<!--//widget-->

							<%
								if (currentUser != null && (currentUser.getRole() == 4 || currentUser.getId() == posterId)) {
							%>

							<section class="widget has-divider">
								<h3 class="section-heading text-highlight">Something wrong?</h3>

								<a class="btn btn-theme" href="/Alumni-Tracker/editAJob/<%=id%>""><i
									class="fa fa-edit"></i>Edit Job Posting</a>


							</section>

							<%
								}
							%>


							<%
								if (currentUser != null && (currentUser.getRole() == 4 || currentUser.getId() == posterId)) {
							%>

							<section class="widget has-divider">
								<h3 class="section-heading text-highlight">Old or incorrect
									post?</h3>

								<form action="/Alumni-Tracker/deleteJob" method="POST"
									name="deleteJob">
									<button type="submit" class="btn btn-theme">
										<i class="fa fa-trash"></i>Delete Job Post
									</button>
								</form>


							</section>

							<%
								}
							%>

						</aside>

					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footerTemplate.jsp" />

</body>
</html>