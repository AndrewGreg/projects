<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import= "edu.ben.template.model.User"%>

<%User user = (User) request.getAttribute("currentUser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
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
					<li><img class="schoolImg" src="content/img/schoolCrop1.jpg"
						alt="" />
						<p class="flex-caption">
							<span class="main">Stay in touch.</span> <br /> <span
								class="secondary clearfix">Connect with fellow alumni,
								students, and faculty.</span>
						</p></li>
					<li><img class="schoolImg" src="content/img/schoolCrop2.jpg"
						alt="" />
						<p class="flex-caption">
							<span class="main">Socialize</span> <br /> <span
								class="secondary clearfix">Keep track of events related
								to Benedictine and its alumni.</span>
						</p></li>
					<li><img class="schoolImg" src="content/img/schoolCrop3.jpg"
						alt="" />
						<p class="flex-caption">
							<span class="main">What are they up to?</span> <br /> <span
								class="secondary clearfix">Look at alumni or faculty
								profiles to see where they're working at or what they're doing.</span>
						</p></li>
					<li><img class="schoolImg" src="content/img/schoolCrop4.jpg"
						alt="" />
						<p class="flex-caption">
							<span class="main">Find a path</span> <br /> <span
								class="secondary clearfix">Search for job opportunities
								posted by fellow alumni or faculty. </span>
						</p></li>
				</ul>
				<!--//slides-->
			</div>
			<!--//flexslider-->
			<section class="promo box box-dark">
			
			<% if(user == null) { %>
			<div class="col-md-9">
			
			<%} else { %>
			<div class="col-md-12">
			<% } %>
			
				<h1 class="section-heading">Alumni Tracker</h1>
				<p>Join a community of fellow alumni, students and faculty who
					are a part of Benedictine University. This web site will let you
					keep in touch with current students, old classmates, and faculty.
					You can organize or keep track of events related to the University.
					You'll also be able to post job openings which other alumni might
					be able to apply for, or check out opportunities posted by other users.</p>
			</div>
			
			<%  if(user == null) { %>
			<div class="col-md-3">
				<a class="btn btn-cta" href="#"><i class="fa fa-pencil"></i>Apply
					Now</a>
			</div> <% } %>
			
			</section>
			<!--//promo-->
			<section class="news">
			<h1 class="section-heading text-highlight">
				<span class="line">Latest News</span>
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
							<div class="col-md-4 news-item">
								<h2 class="title">
									<a href="news-single.html">Phasellus scelerisque metus</a>
								</h2>
								<img class="thumb"
									src="content/templateAssets/assets/images/news/news-thumb-1.jpg"
									alt="" />
								<p>Suspendisse purus felis, porttitor quis sollicitudin sit
									amet, elementum et tortor. Praesent lacinia magna in malesuada
									vestibulum. Pellentesque urna libero.</p>
								<a class="read-more" href="news-single.html">Read more<i
									class="fa fa-chevron-right"></i></a>
							</div>
							<!--//news-item-->
							<div class="col-md-4 news-item">
								<h2 class="title">
									<a href="news-single.html">Morbi at vestibulum turpis</a>
								</h2>
								<p>Nam feugiat erat vel neque mollis, non vulputate erat
									aliquet. Maecenas ac leo porttitor, semper risus condimentum,
									cursus elit. Vivamus vitae libero tellus.</p>
								<a class="read-more" href="news-single.html">Read more<i
									class="fa fa-chevron-right"></i></a> <img class="thumb"
									src="content/templateAssets/assets/images/news/news-thumb-2.jpg"
									alt="" />
							</div>
							<!--//news-item-->
							<div class="col-md-4 news-item">
								<h2 class="title">
									<a href="news-single.html">Aliquam id iaculis urna</a>
								</h2>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
									Etiam bibendum mauris eget sapien consectetur pellentesque.
									Proin elementum tristique euismod.</p>
								<a class="read-more" href="news-single.html">Read more<i
									class="fa fa-chevron-right"></i></a> <img class="thumb"
									src="content/templateAssets/assets/images/news/news-thumb-3.jpg"
									alt="" />
							</div>
							<!--//news-item-->
						</div>
						<!--//item-->
						<div class="item">
							<div class="col-md-4 news-item">
								<h2 class="title">
									<a href="news-single.html">Phasellus scelerisque metus</a>
								</h2>
								<img class="thumb"
									src="content/templateAssets/assets/images/news/news-thumb-4.jpg"
									alt="" />
								<p>Suspendisse purus felis, porttitor quis sollicitudin sit
									amet, elementum et tortor. Praesent lacinia magna in malesuada
									vestibulum. Pellentesque urna libero.</p>
								<a class="read-more" href="news-single.html">Read more<i
									class="fa fa-chevron-right"></i></a>
							</div>
							<!--//news-item-->
							<div class="col-md-4 news-item">
								<h2 class="title">
									<a href="news-single.html">Morbi at vestibulum turpis</a>
								</h2>
								<p>Nam feugiat erat vel neque mollis, non vulputate erat
									aliquet. Maecenas ac leo porttitor, semper risus condimentum,
									cursus elit. Vivamus vitae libero tellus.</p>
								<a class="read-more" href="news-single.html">Read more<i
									class="fa fa-chevron-right"></i></a> <img class="thumb"
									src="content/templateAssets/assets/images/news/news-thumb-5.jpg"
									alt="" />
							</div>
							<!--//news-item-->
							<div class="col-md-4 news-item">
								<h2 class="title">
									<a href="news-single.html">Aliquam id iaculis urna</a>
								</h2>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
									Etiam bibendum mauris eget sapien consectetur pellentesque.
									Proin elementum tristique euismod.</p>
								<a class="read-more" href="news-single.html">Read more<i
									class="fa fa-chevron-right"></i></a> <img class="thumb"
									src="content/templateAssets/assets/images/news/news-thumb-6.jpg"
									alt="" />
							</div>
							<!--//news-item-->
						</div>
						<!--//item-->
					</div>
					<!--//carousel-inner-->
				</div>
				<!--//news-carousel-->
			</div>
			<!--//section-content--> </section>
			<!--//news-->
			<div class="row cols-wrapper">
				<div class="col-md-3">
					<section class="events">
					<h1 class="section-heading text-highlight">
						<span class="line">Events</span>
					</h1>
					<div class="section-content">
						<div class="event-item">
							<p class="date-label">
								<span class="month">FEB</span> <span class="date-number">18</span>
							</p>
							<div class="details">
								<h2 class="title">Open Day</h2>
								<p class="time">
									<i class="fa fa-clock-o"></i>10:00am - 18:00pm
								</p>
								<p class="location">
									<i class="fa fa-map-marker"></i>East Campus
								</p>
							</div>
							<!--//details-->
						</div>
						<!--event-item-->
						<div class="event-item">
							<p class="date-label">
								<span class="month">SEP</span> <span class="date-number">06</span>
							</p>
							<div class="details">
								<h2 class="title">E-learning at College Green</h2>
								<p class="time">
									<i class="fa fa-clock-o"></i>10:00am - 16:00pm
								</p>
								<p class="location">
									<i class="fa fa-map-marker"></i>Learning Center
								</p>
							</div>
							<!--//details-->
						</div>
						<!--event-item-->
						<div class="event-item">
							<p class="date-label">
								<span class="month">JUN</span> <span class="date-number">23</span>
							</p>
							<div class="details">
								<h2 class="title">Career Fair</h2>
								<p class="time">
									<i class="fa fa-clock-o"></i>09:45am - 16:00pm
								</p>
								<p class="location">
									<i class="fa fa-map-marker"></i>Library
								</p>
							</div>
							<!--//details-->
						</div>
						<!--event-item-->
						<div class="event-item">
							<p class="date-label">
								<span class="month">May</span> <span class="date-number">17</span>
							</p>
							<div class="details">
								<h2 class="title">Science Seminar</h2>
								<p class="time">
									<i class="fa fa-clock-o"></i>14:00pm - 18:00pm
								</p>
								<p class="location">
									<i class="fa fa-map-marker"></i>Library
								</p>
							</div>
							<!--//details-->
						</div>
						<!--event-item-->
						<a class="read-more" href="events.html">All events<i
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
								
								<div class="col-md-12 col-sm-12 col-md-offset-3 form-group keywords">
									<input class="form-control pull-left" type="text"
										placeholder="Search keywords" />
									<button type="submit" class="btn btn-theme">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>
						<!--//course-finder-form-->
						<a class="read-more" href="courses.html">View all job postings<i
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
						<p class="description">Aenean feugiat a diam tempus sodales.
							Quisque lorem nulla, ultrices imperdiet malesuada at, suscipit
							vel lorem. Nulla dignissim nisi ac aliquet semper.</p>
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
							<a href="#"><i class="fa fa-caret-right"></i>Faculty Directory</a>
						</p>
						<p>
							<a href="#"><i class="fa fa-caret-right"></i>Events</a>
						</p>
						<p>
							<a href="#"><i class="fa fa-caret-right"></i>Job Postings</a>
						</p>
						<p>
							<a href="#"><i class="fa fa-caret-right"></i>Contact</a>
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
											src="content/templateAssets/assets/images/testimonials/profile-1.png"
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
											src="content/templateAssets/assets/images/testimonials/profile-2.png"
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
											src="content/templateAssets/assets/images/testimonials/profile-3.png"
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
	<jsp:include page="footerTemplate.jsp" />
</body>
</html>
