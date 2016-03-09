<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="edu.ben.template.model.Job"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<Job> jobs;
	if (request.getAttribute("jobs") != null) {
		jobs = (ArrayList<Job>) request.getAttribute("jobs");
	} else {
		jobs = new ArrayList<Job>();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jobs</title>

 <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">    
    <link rel="shortcut icon" href="favicon.ico">  
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>     
    <!-- Global CSS -->
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">   
    <!-- Plugins CSS -->    
    <link rel="stylesheet" href="assets/plugins/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="assets/plugins/flexslider/flexslider.css">
    <link rel="stylesheet" href="assets/plugins/pretty-photo/css/prettyPhoto.css">    
    <!-- Theme CSS -->  
    <link id="theme-style" rel="stylesheet" href="assets/css/styles.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
</head>
<body>

 <div class="wrapper">
        <!-- ******HEADER****** --> 
        <header class="header">  
            <div class="top-bar">
                <div class="container">              
                    <ul class="social-icons col-md-6 col-sm-6 col-xs-12 hidden-xs">
                        <li><a href="#" ><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#" ><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#" ><i class="fa fa-youtube"></i></a></li>
                        <li><a href="#" ><i class="fa fa-linkedin"></i></a></li>
                        <li><a href="#" ><i class="fa fa-google-plus"></i></a></li>         
                        <li class="row-end"><a href="#" ><i class="fa fa-rss"></i></a></li>             
                    </ul><!--//social-icons-->
                    <form class="pull-right search-form" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search the site...">
                        </div>
                        <button type="submit" class="btn btn-theme">Go</button>
                    </form>         
                </div>      
            </div><!--//to-bar-->
            <div class="header-main container">
                <h1 class="logo col-md-4 col-sm-4">
                    <a href="index.html"><img id="logo" src="assets/images/logo.png" alt="Logo"></a>
                </h1><!--//logo-->           
                <div class="info col-md-8 col-sm-8">
                    <ul class="menu-top navbar-right hidden-xs">
                        <li class="divider"><a href="index.html">Home</a></li>
                        <li class="divider"><a href="faq.html">FAQ</a></li>
                        <li><a href="contact.html">Contact</a></li>
                    </ul><!--//menu-top-->
                    <br />
                    <div class="contact pull-right">
                        <p class="phone"><i class="fa fa-phone"></i>Call us today 0800 123 4567</p> 
                        <p class="email"><i class="fa fa-envelope"></i><a href="#">enquires@website.com</a></p>
                    </div><!--//contact-->
                </div><!--//info-->
            </div><!--//header-main-->
        </header><!--//header-->
        
        <!-- ******NAV****** -->
        <nav class="main-nav" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button><!--//nav-toggle-->
                </div><!--//navbar-header-->            
                <div class="navbar-collapse collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="nav-item"><a href="index.html">Home</a></li>
                        <li class="nav-item dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false" href="#">Courses <i class="fa fa-angle-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="courses.html">Course List</a></li>
                                <li><a href="course-single.html">Single Course (with image)</a></li>
                                <li><a href="course-single-2.html">Single Course (with video)</a></li>   
                                <li class="dropdown-submenu">
                                    <a tabindex="-1" href="#">Menu Levels <i class="fa fa-angle-right"></i></a>
                                    <ul class="dropdown-menu">
                                        <li><a tabindex="-1" href="#">Submenu Level 2</a></li>
                                        <li class="dropdown-submenu">
                                            <a href="#">Submenu Level 2 <i class="fa fa-angle-right"></i></a>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Submenu Level 3</a></li>
                                            	<li><a href="#">Submenu Level 3</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="#">Submenu Level 2</a></li>
                                        <li><a href="#">Submenu Level 2</a></li>
                                    </ul>
                                </li>            
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false" href="#">News <i class="fa fa-angle-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="news.html">News List</a></li>
                                <li><a href="news-single.html">Single News (with image)</a></li>   
                                <li><a href="news-single-2.html">Single News (with video)</a></li>          
                            </ul>
                        </li>
                        <li class="nav-item"><a href="events.html">Events</a></li>
                        <li class="active nav-item dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false" href="#">Pages <i class="fa fa-angle-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="about.html">About</a></li>
                                <li><a href="team.html">Leadership Team</a></li>
                                <li><a href="jobs.html">Jobs</a></li>
                                <li><a href="job-single.html">Single Job</a></li>
                                <li><a href="gallery.html">Gallery (3 columns)</a></li>
                                <li><a href="gallery-2.html">Gallery (4 columns)</a></li>
                                <li><a href="gallery-3.html">Gallery (2 columns)</a></li>
                                <li><a href="gallery-4.html">Gallery (with sidebar)</a></li>
                                <li><a href="gallery-album.html">Single Gallery</a></li>
                                <li><a href="gallery-album-2.html">Single Gallery (with sidebar)</a></li>
                                <li><a href="faq.html">FAQ</a></li>                                
                                <li><a href="privacy.html">Privacy Policy</a></li> 
                                <li><a href="terms-and-conditions.html">Terms & Conditions</a></li>                   
                            </ul>
                        </li><!--//dropdown-->
                        <li class="nav-item dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false" href="#">Shortcodes <i class="fa fa-angle-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="typography.html"><i class="fa fa-file-text"></i> Typography</a></li>
                                <li><a href="tables.html"><i class="fa fa-table"></i> Tables</a></li>
                                <li><a href="buttons.html"><i class="fa fa-star"></i> Buttons</a></li>
                                <li><a href="components.html"><i class="fa fa-rocket"></i> Components</a></li> 
                                <li><a href="icons.html"><i class="fa fa-heart"></i> Icons</a></li>                                                 
                            </ul>
                        </li><!--//dropdown-->
                        <li class="nav-item"><a href="contact.html">Contact</a></li>
                    </ul><!--//nav-->
                </div><!--//navabr-collapse-->
            </div><!--//container-->
        </nav><!--//main-nav-->
        
          <!-- ******CONTENT****** --> 
        <div class="content container">
            <div class="page-wrapper">
                <header class="page-heading clearfix">
                    <h1 class="heading-title pull-left">Jobs</h1>
                    <div class="breadcrumbs pull-right">
                        <ul class="breadcrumbs-list">
                            <li class="breadcrumbs-label">You are here:</li>
                            <li><a href="index.html">Home</a><i class="fa fa-angle-right"></i></li>
                            <li class="current">Jobs</li>
                        </ul>
                    </div><!--//breadcrumbs-->
                </header> 
                <div class="page-content">
                    <div class="row page-row">
                        <div class="jobs-wrapper col-md-8 col-sm-7">           
                            <%
								for (int i = 0; i < jobs.size(); i++) {

									String name = jobs.get(i).getName() != null ? jobs.get(i).getName() : "";
									String company = jobs.get(i).getCompany() != null ? jobs.get(i).getCompany() : "";
									String id = Long.toString(jobs.get(i).getId());
									String description = jobs.get(i).getDescription() != null ? jobs.get(i).getDescription() : "";
									String location = jobs.get(i).getLocation() != null ? jobs.get(i).getLocation() : "";
									int hours = jobs.get(i).getHours() != 0 ? jobs.get(i).getHours() : 0;
									int startSalary = jobs.get(i).getStart_salary() != 0 ? jobs.get(i).getStart_salary() : 0;
									int endSalary = jobs.get(i).getEnd_salary() != 0 ? jobs.get(i).getEnd_salary() : 0;
									
							%>
                            <div class="panel panel-default page-row">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><a href="/jobs/<%=id%>"><%=name%></a> <span class="label label-success pull-right"><%=hours%></span></h3>
                                    
                                </div>
                                <div class="panel-body">
                                    <%=description%>
                                </div>
                                <ul class="list-group">
                                    <li class="list-group-item"><strong>Location:</strong> <%=location%></li>
                                    <li class="list-group-item"><strong>Salary:</strong> <%=startSalary%> - <%=endSalary%></li>
                                </ul>
                                <div class="panel-footer">
                                    <div class="row">
                                        <ul class="list-inline col-md-8 col-sm-6 col-xs-6">
                                            <li><a href="/jobs/<%=id%>">More details</a></li>
                                        </ul>
                                        <div class="meta col-md-4 col-sm-6 col-xs-6 text-right">
                                            <small>Posted 3 days ago</small>
                                        </div>
                                    </div>
                                </div>
                            </div><!--//panel-->
                            <%
								}
                            %>
                        </div>
                    </div>
                </div>
            </div>
         </div>
        
       </div>
       
       <!-- ******FOOTER****** -->
	<footer class="footer">
	<div class="footer-content">
		<div class="container">
			<div class="row">
				<div class="footer-col col-md-3 col-sm-4 about">
					<div class="footer-col-inner">
						<h3>About</h3>
						<ul>
							<li><a href="about.html"><i class="fa fa-caret-right"></i>About
									us</a></li>
							<li><a href="contact.html"><i class="fa fa-caret-right"></i>Contact
									us</a></li>
							<li><a href="privacy.html"><i class="fa fa-caret-right"></i>Privacy
									policy</a></li>
							<li><a href="terms-and-conditions.html"><i
									class="fa fa-caret-right"></i>Terms & Conditions</a></li>
						</ul>
					</div>
					<!--//footer-col-inner-->
				</div>
				<!--//foooter-col-->
				<div class="footer-col col-md-6 col-sm-8 newsletter">
					<div class="footer-col-inner">
						<h3>Join our mailing list</h3>
						<p>Subscribe to get our weekly newsletter delivered directly
							to your inbox</p>
						<form class="subscribe-form">
							<div class="form-group">
								<input type="email" class="form-control"
									placeholder="Enter your email" />
							</div>
							<input class="btn btn-theme btn-subscribe" type="submit"
								value="Subscribe">
						</form>

					</div>
					<!--//footer-col-inner-->
				</div>
				<!--//foooter-col-->
				<div class="footer-col col-md-3 col-sm-12 contact">
					<div class="footer-col-inner">
						<h3>Contact us</h3>
						<div class="row">
							<p class="adr clearfix col-md-12 col-sm-4">
								<i class="fa fa-map-marker pull-left"></i> <span
									class="adr-group pull-left"> <span
									class="street-address">Benedictine University</span><br> <span
									class="region">5700 College Rd</span><br> <span
									class="postal-code">60532</span><br> <span
									class="country-name">United States</span>
								</span>
							</p>
							<p class="tel col-md-12 col-sm-4">
								<i class="fa fa-phone"></i>(630) 829-6000
							</p>
							<p class="email col-md-12 col-sm-4">
								<i class="fa fa-envelope"></i><a href="#">benedictine@university.com</a>
							</p>
						</div>
					</div>
					<!--//footer-col-inner-->
				</div>
				<!--//foooter-col-->
			</div>
		</div>
	</div>
	<!--//footer-content-->
	<div class="bottom-bar">
		<div class="container">
			<div class="row">
				<small class="copyright col-md-6 col-sm-12 col-xs-12">Copyright
					@ 2014 College Green Online | Website template by <a href="#">3rd
						Wave Media</a>
				</small>
				<ul class="social pull-right col-md-6 col-sm-12 col-xs-12">
					<li><a href="https://twitter.com/BenU1887" target="_blank"><i
							class="fa fa-twitter"></i></a></li>
					<li><a href="https://www.facebook.com/BenedictineUniversity/"
						target="_blank"><i class="fa fa-facebook"></i></a></li>
					<li><a href="https://www.youtube.com/user/benu1887"
						target="_blank"><i class="fa fa-youtube"></i></a></li>
					<li><a
						href="https://www.linkedin.com/company/benedictine-university"
						target="_blank"><i class="fa fa-linkedin"></i></a></li>
					<li><a
						href="https://plus.google.com/113106216606814236277/posts"
						target="_blank"><i class="fa fa-google-plus"></i></a></li>
					<li><a href="https://www.instagram.com/benu1887/"
						target="_blank"><i class="fa fa-instagram"></i></a></li>
					<li class="row-end"><a href="https://www.ben.edu"
						target="_blank"><i class="fa fa-university"></i></a></li>
				</ul>
				<!--//social-->
			</div>
			<!--//row-->
		</div>
		<!--//container-->
	</div>
	<!--//bottom-bar--> </footer>
	<!--//footer-->

	<!-- Javascript -->
	<script type="text/javascript"
		src="content/templateAssets/assets/plugins/jquery-1.11.2.min.js"></script>
	<script type="text/javascript"
		src="content/templateAssets/assets/plugins/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="content/templateAssets/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="content/templateAssets/assets/plugins/bootstrap-hover-dropdown.min.js"></script>
	<script type="text/javascript"
		src="content/templateAssets/assets/plugins/back-to-top.js"></script>
	<script type="text/javascript"
		src="content/templateAssets/assets/plugins/jquery-placeholder/jquery.placeholder.js"></script>
	<script type="text/javascript"
		src="content/templateAssets/assets/plugins/pretty-photo/js/jquery.prettyPhoto.js"></script>
	<script type="text/javascript"
		src="content/templateAssets/assets/plugins/flexslider/jquery.flexslider-min.js"></script>
	<script type="text/javascript"
		src="content/templateAssets/assets/plugins/jflickrfeed/jflickrfeed.min.js"></script>
	<script type="text/javascript"
		src="content/templateAssets/assets/js/main.js"></script>
</body>
</html>