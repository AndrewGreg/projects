<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="edu.ben.template.model.Testimonial"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="edu.ben.template.model.UploadFile"%>

<%
	ArrayList<Testimonial> testimonials;
	if (request.getAttribute("testimonials") != null) {
		testimonials = (ArrayList<Testimonial>) request.getAttribute("testimonials");
	} else {
		testimonials = new ArrayList<Testimonial>();
	}
	String testimonialAttempt = request.getAttribute("testimonialAttempt") != null
			? (String) request.getAttribute("testimonialAttempt") : "";
	//String testimonial = request.getAttribute("testimonial") != null
		//	? (String) request.getAttribute("testimonial") : "";
	String errors = request.getAttribute("errors") != null ? (String) request.getAttribute("errors") : "";
	User currentUser = (User) request.getAttribute("currentUser");
	ArrayList<UploadFile> photos;
		photos = (ArrayList<UploadFile>) request.getAttribute("photos");

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
									if (testimonials.get(i).getPoster().isActive()) {

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
										
						
										int k = 0;
							%>
							
							

							<div class="row page-row">
								<figure class="thumb col-md-3 col-sm-4 col-xs-6">
									<%
										if(photos.size() == 0){
									%>
											<img class="img-responsive"
											src="/Alumni-Tracker/content/img/empty-profile.png"
											alt="Profile picture" />	
									<%
										}else{
									%>

									<%
											while(k < photos.size()){

												if(testimonials.get(i).getPoster().getId() == photos.get(k).getProfile().getId()){
									%>	
													<img id="profile-pic" class="img-responsive"
														src="/Alumni-Tracker/getImage/<%=testimonials.get(i).getPoster().getId()%>.do"
														alt="Profile Picture" width="128" height="128">
									<%
												k++;		

												}else if(k == photos.size() - 1){
													k++;
									%>	
										<img class="img-responsive"
											src="/Alumni-Tracker/content/img/empty-profile.png"
											alt="Profile picture" />	
									<%
										}else{
											k++;
										}
											
										}
										}
									%>
								
									<%
										if (currentUser != null ) {
											if(currentUser.getRole() == 4){
									%>
									<form
										action="/Alumni-Tracker/deleteTestimonial/<%=testimonials.get(i).getId()%>"
										method="POST" name="deleteTestimonial">
										<button type="submit" class="btn btn-theme" style="margin-top: 10px">
											<i class="fa fa-trash"></i>Delete Testimonial
										</button>
									</form>

										<button type="button" class="btn btn-theme"
											data-toggle="modal" data-target="#testimonialModal" style="margin-top: 10px">
											<i class="fa fa-comments"></i> Edit Testimonial
										</button>
									<!-- Modal -->
									<div class="modal fade" id="testimonialModal" role="dialog">
										<div class="modal-dialog">

											<!-- Modal content-->
											<div class="modal-content">
												<div class="modal-header" style="padding: 35px 50px;">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modalh4">
														<span class="glyphicon glyphicon-pencil"></span> Tell us
														what you think!
													</h4>
												</div>
												<div class="modal-body" style="padding: 40px 50px;">
													<%
														if (testimonialAttempt.equals("failure")) {
													%>
													<small id="testimonialErrorMsg"><%=errors%></small>

													<%
														}
													%>
													<form role="form"
														action="/Alumni-Tracker/editTestimonial/<%=testimonials.get(i).getId()%>"
														method="POST" name="editTestimonial">
														<div class="form-group" data-name="testimonial">
															<textarea class="form-control" rows="8" id="testimonial"
																name="testimonial" placeholder="I think..."
																class="form-control" autofocus><%=testimonial%></textarea>
														</div>
														<button type="submit" class="btn btn-theme btn-block">
															<span class="glyphicon glyphicon-send"></span> Submit
														</button>
													</form>
												</div>
											</div>

										</div>
									</div>
									<%	
										}
										}
									%>
								</figure>
								<div class="details col-md-9 col-sm-8 col-xs-6">	
									<h3 class="title"><%=name%></h3>
									<h6><%=major%></h6>
									<p><%=testimonial%></p>
								</div>
							</div>

							<%
								}
								if (testimonials.size() == 0) {
							%>

							<h4>We could not find any testimonials to display.</h4>

							<%
								}
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