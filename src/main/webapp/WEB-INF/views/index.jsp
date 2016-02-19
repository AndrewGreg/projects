<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<!-- Navigation -->
	<jsp:include page="navBar.jsp" />
	<br>
	<br>

	<!-- Header Carousel -->
	<header id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="fill"
					style="background-image: url('content/img/School1.jpg');"></div>
				<div class="carousel-caption">
					<h2>Organized to provide you with the latest updates about
						alumni programs.</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url('content/img/School2.jpg');"></div>
				<div class="carousel-caption">
					<h2>Check out the upcoming events.</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url('content/img/School3.jpg');"></div>
				<div class="carousel-caption">
					<h2>Alumni networking opportunities are here!</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url('content/img/School4.jpg');"></div>
				<div class="carousel-caption">
					<h2></h2>
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url('content/img/School5.jpg');"></div>
				<div class="carousel-caption">
					<h2></h2>
				</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="icon-prev"></span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span class="icon-next"></span>
		</a>
	</header>


	<!-- Page Content -->
	<div class="container">

		<!-- Marketing Icons Section -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Welcome to the Alumni Tradition</h1>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-check"></i> Events
						</h4>
					</div>
					<div class="panel-body">
						<p>This Link Doesn't Work Yet</p>
						<a href="events.jsp" class="btn btn-default">Find More!</a>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-gift"></i> Job Postings
						</h4>
					</div>
					<div class="panel-body">
<<<<<<< HEAD
						<p>Look for Opportunities here</p>
						<a href="/jobPostings" class="btn btn-default">Find
=======
						<p>This Link Doesn't Work Yet</p>
						<a href="jobPostings.jsp" class="btn btn-default">Find
>>>>>>> cbaaa38b3144dae3eaff9426623fe06f463b1aeb
							Positions!</a>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-compass"></i> Join the Team
						</h4>
					</div>
					<div class="panel-body">
<<<<<<< HEAD
						<p>Be Apart Of A Stronger Group!</p>
						<a href="/register" class="btn btn-default">Sign Up!</a>
=======
						<p>This Link Doesn't Work Yet</p>
						<a href="register.jsp" class="btn btn-default">Sign Up!</a>
>>>>>>> cbaaa38b3144dae3eaff9426623fe06f463b1aeb
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->

		<!-- <!-- Next Sprint????
        Portfolio Section
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Recent Events</h2>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
                </a>
            </div>
        </div>
        /.row -->

	</div>
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	<!--/#footer-->
	
	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>
</body>
</html>