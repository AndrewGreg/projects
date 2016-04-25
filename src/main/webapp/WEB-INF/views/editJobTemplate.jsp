<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.ben.template.model.Job"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.HashMap"%>
<%
HashMap<String, String> errors;
if (request.getAttribute("errors") != null) {
	errors = (HashMap<String, String>) request.getAttribute("errors");
} else {
	errors = new HashMap<String, String>();
}

String name = (request.getParameter("name") == null) ? "" : (String) request.getParameter("name");
String company = (request.getParameter("company") == null) ? "" : (String) request.getParameter("company");
String description = (request.getParameter("description") == null) ? ""
		: (String) request.getParameter("description");
String location = (request.getParameter("location") == null) ? ""
		: (String) request.getParameter("location");
String startSalary = (request.getParameter("startSalary") == null) ? ""
		: (String) request.getParameter("startSalary");
String endSalary = (request.getParameter("endSalary") == null) ? ""
		: (String) request.getParameter("endSalary");
String hours = (request.getParameter("hours") == null) ? "" : (String) request.getParameter("hours");
%>

<%
	User currentUser = (User) request.getAttribute("currentUser");
%>
<%
	Job editJob = (Job) request.getAttribute("editJob");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit a Job Posting</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper ben-container">
				<header class="page-heading clearfix pageHeading">
				<h1 class="heading-title pull-left">Edit a Job Posting</h1>
				<div class="breadcrumbs pull-right">
					<ul class="breadcrumbs-list">
						<li class="breadcrumbs-label">You are here:</li>
						<li><a href="/Alumni-Tracker/">Home</a><i class="fa fa-angle-right"></i></li>
						<li><a href="/Alumni-Tracker/jobs">Jobs</a><i class="fa fa-angle-right"></i></li>
						<li class="current">Edit Job</li>
					</ul>
				</div>
				<!--//breadcrumbs--> </header>
				<br>
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<form action="/Alumni-Tracker/editAJob/<%=editJob.getId() %>" method="POST" name="createJob">

							<div class="row">
								<!-- Input control group -->
								<div class="form-group col-sm-12">
									<!-- Title -->
									<label class="control-label" for="name">*Job Name:</label>
									<div class="controls">
										<input type="text" id="name" name="name" placeholder="Name"
											class="form-control" value="<%=editJob.getName()%>" autofocus>

										<%
											if (errors.get("name") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("name")%></span>

										<%
											}
										%>

									</div>
								</div>
								<!-- /Input control group -->
							</div>
							<br>
							<div class="row">
								<!-- Input control group -->
								<div class="form-group col-sm-6">
									<!-- Title -->
									<label class="control-label" for="company">*Company:</label>
									<div class="controls">
										<input type="text" id="company" name="company"
											placeholder="Company" class="form-control"
											value="<%=editJob.getCompany()%>">

										<%
											if (errors.get("company") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("company")%></span>

										<%
											}
										%>

									</div>
								</div>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="form-group col-sm-6">
									<!-- Title -->
									<label class="control-label" for="location">*Location:</label>
									<div class="controls">
										<input type="text" id="location" name="location"
											placeholder="Location" class="form-control"
											value="<%=location%>">

										<%
											if (errors.get("location") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("location")%></span>

										<%
											}
										%>

									</div>
								</div>
								<!-- /Input control group -->
							</div>
							<br>
							<div class="row">
								<div class="form-group col-sm-6">
									<!-- Input control group -->

									<!-- Title -->
									<label class="control-label" for="hours">*Hours:</label>
									<div class="controls">
										<select name="hours" class="form-control" required>
											<option value="1">Part-time</option>
											<option value="2">Full-time</option>
										</select>
										<%
											if (errors.get("hours") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("hours")%></span>

										<%
											}
										%>
									</div>
									<br>
									<!-- /Input control group -->
								</div>

								<div class="form-group col-sm-3">
									<!-- Input control group -->
									<label class="control-label" for="startSalary">Start
										Salary:</label>
									<div class="controls">
										<input type="number" id="startSalary" name="startSalary"
											placeholder="$" class="form-control" value="<%=startSalary%>"
											min="0" step="500" pattern="[0-9].">
									</div>

									<!-- /Input control group -->
								</div>

								<div class="form-group col-sm-3">
									<!-- Input control group -->
									<label class="control-label" for="endSalary">End
										Salary:</label>
									<div class="controls">
										<input type="number" id="endSalary" name="endSalary"
											placeholder="$" class="form-control" value="<%=endSalary%>"
											min="0" step="500" pattern="[0-9].">
									</div>

									<!-- /Input control group -->
								</div>

								<%
									if (errors.get("salary") != null) {
								%>

								<span class="help-block" style="color: #e60000"> <%=errors.get("salary")%></span>

								<%
									}
								%>
							</div>
							<div class="row">
								<!-- Input control group -->
								<div class="form-group col-sm-12">
									<!-- Title -->
									<label class="control-label" for="description">*Description:</label>
									<div class="controls">
										<textarea rows="5" id="description" name="description"
											placeholder="Job description and/or requirements."
											class="form-control"><%=description%></textarea>

										<%
											if (errors.get("description") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("description")%></span>

										<%
											}
										%>

									</div>
								</div>
								<!-- /Input control group -->
							</div>
							<br>
							<div class="row">
								<!-- Input control group -->
								<div class="form-group col-md-12">
									<div class="checkbox">
										<label> <input type="checkbox" name="public">
											This is a public posting.
										</label>
										<div class="pull-right">
											<button type="reset" class="btn btn-danger">Clear</button>
											<button type="submit" class="btn btn-primary">Edit Job</button>
										</div>
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-4">
									<span>* denotes required field.</span>
								</div>
							</div>
							<br>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--//wrapper-->
	<jsp:include page="footerTemplate.jsp" />
</body>
</html>