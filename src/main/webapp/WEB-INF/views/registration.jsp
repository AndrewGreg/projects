<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.ben.template.model.Major"%>
<%@ page import="edu.ben.template.model.Title"%>
<%
	HashMap<String, String> errors;
	if (request.getAttribute("errors") != null) {
		errors = (HashMap<String, String>) request.getAttribute("errors");
	} else {
		errors = new HashMap<String, String>();
	}

	ArrayList<Major> majorList;
	if (request.getAttribute("majorList") != null) {
		majorList = (ArrayList<Major>) request.getAttribute("majorList");
	} else {
		majorList = new ArrayList<Major>();
	}

	ArrayList<Title> titleList;
	if (request.getAttribute("titleList") != null) {
		titleList = (ArrayList<Title>) request.getAttribute("titleList");
	} else {
		titleList = new ArrayList<Title>();
	}

	String firstName = (request.getParameter("firstName") == null) ? ""
			: (String) request.getParameter("firstName");
	String lastName = (request.getParameter("lastName") == null) ? ""
			: (String) request.getParameter("lastName");
	String benEmail = (request.getParameter("benEmail") == null) ? ""
			: (String) request.getParameter("benEmail");
	String personalEmail = (request.getParameter("personalEmail") == null) ? ""
			: (String) request.getParameter("personalEmail");
	String gradYear = (request.getParameter("gradYear") == null) ? ""
			: (String) request.getParameter("gradYear");
	String occupation = (request.getParameter("occupation") == null) ? ""
			: (String) request.getParameter("occupation");
	String title = (request.getParameter("title") == null) ? "" : (String) request.getParameter("title");
	String suffix = (request.getParameter("suffix") == null) ? "" : (String) request.getParameter("suffix");
	String bio = (request.getParameter("bio") == null) ? "" : (String) request.getParameter("bio");
	String phone = (request.getParameter("phone") == null) ? "" : (String) request.getParameter("phone");
	String workPhone = (request.getParameter("workPhone") == null) ? ""
			: (String) request.getParameter("workPhone");
	String gradSchool = (request.getParameter("gradSchool") == null) ? ""
			: (String) request.getParameter("gradSchool");
	String linkedin = (request.getParameter("linkedin") == null) ? ""
			: (String) request.getParameter("linkedin");
	String major = (request.getParameter("major") == null) ? "" : (String) request.getParameter("major");
	String minor = (request.getParameter("minor") == null) ? "" : (String) request.getParameter("minor");
	String concentration = (request.getParameter("concentration") == null) ? ""
			: (String) request.getParameter("concentration");
	String company = (request.getParameter("company") == null) ? "" : (String) request.getParameter("company");
	String experience = (request.getParameter("experience") == null) ? ""
			: (String) request.getParameter("experience");
