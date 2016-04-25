<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="edu.ben.template.model.Job"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%
	User currentUser = (User)request.getAttribute("currentUser");
	ArrayList<Job> jobs;
	if (request.getAttribute("jobs") != null) {
		jobs = (ArrayList<Job>) request.getAttribute("jobs");
	} else {
		jobs = new ArrayList<Job>();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jobs</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>

	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper">
				<header class="page-heading clearfix ben-container">
					<h1 class="heading-title pull-left">Job Postings</h1>
					<div class="breadcrumbs pull-right">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/Alumni-Tracker/">Home</a><i
								class="fa fa-angle-right"></i></li>
							<li class="current">Jobs</li>
						</ul>
					</div>
					<!--//breadcrumbs-->
				</header>
				<div class="page-content">
					<div class="row page-row">
						<div class="jobs-wrapper col-md-7 col-sm-6">
							<br>
							<%
								for (int i = 0; i < jobs.size(); i++) {

									String name = jobs.get(i) != null && jobs.get(i).getName() != null ? jobs.get(i).getName() : "";
									String company = jobs.get(i) != null && jobs.get(i).getCompany() != null ? jobs.get(i).getCompany()
											: "";
									String id = jobs.get(i) != null ? Long.toString(jobs.get(i).getId()) : "-1";
									String description = jobs.get(i) != null && jobs.get(i).getShortDescription() != null
											? jobs.get(i).getShortDescription() : "N/A";
									String location = jobs.get(i) != null && jobs.get(i).getLocation() != null ? jobs.get(i).getLocation()
											: "";
									int hours = jobs.get(i) != null && jobs.get(i).getHours() != 0 ? jobs.get(i).getHours() : 0;
									int startSalary = jobs.get(i) != null && jobs.get(i).getStart_salary() != 0
											? jobs.get(i).getStart_salary() : 0;
									int endSalary = jobs.get(i) != null && jobs.get(i).getEnd_salary() != 0 ? jobs.get(i).getEnd_salary()
											: 0;
									float startWage = jobs.get(i) != null && jobs.get(i).getStart_wage() != 0 ? jobs.get(i).getStart_wage()
											: 0;
									float endWage = jobs.get(i) != null && jobs.get(i).getEnd_wage() != 0 ? jobs.get(i).getEnd_wage() : 0;
									String hoursDisplay = "";
									String payDisplay = "";
									String googleLink = jobs.get(i) != null && jobs.get(i).getGoogleMapsLink() != null
											? jobs.get(i).getGoogleMapsLink() : "#";
									String poster = jobs.get(i) != null && jobs.get(i).getPoster() != null
											&& jobs.get(i).getPoster().getFirstName() != null
											&& jobs.get(i).getPoster().getLastName() != null
													? (jobs.get(i).getPoster().getFirstName() + " " + jobs.get(i).getPoster().getLastName())
													: "N/A";

									if (hours == 2) {
										hoursDisplay = "Full-time";
										//payDisplay = "Salary";
									} else {
										hoursDisplay = "Part-time";
										//payDisplay = "Wage";
									}
							%>
							<div class="panel panel-default page-row">
								<div class="panel-heading">
									<h3 class="panel-title">
										<a href="/Alumni-Tracker/jobs/<%=id%>"><%=name%></a> <span
											class="label label-success pull-right"><%=hoursDisplay%></span>
									</h3>

								</div>
								<div class="panel-body">


									<%=description%>

								</div>
								<ul class="list-group">
									<li class="list-group-item"><strong>Location: </strong><a
										href="<%=googleLink%>" target="_blank"> <i
											class="fa fa-map-marker"></i><%=location%></a></li>

									<li class="list-group-item"><strong>Salary:</strong> <i
											class="fa fa-usd"></i><%=startSalary%>
										- <%=endSalary%></li>

								</ul>
								<div class="panel-footer">
									<div class="row">
										<ul class="list-inline col-md-8 col-sm-6 col-xs-6">
											<li><a href="/Alumni-Tracker/jobs/<%=id%>"><i
													class="fa fa-info"></i>More details</a></li>
										</ul>
										<div class="meta col-md-4 col-sm-6 col-xs-6 text-right"
											style="color: white">
											<small>Posted by: <%=poster%></small>
										</div>
									</div>
								</div>
							</div>
							<!--//panel-->
							<%
								}
							%>
						</div>
						<aside
							class="page-sidebar  col-md-4 col-md-offset-1 col-sm-5 col-sm-offset-1">
							<section class="widget has-divider video">

								<div class="row">
									<h3 class="section-heading text-highlight col-md-10">
										<span class="line">What's next?</span>
										<div class="carousel-controls pull-right">
											<a class="prev" href="#videos-carousel" data-slide="prev"><i
												class="fa fa-caret-left"></i></a> <a class="next"
												href="#videos-carousel" data-slide="next"><i
												class="fa fa-caret-right"></i></a>
										</div>
									</h3>

								</div>
								<div class="section-content">
									<div id="videos-carousel"
										class="videos-carousel carousel slide">
										<div class="carousel-inner">
											<div class="item active">
												<iframe class="video-iframe"
													src="https://www.youtube.com/embed/3ZNkGeLyIcA"
													frameborder="0" allowfullscreen=""></iframe>
											</div>
											<!--//item-->
											<div class="item">
												<iframe class="video-iframe"
													src="https://www.youtube.com/embed/H-KFiJBX2Pk"
													frameborder="0" allowfullscreen=""></iframe>
											</div>
											<!--//item-->
											<div class="item">
												<iframe class="video-iframe"
													src="https://www.youtube.com/embed/SzUzY_0wEjI"
													frameborder="0" allowfullscreen=""></iframe>
											</div>
											<!--//item-->
										</div>
										<!--//carousel-inner-->
									</div>
									<!--//videos-carousel-->
									<p class="description">Careers after Benedictine.</p>
								</div>
							</section>
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
						</aside>
					</div>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="footerTemplate.jsp" />
</body>
</html>