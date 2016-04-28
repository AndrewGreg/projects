<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="edu.ben.template.model.Event"%>
<%@page import="edu.ben.template.model.User"%>


<%
	User currentUser = (User) request.getAttribute("currentUser");
	//boolean flag = (boolean) request.getAttribute("addRsvp");
%>
<%
	Event currentEvent = (Event) request.getAttribute("currentEvent");
	String id = Long.toString(currentEvent.getId());

	String name = currentEvent != null && currentEvent.getName() != null ? currentEvent.getName() : "N/A";
	String poster = currentEvent != null && currentEvent.getPoster() != null
			&& currentEvent.getPoster().getFirstName() != null && currentEvent.getPoster().getLastName() != null
					? (currentEvent.getPoster().getFirstName() + " " + currentEvent.getPoster().getLastName())
					: "N/A";
	String posterEmail = currentEvent != null && currentEvent.getPoster() != null
			&& currentEvent.getPoster().getEmail() != null ? currentEvent.getPoster().getEmail() : "N/A";

	Long posterId = currentEvent != null && currentEvent.getPoster() != null ? currentEvent.getPoster().getId()
			: -1L;

	String location = currentEvent != null && currentEvent.getLocation() != null ? currentEvent.getLocation()
			: "N/A";
	String googleLink = currentEvent != null && currentEvent.getGoogleMapsLink() != null
			? currentEvent.getGoogleMapsLink() : "#";
	String date = currentEvent != null && currentEvent.getDate().toString() != null
			? currentEvent.getDate().toString() : "N/A";
	String description = currentEvent != null && currentEvent.getDescription() != null
			? currentEvent.getDescription() : "N/A";
	String timeRange = currentEvent != null && currentEvent.getTimeRange() != null ? currentEvent.getTimeRange()
			: "N/A";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=name%></title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>

	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper">
				<header class="page-heading clearfix ben-container">
					<h1 class="heading-title pull-left"><%=name%></h1>
					
					<div class="breadcrumbs pull-right">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/Alumni-Tracker/">Home</a><i
								class="fa fa-angle-right"></i></li>
							<li><a href="/Alumni-Tracker/eventsTemplate">Events</a><i
								class="fa fa-angle-right"></i></li>
							<li class="current"><%=name%></li>
						</ul>
					</div>
					<!--//breadcrumbs-->
				</header>
				<div class="page-content">
					<div class="row page-row">
						<div class="jobs-wrapper col-md-8 col-sm-7">
							<br>
							<div class="box box-border page-row">
								<ul class="list-unstyled">
									<li><strong>Posted By:</strong> <%=poster%></li>
									<li><strong>Location:</strong> <a href="<%=googleLink%>"
										target="_blank"><i class="fa fa-map-marker"></i><%=location%></a></li>
									<li><strong>Date:</strong> <%=date%></li>
									<li><strong>Time:</strong> <%=timeRange%></li>
									<li><strong>Contact:</strong> <%=posterEmail%></li>
								</ul>
							</div>
							<p><%=description%></p>

						</div>
						<!-- /Job Wrapper -->

						<aside
							class="page-sidebar  col-md-3 col-md-offset-1 col-sm-4 col-sm-offset-1">


							<section class="widget has-divider">
								<h3 class="section-heading text-highlight">RSVP!</h3>
								<h4><a class="btn btn-theme" href ="/Alumni-Tracker/rsvpList">RSVP List</a></h4>
								

								<%
									if (currentUser != null) {
									
								%>
								<a class="btn btn-theme" href="/Alumni-Tracker/addRsvp"><i
									class="fa fa-send-o"></i>Attend</a>
										
								
									<a class="btn btn-theme" href="/Alumni-Tracker/deleteRsvp"><i
									class="fa fa-ban"></i>Not Going </a>
									<%} %> 

							</section>

							<section class="widget has-divider">
								<h3 class="section-heading text-highlight">Create your own
									event!</h3>

								<%
									if (currentUser != null) {
								%>


								<a class="btn btn-theme" href="/Alumni-Tracker/createNewEvent"><i
									class="fa fa-plus"></i>Create Event</a>


								<%
									} else {
								%>

								<p>You are viewing this page as a guest. If you want to
									create a new event, please log in.</p>
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

								<a class="btn btn-theme"
									href="/Alumni-Tracker/editAnEvent/<%=id%>""><i
									class="fa fa-edit"></i>Edit Event</a>
							</section>

							<%
								}
							%>


							<%
								if (currentUser != null && (currentUser.getRole() == 4 || currentUser.getId() == posterId)) {
							%>

							<section class="widget has-divider">
								<h3 class="section-heading text-highlight">Old or incorrect
									event?</h3>

								<form action="/Alumni-Tracker/deleteEvent" method="POST"
									name="deleteEvent">
									<button type="submit" class="btn btn-theme">
										<i class="fa fa-trash"></i>Delete Event
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