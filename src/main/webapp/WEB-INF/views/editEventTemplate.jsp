<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="edu.ben.template.model.User"%>
<%@page import="edu.ben.template.model.Event"%>
<%@ page import="java.util.HashMap"%>
<%
	HashMap<String, String> errors;
	if (request.getAttribute("errors") != null) {
		errors = (HashMap<String, String>) request.getAttribute("errors");
	} else {
		errors = new HashMap<String, String>();
	}
%>

<%
	User currentUser = (User) request.getAttribute("currentUser");
%>
<%
	Event currentEvent = (Event) request.getAttribute("currentEvent");
	String id = Long.toString(currentEvent.getId());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create an Event</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>

 <div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />
		<!-- ******CONTENT****** -->
		<div class="content container">
			<div class="page-wrapper">
				<header class="page-heading clearfix">
				<h1 class="heading-title pull-left" style="color: white">Edit
					an Event</h1>
				<div class="breadcrumbs pull-right">
					<ul class="breadcrumbs-list">
						<li class="breadcrumbs-label">You are here:</li>
						<li><a href="index.html">Home</a><i class="fa fa-angle-right"></i></li>
						<li><a href="jobs.html">Events</a><i class="fa fa-angle-right"></i></li>
						<li class="current" style="color: white">Edit an Event</li>
					</ul>
				</div>
				<!--//breadcrumbs--> </header>
				<div class="container">
					<div class="row">
						<article class="contact-form col-md-8 col-sm-7  page-row">
						<form action="/Alumni-Tracker/editAnEvent" method="POST"
							name="editAnEvent">

							<%
								String name = (request.getParameter("name") == null) ? "" : (String) request.getParameter("name");
								String description = (request.getParameter("description") == null) ? "" : (String) request.getParameter("description");
								String location = (request.getParameter("location") == null) ? "" : (String) request.getParameter("location");
								String date = (request.getParameter("date") == null) ? "" : (String) request.getParameter("date");
							%>


							<div class="form-group col-sm-6">
								<label style="color: white">Event Name </label><input type="text"
									class="form-control" name="name" value="<%=currentEvent.getName()%>" required>

								<%
									if (errors.get("name") != null) {
								%>

								<h6 style="color: red"><%=errors.get("name")%></h6>

								<%
									}
								%>
							</div>

							<div class="form-group col-sm-3">
								<label style="color: white">Location </label><input type="text"
									class="form-control" name="location" value="<%=location%>"
									required>

								<%
									if (errors.get("location") != null) {
								%>

								<h6 style="color: red"><%=errors.get("location")%></h6>

								<%
									}
								%>
							</div>

							<div class="form-group col-sm-3">
								<label style="color: white">Date </label><input type="text"
									class="form-control" name=date value="<%=date%>"
									required>

								<%
									if (errors.get("date") != null) {
								%>

								<h6 style="color: red"><%=errors.get("date")%></h6>

								<%
									}
								%>
							</div>
							
							<div class="form-group col-sm-12">
								<label style="color: white">Description </label>
								<textarea class="form-control" rows="8" name="description"></textarea>

								<%
									if (errors.get("description") != null) {
								%>

								<h6 style="color: red"><%=errors.get("description")%></h6>

								<%
									}
								%>

								<br>
								<button type="reset" class="btn btn-danger">Clear</button>
								<button type="submit" class="btn btn-primary">Create
									posting</button>
							</div>

						</form>
						</article>
					</div>
				</div>

			</div>
		</div>
	</div><!--//wrapper-->
	<!-- ******FOOTER****** -->
	<jsp:include page="footerTemplate.jsp" />
</body>
</html>