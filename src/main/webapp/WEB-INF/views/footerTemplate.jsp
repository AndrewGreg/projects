<!-- ******FOOTER****** -->
<footer class="footer">
	<div class="footer-content">
		<div class="container">
			<div class="row">
				<div class="footer-col col-md-3 col-sm-4 about">
					<div class="footer-col-inner">
						<h3>About</h3>
						<ul>
							<li><a href="/Alumni-Tracker/content/img/BuMap.jpg"><i
									class="fa fa-caret-right"></i>Campus Map</a></li>
							<li><a href="/Alumni-Tracker/eventsTemplate"><i
									class="fa fa-caret-right"></i>Events</a></li>
							<li><a href="/Alumni-Tracker/jobsTemplate"><i
									class="fa fa-caret-right"></i>Jobs</a></li>
							<li><a href="/Alumni-Tracker/testimonialList"><i
									class="fa fa-caret-right"></i>Testimonials</a></li>
						</ul>
					</div>
					<!--//footer-col-inner-->
				</div>
				<!--//footer-col-->
				<div class="footer-col col-md-6 col-sm-8 newsletter">
					<div class="footer-col-inner">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d13982.388980346223!2d-88.1081289788033!3d41.77855593726398!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x880e572eebeb741b%3A0x595f78ae69ec2107!2sBenedictine+University!5e0!3m2!1sen!2sus!4v1457379042884"
							width="100%" height="250" frameborder="0" style="border: 0"
							allowfullscreen></iframe>

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
								<i class="fa fa-envelope"></i><a
									href="mailto:benedictine@university.com?">benedictine@university.com</a>
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
					@ 2016 Benedictine University | Website template by 3rd Wave Media
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
	<!--//bottom-bar-->
</footer>
<!--//footer-->

<!-- Javascript -->
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/plugins/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/plugins/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/plugins/bootstrap-hover-dropdown.min.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/plugins/back-to-top.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/plugins/jquery-placeholder/jquery.placeholder.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/plugins/pretty-photo/js/jquery.prettyPhoto.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/plugins/flexslider/jquery.flexslider-min.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/plugins/jflickrfeed/jflickrfeed.min.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/templateAssets/assets/js/main.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/jQuery/clickable-row.js"></script>
<script type="text/javascript"
	src="/Alumni-Tracker/content/jQuery/table-search.js"></script>


<%
	String loginAttempt = request.getAttribute("loginAttempt") != null
			? (String) request.getAttribute("loginAttempt")
			: "";

	if (loginAttempt.equals("failure")) {
%>
<script type="text/javascript">
	$(document).ready(function() {

		$('#loginModal').modal('show');

	});
</script>

<%
	}
%>

<%
	String eventCreation = request.getAttribute("eventCreation") != null
			? (String) request.getAttribute("eventCreation")
			: "";

	if (eventCreation.equals("true")) {
%>
<jsp:include page="eventCreationModal.jsp" />
<script type="text/javascript">
	$(document).ready(function() {

		$('#eventCreation').modal('show');

	});
</script>

<%
	}
%>


<%
	String eventDeletion = request.getAttribute("eventDeletion") != null
			? (String) request.getAttribute("eventDeletion")
			: "";

	if (eventDeletion.equals("true")) {
%>
<jsp:include page="deleteEventModal.jsp" />
<script type="text/javascript">
	$(document).ready(function() {

		$('#eventDeletion').modal('show');

	});
</script>

<%
	}
%>


<%
	String jobCreation = request.getAttribute("jobCreation") != null
			? (String) request.getAttribute("jobCreation")
			: "";

	if (jobCreation.equals("true")) {
%>
<jsp:include page="jobCreationModal.jsp" />
<script type="text/javascript">
	$(document).ready(function() {

		$('#jobCreation').modal('show');

	});
</script>

<%
	}
%>


<%
	String jobDeletion = request.getAttribute("jobDeletion") != null
			? (String) request.getAttribute("jobDeletion")
			: "";

	if (jobDeletion.equals("true")) {
%>
<jsp:include page="deleteJobModal.jsp" />
<script type="text/javascript">
	$(document).ready(function() {

		$('#jobDeletion').modal('show');
	});
</script>
<%
	}
%>

<%
	String testimonialCreation = request.getAttribute("testimonialCreation") != null
			? (String) request.getAttribute("testimonialCreation")
			: "";

	if (testimonialCreation.equals("true")) {
%>
<jsp:include page="testimonialCreationModal.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

		$('#testimonialCreation').modal('show');
	});
</script>
<%
	}
%>


<%
	String testimonialAttempt = request.getAttribute("testimonialAttempt") != null
			? (String) request.getAttribute("testimonialAttempt")
			: "";

	if (testimonialAttempt.equals("failure")) {
%>
<script type="text/javascript">
	$(document).ready(function() {

		$('#testimonialModal').modal('show');

	});
</script>

<%
	}
%>

<%
	String addRsvpCheck = request.getAttribute("addRsvp") != null
			? (String) request.getAttribute("addRsvp")
			: "";

	if (addRsvpCheck.equals("true")) {
%>

<jsp:include page="rsvpAddModal.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

		$('#addRsvp').modal('show');

	});
</script>

<%
	}
%>


<%
	String rsvpListCheck = request.getAttribute("rsvpList") != null
			? (String) request.getAttribute("rsvpList")
			: "";

	if (rsvpListCheck.equals("true")) {
%>

<jsp:include page="rsvpList.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

		$('#rsvpList').modal('show');

	});
</script>

<%
	}
%>

<%
	String deleteRsvp = request.getAttribute("deleteRsvp") != null
			? (String) request.getAttribute("deleteRsvp")
			: "";

	if (deleteRsvp.equals("true")) {
%>

<jsp:include page="deleteRsvp.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

		$('#deleteRsvp').modal('show');

	});
</script>

<%
	}
%>
