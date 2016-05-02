<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="edu.ben.template.model.Testimonial"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<Testimonial> testimonials;
	if (request.getAttribute("testimonials") != null) {
		testimonials = (ArrayList<Testimonial>) request.getAttribute("testimonials");
	} else {
		testimonials = new ArrayList<Testimonial>();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testimonials</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper">
				<header class="page-heading clearfix ben-container">
					<h1 class="heading-title pull-left">Testimonials</h1>
					<div class="breadcrumbs pull-right">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/Alumni-Tracker/">Home</a><i
								class="fa fa-angle-right"></i></li>
							<li class="current">Testimonials</li>
						</ul>
					</div>
					<!--//breadcrumbs-->
				</header>

				<div class="page-content">
					<div class="row page-row">
						<div class="team-wrapper col-md-7 col-sm-6">
							<br>

							<%
								for (int i = 0; i < testimonials.size(); i++) {
									if(testimonials.get(i).getPoster().isActive()){

									String testimonial = testimonials.get(i) != null && testimonials.get(i).getTestimonial() != null
											? testimonials.get(i).getTestimonial() : "N/A";
									String name = testimonials.get(i) != null && testimonials.get(i).getPoster() != null
											&& testimonials.get(i).getPoster().getFirstName() != null
											&& testimonials.get(i).getPoster().getLastName() != null
													? testimonials.get(i).getPoster().getFirstName() + " "
															+ testimonials.get(i).getPoster().getLastName()
													: "N/A";
									String major = testimonials.get(i) != null && testimonials.get(i).getPoster() != null
											&& testimonials.get(i).getPoster().getMajorAtIndex(0) != null
											&& testimonials.get(i).getPoster().getMajorAtIndex(0).getName() != null
													? testimonials.get(i).getPoster().getMajorAtIndex(0).getName() : "N/A";
							%>

							<div class="row page-row">
								<figure class="thumb col-md-3 col-sm-4 col-xs-6">
									<img class="img-responsive"
										src="/Alumni-Tracker/content/img/empty-profile.png"
										alt="Profile picture" />
								</figure>
								<div class="details col-md-9 col-sm-8 col-xs-6">
									<h3 class="title"><%=name%></h3>
									<h6><%=major%></h6>
									<p><%=testimonial%></p>
								</div>
							</div>

							<%
									}
								}

								if (testimonials.size() == 0) {
							%>

							<h4>We could not find any testimonials to display.</h4>

							<%
								}
							%>


						</div>

						<aside
							class="page-sidebar  col-md-4 col-md-offset-1 col-sm-5 col-sm-offset-1">
							<section class="widget has-divider video">

								<div class="row">
									<h3 class="section-heading text-highlight col-md-10">
										<span class="line">What do they think of us?</span>
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
													src="https://www.youtube.com/embed/xYlP7DRUiQk"
													frameborder="0" allowfullscreen=""></iframe>
											</div>
											<!--//item-->
											<div class="item">
												<iframe class="video-iframe"
													src="https://www.youtube.com/embed/_s4DrGKB_Uc"
													frameborder="0" allowfullscreen=""></iframe>
											</div>
											<!--//item-->
											<div class="item">
												<iframe class="video-iframe"
													src="https://www.youtube.com/embed/tvSnlQaH0YY"
													frameborder="0" allowfullscreen=""></iframe>
											</div>
											<!--//item-->
										</div>
										<!--//carousel-inner-->
									</div>
									<!--//videos-carousel-->
									<p class="description">Video testimonials.</p>
								</div>
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