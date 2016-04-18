<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.ben.template.model.Event"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%
	User user = (User) request.getAttribute("currentUser");
	ArrayList<Event> events;
	if (request.getAttribute("events") != null) {
		events = (ArrayList<Event>) request.getAttribute("events");
	} else {
		events = new ArrayList<Event>();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>


	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper ben-container">
				<header class="page-heading clearfix pageHeading">
					<h1 class="heading-title pull-left">Events</h1>
					<div class="breadcrumbs pull-right">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/Alumni-Tracker/">Home</a><i
								class="fa fa-angle-right"></i></li>
							<li class="current">Events</li>
						</ul>
					</div>
					<!--//breadcrumbs-->
				</header>
				<div class="page-content">
					<div class="row page-row">
						<div class="events-wrapper col-md-7 col-sm-6">
							<br>

							<%
								for (int i = 0; i < events.size(); i++) {

									String name = events.get(i).getName() != null ? events.get(i).getName() : "No event name";
									String location = events.get(i).getLocation() != null ? events.get(i).getLocation() : "No Location";
									String description = events.get(i).getDescription() != null ? events.get(i).getDescription()
											: "No event description";
									String month = events.get(i).getEventMonth() != null ? events.get(i).getEventMonth() : "NA";
									String day = events.get(i).getEventDay() != null ? events.get(i).getEventDay() : "NA";
									String timeRange = events.get(i).getTimeRange() != null ? events.get(i).getTimeRange()
											: "No time range";
									String id = Long.toString(events.get(i).getId());
									String googleLink = events.get(i) != null && events.get(i).getGoogleMapsLink() != null
											? events.get(i).getGoogleMapsLink() : "NA";
							%>

							<article class="events-item page-row has-divider clearfix">
								<div class="date-label-wrapper col-md-1 col-sm-2">
									<p class="date-label">
										<span class="month"><%=month%></span> <span
											class="date-number"><%=day%></span>
									</p>
								</div>
								<!--//date-label-wrapper-->
								<div class="details col-md-11 col-sm-10">
									<h3 class="title">
										<a href="/Alumni-Tracker/newEvents/<%=id%>"><%=name%></a>
									</h3>
									<p class="meta">
										<span class="time"><i class="fa fa-clock-o"></i><%=timeRange%></span><span
											class="location"><i class="fa fa-map-marker"></i><a
											href="<%=googleLink%>" target="_blank"><%=location%></a></span>
									</p>

									<p class="desc">
										<%
											if (description.length() > 90) {
										%>
										<%=(description.substring(0, 100) + "...")%>
										<%
											} else {
										%>
										<%=description%>
										<%
											}
										%>

									</p>


								</div>
								<!--//details-->
							</article>
							<!--//events-item-->

							<%
								}
							%>
						</div>

						<!--//events-wrapper-->
						<aside
							class="page-sidebar  col-md-4 col-md-offset-1 col-sm-5 col-sm-offset-1">
							<section class="widget has-divider video">

								<div class="row">
									<h3 class="section-heading text-highlight col-md-10">
										<span class="line">Past Events</span>
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
													src="https://www.youtube.com/embed/HG6eNOdXMpU"
													frameborder="0" allowfullscreen=""></iframe>
											</div>
											<!--//item-->
											<div class="item">
												<iframe class="video-iframe"
													src="https://www.youtube.com/embed/6MMMLFjSw8U"
													frameborder="0" allowfullscreen=""></iframe>
											</div>
											<!--//item-->
											<div class="item">
												<iframe class="video-iframe"
													src="https://www.youtube.com/embed/1EkhSu0fM7U"
													frameborder="0" allowfullscreen=""></iframe>
											</div>
											<!--//item-->
										</div>
										<!--//carousel-inner-->
									</div>
									<!--//videos-carousel-->
									<p class="description">Past events from Benedictine
										University organized by student groups or faculty.</p>
								</div>
							</section>
							<section class="widget has-divider">
								<h3 class="section-heading text-highlight">Create your own
									event!</h3>

								<%
									if (user != null) {
								%>


								<a class="btn btn-theme" href="/Alumni-Tracker/createNewEvent"><i
									class="fa fa-calendar"></i>Create Event</a>


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
						</aside>
					</div>
				</div>

			</div>

		</div>
	</div>


	<jsp:include page="footerTemplate.jsp" />
</body>
</html>