<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap"%>
<%
	HashMap<String, String> errors;
	if (request.getAttribute("errors") != null) {
		errors = (HashMap<String, String>) request.getAttribute("errors");
	} else {
		errors = new HashMap<String, String>();
	}

	String name = (request.getParameter("name") == null) ? "" : (String) request.getParameter("name");
	String description = (request.getParameter("description") == null) ? ""
			: (String) request.getParameter("description");
	String location = (request.getParameter("location") == null) ? ""
			: (String) request.getParameter("location");
	String date = (request.getParameter("date") == null) ? "" : (String) request.getParameter("date");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Event</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>

	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper">
				<header class="page-heading clearfix">
				<h1 class="heading-title pull-left" style="color: white">Create
					an Event</h1>
				<div class="breadcrumbs pull-right">
					<ul class="breadcrumbs-list">
						<li class="breadcrumbs-label">You are here:</li>
						<li><a href="/">Home</a><i class="fa fa-angle-right"></i></li>
						<li><a href="/eventsTemplate">Events</a><i
							class="fa fa-angle-right"></i></li>
						<li class="current" style="color: white">Create an Event</li>
					</ul>
				</div>
				<!--//breadcrumbs--> </header>
				<div class="container">
					<div class="row">
						<article class="contact-form col-md-8 col-sm-7  page-row">
						<form action="/createNewEvent" method="POST" name="createNewEvent">




							<div class="form-group col-sm-6">
								<label style="color: white">Event Name </label><input
									type="text" class="form-control" name="name" value="<%=name%>"
									required>

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
									class="form-control" name=date value="<%=date%>" required>

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

							<div class="bfh-datepicker">
								<div class="input-prepend bfh-datepicker-toggle"
									data-toggle="bfh-datepicker">
									<span class="add-on"><i class="icon-calendar"></i></span> <input
										type="text" class="input-medium" readonly>
								</div>
								<div class="bfh-datepicker-calendar">
									<table class="calendar table table-bordered">
										<thead>
											<tr class="months-header">
												<th class="month" colspan="4"><a class="previous"
													href="#"><i class="icon-chevron-left"></i></a> <span></span>
													<a class="next" href="#"><i class="icon-chevron-right"></i></a>
												</th>
												<th class="year" colspan="3"><a class="previous"
													href="#"><i class="icon-chevron-left"></i></a> <span></span>
													<a class="next" href="#"><i class="icon-chevron-right"></i></a>
												</th>
											</tr>
											<tr class="days-header">
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>

						</form>
						</article>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!--//wrapper-->
	<jsp:include page="footerTemplate.jsp" />
	<script type="text/javascript"
		src="/content/BootstrapFormHelpers/js/lang/en_US/bootstrap-formhelpers-datepicker.en_US.js"></script>
	<script type="text/javascript"
		src="/content/BootstrapFormHelpers/js/bootstrap-formhelpers-datepicker.js"></script>
</body>
</html>