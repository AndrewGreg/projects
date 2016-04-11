<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="edu.ben.template.model.User"%>
<%@page import="edu.ben.template.model.Event"%>
<%@page import="edu.ben.template.model.Job"%>
<%@ page import="java.util.ArrayList"%>
<%
	User user = (User) request.getAttribute("currentUser");

	ArrayList<Event> events;
	if (request.getAttribute("events") != null) {
		events = (ArrayList<Event>) request.getAttribute("events");
	} else {
		events = new ArrayList<Event>();
	}

	ArrayList<Job> jobs;
	if (request.getAttribute("jobs") != null) {
		jobs = (ArrayList<Job>) request.getAttribute("jobs");
	} else {
		jobs = new ArrayList<Job>();
	}
%>
<!DOCTYPE html>

<html lang="en">

<head>
<title>Home</title>
<jsp:include page="headerTemplate.jsp" />
</head>

<body class="home-page">
	<div class="wrapper">

		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container">
			<div id="promo-slider" class="slider flexslider">
				<ul class="slides">
					<li><img class="schoolImg"
						src="/Alumni-Tracker/content/img/schoolCrop1.jpg" alt="" />
						<p class="flex-caption">
							<span class="main">Stay in touch.</span> <br /> <span
								class="secondary clearfix">Connect with fellow alumni,
								students, and faculty.</span>
						</p></li>
					<li><img class="schoolImg"
						src="/Alumni-Tracker/content/img/schoolCrop2.jpg" alt="" />
						<p class="flex-caption">
							<span class="main">Socialize</span> <br /> <span
								class="secondary clearfix">Keep track of events related
								to Benedictine and its alumni.</span>
						</p></li>
					<li><img class="schoolImg"
						src="/Alumni-Tracker/content/img/schoolCrop3.jpg" alt="" />
						<p class="flex-caption">
							<span class="main">What are they up to?</span> <br /> <span
								class="secondary clearfix">Look at alumni or faculty
								profiles to see where they're working at or what they're doing.</span>
						</p></li>
					<li><img class="schoolImg"
						src="/Alumni-Tracker/content/img/schoolCrop4.jpg" alt="" />
						<p class="flex-caption">
							<span class="main">Find a path</span> <br /> <span
								class="secondary clearfix">Search for job opportunities
								posted by fellow alumni or faculty. </span>
						</p></li>
				</ul>
				<!--//slides-->
			</div>
			<!--//flexslider-->
			<section class="promo box box-dark"> <%
 	if (user == null) {
 %>
			<div class="col-md-9">

				<%
					} else {
				%>
				<div class="col-md-12">
					<%
						}
					%>

					<h1 class="section-heading">Alumni Tracker</h1>
					<p>Join a community of fellow alumni, students and faculty who
						are a part of Benedictine University. This web site will let you
						keep in touch with current students, old classmates, and faculty.
						You can organize or keep track of events related to the
						University. You'll also be able to post job openings which other
						alumni might be able to apply for, or check out opportunities
						posted by other users.</p>
				</div>

				<%
					if (user == null) {
				%>
				<div class="col-md-3">
					<a class="btn btn-cta" href="/Alumni-Tracker/register"><i class="fa fa-pencil"></i>Register
						Now</a>
				</div>
				<%
					}
				%>
			
			</section>
			<!--//promo-->
			<section class="news">
			<h1 class="section-heading text-highlight">
				<span class="line">Job Postings</span>
			</h1>
			<div class="carousel-controls">
				<a class="prev" href="#news-carousel" data-slide="prev"><i
					class="fa fa-caret-left"></i></a> <a class="next" href="#news-carousel"
					data-slide="next"><i class="fa fa-caret-right"></i></a>
			</div>
			<!--//carousel-controls-->
			<div class="section-content clearfix">
				<div id="news-carousel" class="news-carousel carousel slide">
					<div class="carousel-inner">
						<div class="item active">

							<%
								for (int i = 0; i < jobs.size(); i++) {

									Long jobId = jobs.get(i) != null && jobs.get(i).getId() != 0 ? jobs.get(i).getId() : -1L;
									String jobName = jobs.get(i) != null && jobs.get(i).getName() != null ? jobs.get(i).getName() : "NA";
									String shortDescription = jobs.get(i) != null && jobs.get(i).getShortDescription() != null
											? jobs.get(i).getShortDescription() : "NA";

									if (i == 4) {
							%>

						</div>
						<div class="item">

							<%
								}
							%>

							<div class="col-md-4 ">
								<h2 class="title" style="text-align: center;">
									<a href="/jobs/<%=jobId%>"><%=jobName%></a>
								</h2>

								<p><%=shortDescription%></p>
								<a class="read-more" href="/Alumni-Tracker/jobs/<%=jobId%>">Read more<i
									class="fa fa-chevron-right"></i></a>
							</div>
							<!--//news-item-->

							<%
								}

								if (jobs.size() == 0) {
							%>
							<div class="row">
								<span class="col-md-8 col-md-offset-5">No current job
									postings.</span>
							</div>
							<br>
							<%
								}
							%>

						</div>
						<!--//carousel-inner-->
					</div>
					<!--//news-carousel-->
				</div>
				<!--//section-content-->
			</section>
			<!--//news-->
			<div class="row cols-wrapper">
				<div class="col-md-3">
					<section class="events">
					<h1 class="section-heading text-highlight">
						<span class="line">Events</span>
					</h1>
					<div class="section-content">

						<%
							for (int i = 0; i < events.size() && i < 4; i++) {
								Long eventId = events.get(i) != null && events.get(i).getId() != 0 ? events.get(i).getId() : -1L;
								String eventName = events.get(i) != null && events.get(i).getName() != null ? events.get(i).getName()
										: "NA";
								String month = events.get(i) != null && events.get(i).getEventMonth() != null
										? events.get(i).getEventMonth() : "NA";
								String day = events.get(i) != null && events.get(i).getEventDay() != null ? events.get(i).getEventDay()
										: "NA";
								String location = events.get(i) != null && events.get(i).getLocation() != null
										? events.get(i).getLocation() : "NA";
								String timeRange = events.get(i) != null && events.get(i).getTimeRange() != null
										? events.get(i).getTimeRange() : "NA";
								String googleLink = events.get(i) != null && events.get(i).getGoogleMapsLink() != null
										? events.get(i).getGoogleMapsLink() : "NA";
						%>
						<div class="event-item">
							<p class="date-label">
								<span class="month"><%=month%></span> <span class="date-number"><%=day%></span>
							</p>
							<div class="details">
								<h2 class="title">
									<a href="#"><%=eventName%></a>
								</h2>
								<p class="time">
									<i class="fa fa-clock-o"></i><%=timeRange%>
								</p>
								<p class="location">
									<i class="fa fa-map-marker"></i><a href="<%=googleLink%>"
										target="_blank"><%=location%></a>
								</p>
							</div>
							<!--//details-->
						</div>
						<!--event-item-->

						<%
							}

							if (events.size() == 0) {
						%>

						<span>No current events.</span>

						<%
							}
						%>

						<a class="read-more" href="/Alumni-Tracker/eventsTemplate">All events<i
							class="fa fa-chevron-right"></i></a>
					</div>
					<!--//section-content--> </section>
					<!--//events-->
				</div>
				<!--//col-md-3-->
				<div class="col-md-6">
					<section class="course-finder">
					<h1 class="section-heading text-highlight">
						<span class="line">Job Finder</span>
					</h1>
					<div class="section-content">
						<form class="course-finder-form" action="#" method="POST">
							<div class="row">
								<div class="form-group col-md-8 col-md-offset-2">
									<input type="text" id="jobSearch" name="jobSearch"
										placeholder="Search keywords..." class="form-control">
								</div>

							</div>
							<button type="submit" class="btn btn-theme pull-right">
								<i class="fa fa-search"></i> Search
							</button>
						</form>
						<!--//course-finder-form-->
						<a class="read-more" href="/Alumni-Tracker/jobs">View all job postings<i
							class="fa fa-chevron-right"></i></a>
					</div>
					<!--//section-content--> </section>
					<!--//course-finder-->
					<section class="video">
					<h1 class="section-heading text-highlight">
						<span class="line">Benedictine Alumni</span>
					</h1>
					<div class="carousel-controls">
						<a class="prev" href="#videos-carousel" data-slide="prev"><i
							class="fa fa-caret-left"></i></a> <a class="next"
							href="#videos-carousel" data-slide="next"><i
							class="fa fa-caret-right"></i></a>
					</div>
					<!--//carousel-controls-->
					<div class="section-content">
						<div id="videos-carousel" class="videos-carousel carousel slide">
							<div class="carousel-inner">
								<div class="item active">
									<iframe class="video-iframe"
										src="https://www.youtube.com/embed/7FZWPPZ_qxs"
										frameborder="0" allowfullscreen=""></iframe>
								</div>
								<!--//item-->
								<div class="item">
									<iframe class="video-iframe"
										src="https://www.youtube.com/embed/3ZNkGeLyIcA"
										frameborder="0" allowfullscreen=""></iframe>
								</div>
								<!--//item-->
								<div class="item">
									<iframe class="video-iframe"
										src="https://www.youtube.com/embed/7CMYxPam3nY"
										frameborder="0" allowfullscreen=""></iframe>
								</div>
								<!--//item-->
							</div>
							<!--//carousel-inner-->
						</div>
						<!--//videos-carousel-->
						<p class="description">Explore the daily lives of Benedictine
							University's students. From their classes, to extracurricular
							activities and events.</p>
					</div>
					<!--//section-content--> </section>
					<!--//video-->
				</div>
				<div class="col-md-3">
					<section class="links">
					<h1 class="section-heading text-highlight">
						<span class="line">Quick Links</span>
					</h1>
					<div class="section-content">
						<p>
							<a href="/Alumni-Tracker/faculty"><i class="fa fa-caret-right"></i>Faculty
								Directory</a>
						</p>
						<p>
							<a href="/Alumni-Tracker/alumni"><i class="fa fa-caret-right"></i>Alumni
								Directory</a>
						</p>
						<p>
							<a href="/Alumni-Tracker/eventsTemplate"><i class="fa fa-caret-right"></i>Events</a>
						</p>
						<p>
							<a href="/Alumni-Tracker/jobs"><i class="fa fa-caret-right"></i>Job Postings</a>
						</p>
					</div>
					<!--//section-content--> </section>
					<!--//links-->
					<section class="testimonials">
					<h1 class="section-heading text-highlight">
						<span class="line"> Testimonials</span>
					</h1>
					<div class="carousel-controls">
						<a class="prev" href="#testimonials-carousel" data-slide="prev"><i
							class="fa fa-caret-left"></i></a> <a class="next"
							href="#testimonials-carousel" data-slide="next"><i
							class="fa fa-caret-right"></i></a>
					</div>
					<!--//carousel-controls-->
					<div class="section-content">
						<div id="testimonials-carousel"
							class="testimonials-carousel carousel slide">
							<div class="carousel-inner">
								<div class="item active">
									<blockquote class="quote">
										<p>
											<i class="fa fa-quote-left"></i>I’m very happy interdum eget
											ipsum. Nunc pulvinar ut nulla eget sollicitudin. In hac
											habitasse platea dictumst. Integer mattis varius ipsum,
											posuere posuere est porta vel. Integer metus ligula, blandit
											ut fermentum a, rhoncus in ligula. Duis luctus.
										</p>
									</blockquote>
									<div class="row">
										<p class="people col-md-8 col-sm-3 col-xs-8">
											<span class="name">Marissa Spencer</span><br /> <span
												class="title">Curabitur commodo</span>
										</p>
										<img class="profile col-md-4 pull-right"
											src="/Alumni-Tracker/content/templateAssets/assets/images/testimonials/profile-1.png"
											alt="" />
									</div>
								</div>
								<!--//item-->
								<div class="item">
									<blockquote class="quote">
										<p>
											<i class="fa fa-quote-left"></i> I'm very pleased commodo
											gravida ultrices. Sed massa leo, aliquet non velit eu,
											volutpat vulputate odio. Interdum et malesuada fames ac ante
											ipsum primis in faucibus. Suspendisse porttitor metus eros,
											ut fringilla nulla auctor a.
										</p>
									</blockquote>
									<div class="row">
										<p class="people col-md-8 col-sm-3 col-xs-8">
											<span class="name">Marco Antonio</span><br /> <span
												class="title"> Gravida ultrices</span>
										</p>
										<img class="profile col-md-4 pull-right"
											src="/Alumni-Tracker/content/templateAssets/assets/images/testimonials/profile-2.png"
											alt="" />
									</div>
								</div>
								<!--//item-->
								<div class="item">
									<blockquote class="quote">
										<p>
											<i class="fa fa-quote-left"></i> I'm delighted commodo
											gravida ultrices. Sed massa leo, aliquet non velit eu,
											volutpat vulputate odio. Interdum et malesuada fames ac ante
											ipsum primis in faucibus. Suspendisse porttitor metus eros,
											ut fringilla nulla auctor a.
										</p>
									</blockquote>
									<div class="row">
										<p class="people col-md-8 col-sm-3 col-xs-8">
											<span class="name">Kate White</span><br /> <span
												class="title"> Gravida ultrices</span>
										</p>
										<img class="profile col-md-4 pull-right"
											src="/Alumni-Tracker/content/templateAssets/assets/images/testimonials/profile-3.png"
											alt="" />
									</div>
								</div>
								<!--//item-->

							</div>
							<!--//carousel-inner-->
						</div>
						<!--//testimonials-carousel-->
					</div>
					<!--//section-content--> </section>
					<!--//testimonials-->
				</div>
				<!--//col-md-3-->
			</div>
			<!--//cols-wrapper-->

		</div>
		<!--//content-->
	</div>
	<!--//wrapper-->
	<!-- <jsp:include page="footerTemplate.jsp" /> -->
</body>
</html>