%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
<title>Registration</title>
<jsp:include page="headerTemplate.jsp" />
<link id="theme-style" rel="stylesheet"
	href="/Alumni-Tracker/content/css/registrationForm.css">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />
		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper ben-container">
				<header class="page-heading clearfix pageHeading">
					<h1 class="heading-title pull-left">Account Registration</h1>
					<div class="breadcrumbs pull-right">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/Alumni-Tracker/">Home</a><i
								class="fa fa-angle-right"></i></li>
							<li class="current">Registration</li>
						</ul>
					</div>
					<!--//breadcrumbs-->
				</header>
				<div class="row">
					<div class="col-xs-6 col-xs-offset-3">
						<div class="stepwizard">
							<div class="stepwizard-row setup-panel">
								<div class="stepwizard-step">
									<a href="#step-1" type="button"
										class="btn btn-primary btn-circle">1</a>
									<p>Contact Info</p>
								</div>
								<div class="stepwizard-step">
									<a href="#step-2" type="button"
										class="btn btn-default btn-circle" disabled="disabled">2</a>
									<p>Education Info</p>
								</div>
								<div class="stepwizard-step">
									<a href="#step-3" type="button"
										class="btn btn-default btn-circle" disabled="disabled">3</a>
									<p>Employment Info</p>
								</div>
								<div class="stepwizard-step">
									<a href="#step-4" type="button"
										class="btn btn-default btn-circle" disabled="disabled">4</a>
									<p>Complete</p>
								</div>

							</div>
						</div>
						<form action="/Alumni-Tracker/register" method="POST"
							name="register" enctype="multipart/form-data">

							<div class="row setup-content" id="step-1">


								<h3>Contact Information</h3>
								<br>

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="title">Title:</label>
									<div class="controls" data-name="title">
										<select name="title" id="title" class="form-control">
											<option value="-1">No Title</option>
											<%
												for (int i = 0; i < titleList.size(); i++) {
													String titleName = titleList.get(i).getName() != null ? titleList.get(i).getName() : "";
											%>
											<option value="<%=titleList.get(i).getId()%>"><%=titleName%></option>
											<%
												}
											%>
										</select>

										<%
											if (errors.get("title") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("title")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="firstName">*First
										Name:</label>
									<div class="controls" data-name="firstName">
										<input type="text" id="firstName" name="firstName"
											placeholder="John" class="form-control"
											value="<%=firstName%>">

										<%
											if (errors.get("firstName") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("firstName")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="lastName">*Last Name:</label>
									<div class="controls" data-name="lastName">
										<input type="text" id="lastName" name="lastName"
											placeholder="Doe" class="form-control" value="<%=lastName%>">

										<%
											if (errors.get("lastName") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("lastName")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="suffix">Suffix:</label>
									<div class="controls" data-name="suffix">
										<input type="text" id="suffix" name="suffix"
											placeholder="Ph.D" class="form-control" value="<%=suffix%>">

										<%
											if (errors.get("suffix") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("suffix")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="benEmail">*Benedictine
										E-mail (Must start with a letter and end in "@ben.edu"):</label>
									<div class="controls" data-name="benEmail">
										<input type="text" id="benEmail" name="benEmail"
											placeholder="jdoe@ben.edu" class="form-control"
											value="<%=benEmail%>">

										<%
											if (errors.get("benEmail") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("benEmail")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="personalEmail">Personal
										E-mail:</label>
									<div class="controls" data-name="personalEmail">
										<input type="text" id="personalEmail" name="personalEmail"
											placeholder="johnDoe@someplace.com" class="form-control"
											value="<%=personalEmail%>">

										<%
											if (errors.get("personalEmail") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("personalEmail")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="phone">Phone:</label>
									<div class="controls" data-name="phone">
										<input type="text" id="phone" name="phone"
											placeholder=" (000)000-0000"
											class="form-control input-medium bfh-phone"
											data-format=" (ddd)ddd-dddd" value="<%=phone%>">


										<%
											if (errors.get("phone") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("phone")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="workPhone">Work
										Phone:</label>
									<div class="controls" data-name="workPhone">
										<input type="text" id="workPhone" name="workPhone"
											placeholder=" (000)000-0000"
											class="form-control input-medium bfh-phone"
											data-format=" (ddd)ddd-dddd" value="<%=workPhone%>">


										<%
											if (errors.get("workPhone") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("workPhone")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="linkedin">LinkedIn:</label>

									<div class="controls" data-name="linkedin">
										<input type="text" id="linkedin" name="linkedin"
											placeholder="https://www.linkedin.com/in/john-doe"
											class="form-control" value="<%=linkedin%>">

										<%
											if (errors.get("linkedin") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("linkedin")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="bio">Biography:</label>
									<div class="controls" data-name="bio">
										<textarea rows="5" id="bio" name="bio"
											placeholder="This is my biography." class="form-control"
											value="<%=bio%>"></textarea>

										<%
											if (errors.get("bio") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("bio")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<div>
									<p>* denotes required field.</p>
								</div>
								<button class="btn btn-primary nextBtn pull-right" type="button">Next</button>
							</div>


							<div class="row setup-content" id="step-2">

								<h3>Education Information</h3>
								<br>

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="standing">*Standing:</label>

									<div class="controls" data-name="standing">
										<select name="standing" id="standing" class="form-control">

											<option value="1">Student</option>
											<option value="2">Alumnus/Alumna</option>
											<option value="3">Faculty</option>
										</select>

										<%
											if (errors.get("standing") != null) {
										%>

										<h6 style="color: red"><%=errors.get("standing")%></h6>

										<%
											}
										%>
									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="gradYear">Graduation
										Year:</label>
									<div class="controls" data-name="gradYear">
										<select name="gradYear" id="gradYear" class="form-control">
											<option value="0">None</option>

											<%
												for (int year = 2050; year > 1949; year--) {
											%>
											<option value="<%=year%>"><%=year%></option>
											<%
												}
											%>

										</select>


										<%
											if (errors.get("gradYear") != null) {
										%>

										<h6 style="color: red"><%=errors.get("gradYear")%></h6>

										<%
											}
										%>
										<br>
									</div>
								</div>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="gradSchool">Graduate
										School:</label>
									<div class="controls" data-name="gradSchool">
										<input type="text" id="gradSchool" name="gradSchool"
											placeholder="Benedictine University" class="form-control"
											value="<%=gradSchool%>">

										<%
											if (errors.get("gradSchool") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("gradSchool")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="major">*Major:</label>
									<div class="controls" data-name="major">
										<select name="major" class="form-control" required>

											<%
												String majorName = "";
												for (int i = 0; i < majorList.size(); i++) {
													majorName = majorList.get(i).getName() != null ? majorList.get(i).getName() : "";
											%>
											
												<option value="<%=majorList.get(i).getId()%>"><%=majorName%></option>

											<%
												}
											%>
										</select>
										<%
											if (errors.get("major") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("major")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<div>
									<p>* denotes required field.</p>
								</div>
								<button class="btn btn-primary nextBtn pull-right" type="button">Next</button>
							</div>

							<div class="row setup-content" id="step-3">

								<h3>Employment Information</h3>
								<br>

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="company">Company:</label>
									<div class="controls" data-name="company">
										<input type="text" id="company" name="company"
											placeholder="Microsoft" class="form-control"
											value="<%=company%>">

										<%
											if (errors.get("company") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("company")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="occupation">Occupation:</label>
									<div class="controls" data-name="occupation">
										<input type="text" id="occupation" name="occupation"
											placeholder="Software Engineer" class="form-control"
											value="<%=occupation%>">

										<%
											if (errors.get("occupation") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("occupation")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="experience">Experience:</label>
									<div class="controls" data-name="experience">
										<textarea rows="5" id="experience" name="experience"
											placeholder="Professional Experience." class="form-control"
											value="<%=experience%>"></textarea>

										<%
											if (errors.get("experience") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("experience")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<div>
									<p>* denotes required field.</p>
								</div>
								<button class="btn btn-primary nextBtn pull-right" type="button">Next</button>
							</div>

							<div class="row setup-content" id="step-4">

								<h3>Complete Registration</h3>
								<br>

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="password">*Password:</label>
									<div class="controls" data-name="password">
										<input type="password" id="password" name="password"
											placeholder="Password" class="form-control">
									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="control-group">
									<!-- Title -->
									<label class="control-label" for="passConfirm">*Confirm
										Password:</label>
									<div class="controls" data-name="passConfirm">
										<input type="password" id="passConfirm" name="passConfirm"
											placeholder="Confirm Password" class="form-control">

										<%
											if (errors.get("passwords") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("passwords")%></span>

										<%
											}
										%>

									</div>
								</div>
								<br>
								<!-- /Input control group -->

								<div>
									<p>* denotes required field.</p>
								</div>
								<div class="pull-right">
									<button type="submit" class="btn btn-primary">Register</button>
								</div>
							</div>
						</form>
						<br>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--//wrapper-->
	<jsp:include page="footerTemplate.jsp" />
	<script type="text/javascript"
		src="/Alumni-Tracker/content/jQuery/registrationForm.js"></script>
	<script type="text/javascript"
		src="/Alumni-Tracker/content/BootstrapFormHelpers/js/bootstrap-formhelpers-phone.js"></script>
</body>
</html>