<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="edu.ben.template.model.Job"%>
<%@page import="edu.ben.template.model.User"%>

<%
	User currentUser = (User) request.getAttribute("currentUser");
%>
<%
	Job currentJob = (Job) request.getAttribute("currentJob");
	String id = Long.toString(currentJob.getId());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=currentJob.getName()%></title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>

 <div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container">
            <div class="page-wrapper">
                <header class="page-heading clearfix">
                    <h1 class="heading-title pull-left" style="color: white">Jobs</h1>
                    <div class="breadcrumbs pull-right">
                        <ul class="breadcrumbs-list">
                            <li class="breadcrumbs-label">You are here:</li>
                            <li><a href="/">Home</a><i class="fa fa-angle-right"></i></li>
                            <li><a href="/jobs">Jobs</a><i class="fa fa-angle-right"></i></li>
                            <li class="current"  style="color: white"><%=currentJob.getName()%></li>
                        </ul>
                    </div><!--//breadcrumbs-->
                </header> 
                 <div class="page-content">
                    <div class="row page-row">
                        <div class="jobs-wrapper col-md-8 col-sm-7">           
                            <h3 class="title" style="color: white"><%=currentJob.getName()%></h3>
                             <%if((currentUser.getId() == currentJob.getPoster().getId()) || currentUser.getRole() > 2){%>
                            		<a href="/editAJob/<%=id%>" style="color: white">Edit Job</a>
                            <%
                            	}
                            %>
                            <div class="box box-border page-row">
                                <ul class="list-unstyled">
                                <%if(currentJob.getPoster() != null && currentJob.getPoster().getFirstName() != null && currentJob.getPoster().getEmail() != null) { %>
                                	<li><strong>Posted By:</strong> <%=currentJob.getPoster().getFirstName()%> <%=currentJob.getPoster().getLastName()%></li>
                                    <li><strong>Location:</strong><%=currentJob.getLocation()%> </li>
                                    <li><strong>Salary:</strong> <%=currentJob.getStart_salary()%> - <%=currentJob.getEnd_salary()%></li>
                                    <li><strong>Hours:</strong> <%=currentJob.getHours()%></li>
                                    <%-- <li style="color: white"><strong>Closing date:</strong> <%=//currentJob.getEndDate()%></li> --%>
                                    <li><strong>Contact:</strong> <%=currentJob.getPoster().getEmail()%></li>
                                </ul>                                
                            </div>
                            <p style="color: white"> <%=currentJob.getDescription()%></p>
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