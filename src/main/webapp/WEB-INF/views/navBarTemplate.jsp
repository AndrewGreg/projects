<%@page import="edu.ben.template.model.User"%>

<%
	User currentUser = (User) request.getAttribute("currentUser");
	String active = request.getAttribute("active") != null ? (String) request.getAttribute("active") : "";
%>

<!-- ******HEADER****** -->
<header class="header">
	<div class="top-bar">
		<div class="container">
			<ul id="socialIconList"
				class="social-icons col-md-6 col-sm-6 col-xs-12 ">
				<li class="row-end"><a href="https://www.ben.edu"
					target="_blank"><i class="fa fa-university"></i></a></li>
				<li><a href="https://www.instagram.com/benu1887/"
					target="_blank"><i class="fa fa-instagram"></i></a></li>
				<li><a
					href="https://www.linkedin.com/company/benedictine-university"
					target="_blank"><i class="fa fa-linkedin"></i></a></li>
				<li><a href="https://www.youtube.com/user/benu1887"
					target="_blank"><i class="fa fa-youtube"></i></a></li>
				<li><a href="https://www.facebook.com/BenedictineUniversity/"
					target="_blank"><i class="fa fa-facebook"></i></a></li>
				<li><a href="https://twitter.com/BenU1887" target="_blank"><i
						class="fa fa-twitter"></i></a></li>
			</ul>
			<!--//social-icons-->
			<ul class="pull-right">
				<%
					if (currentUser == null) {
				%>

				<!-- Trigger the modal with a button -->
				<button type="button" class="btn btn-theme top-bar-btn"
					data-toggle="modal" data-target="#loginModal">
					<i class="fa fa-lock"></i> Login
				</button>
				<a href="/Alumni-Tracker/register" class="btn btn-theme top-bar-btn"><i
					class="fa fa-pencil"></i> Register</a>

				<jsp:include page="loginModal.jsp" />

				<%
					} else {
				%>
				<div class="btn-group">
					<button type="button"
						class="btn btn-theme top-bar-btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Welcome back
						<%=currentUser.getFirstName()%>! &nbsp;<i class="fa fa-angle-down"></i>
					</button>
					<ul class="dropdown-menu profile-dropdown">
						<li><a class="profile-dropdown-link"
							href="/Alumni-Tracker/user/<%=currentUser.getId()%>">My
								Profile</a></li>
						<li><a class="profile-dropdown-link"
							href="/Alumni-Tracker/myEvents">My Events</a></li>
						<li><a class="profile-dropdown-link"
							href="/Alumni-Tracker/rsvpEventList">RSVP Events</a></li>
						<li><a class="profile-dropdown-link"
							href="/Alumni-Tracker/logout">Logout</a></li>
					</ul>
				</div>
				<%
					}
				%>

			</ul>
		</div>

	</div>
	<!--//to-bar-->
	<div class="header-main container">
		<h1 class="logo col-md-4 col-sm-4">
			<a href="/Alumni-Tracker/"><img id="logo"
				src="/Alumni-Tracker/content/img/benu-logo.svg"
				alt="Benedictine University" style="width: 300px"></a>
		</h1>
		<!--//logo-->
		<div class="info col-md-8 col-sm-8"></div>
		<!--//info-->
	</div>
	<!--//header-main-->
</header>
<!--//header-->

<!-- ******NAV****** -->
<nav class="main-nav" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target="#navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!--//nav-toggle-->
		</div>

		<!--//navbar-header-->
		<div class="navbar-collapse collapse" id="navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="<%if (active.equals("index")) {%>active<%}%> nav-item"><a
					href="/Alumni-Tracker/"><i class="fa fa-home"></i> Home</a></li>
				<li class="<%if (active.equals("faculty")) {%>active<%}%> nav-item"><a
					href="/Alumni-Tracker/faculty"><i class="fa fa-user"></i>
						Faculty</a></li>

				<%
					if (currentUser != null) {
				%>
				<li class="<%if (active.equals("alumni")) {%>active<%}%> nav-item"><a
					href="/Alumni-Tracker/alumni"><i class="fa fa-user"></i> Alumni</a></li>
				<%
					}
				%>

				<%
					if (currentUser != null) {
						if (currentUser.getRole() == 4) {
				%>
				<li class="<%if (active.equals("users")) {%>active<%}%> nav-item"><a
					href="/Alumni-Tracker/allUsers"><i class="fa fa-user"></i>
						Manage Users</a></li>
				<%
					}
					}
				%>

				<li
					class="<%if (active.equals("event")) {%>active<%}%> nav-item dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown"
					data-hover="dropdown" data-delay="0" data-close-others="false"
					href="#"><i class="fa fa-calendar"></i> Events <i
						class="fa fa-angle-down"></i></a>
					<ul class="dropdown-menu">

						<%
							if (currentUser != null) {
						%>
						<li><a href="/Alumni-Tracker/eventsTemplate">View Events</a></li>

						<%
							if (currentUser != null && currentUser.getRole() > 2) {
						%>

						<li><a href="/Alumni-Tracker/createNewEvent">Create Event</a></li>

						<%
							}
						%>

						<%
							} else {
						%>
						<li><a href="/Alumni-Tracker/eventsTemplate">View Public
								Events</a></li>
						<%
							}
						%>
					</ul></li>
				<li
					class="<%if (active.equals("job")) {%>active<%}%> nav-item dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown"
					data-hover="dropdown" data-delay="0" data-close-others="false"
					href="#"><i class="fa fa-suitcase"></i> Jobs<i
						class="fa fa-angle-down"></i></a>
					<ul class="dropdown-menu">

						<%
							if (currentUser != null) {
						%>
						<li><a href="/Alumni-Tracker/jobs">View Job Postings</a></li>

						<%
							if (currentUser != null && currentUser.getRole() > 1) {
						%>
						<li><a href="/Alumni-Tracker/createJobPosting">Create a
								Job Posting</a></li>
						<%
							}
						%>
						<%
							} else {
						%>
						<li><a href="/Alumni-Tracker/jobs">View Public Job
								Postings</a></li>
						<%
							}
						%>
					</ul></li>
				<li
					class="<%if (active.equals("testimonial")) {%>active<%}%> nav-item"><a
					href="/Alumni-Tracker/testimonialList"><i
						class="fa fa-comments"></i> Testimonials</a></li>
			</ul>
			<!--//nav-->
		</div>
		<!--//navabr-collapse-->
	</div>
	<!--//container-->
</nav>
<!--//main-nav-->