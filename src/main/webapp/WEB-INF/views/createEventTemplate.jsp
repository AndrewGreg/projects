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
	String startTime = (request.getParameter("startTime") == null) ? ""
			: (String) request.getParameter("startTime");
	String endTime = (request.getParameter("endTime") == null) ? "" : (String) request.getParameter("endTime");
%>
<!DOCTYPE html>
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
			<div class="page-wrapper ben-container">
				<header class="page-heading clearfix pageHeading">
				<h1 class="heading-title pull-left">Create an Event</h1>
				<div class="breadcrumbs pull-right">
					<ul class="breadcrumbs-list">
						<li class="breadcrumbs-label">You are here:</li>
						<li><a href="/Alumni-Tracker/">Home</a><i class="fa fa-angle-right"></i></li>
						<li><a href="/Alumni-Tracker/eventsTemplate">Events</a><i
							class="fa fa-angle-right"></i></li>
						<li class="current">Create Event</li>
					</ul>
				</div>
				<!--//breadcrumbs--> </header>
				<br>
				<div class="row">
					<div class="col-md-6 col-md-offset-3">

						<form action="/Alumni-Tracker/createNewEvent" method="POST" name="createNewEvent">

							<div class="row">
								<!-- Input control group -->
								<div class="form-group col-sm-12">
									<!-- Title -->
									<label class="control-label" for="name">*Event Name:</label>
									<div class="controls">
										<input type="text" id="name" name="name" placeholder="Event"
											class="form-control" value="<%=name%>" autofocus>

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
									<label class="control-label" for="eventDate">*Event
										Date (mm/dd/yyyy):</label>
									<div class="bfh-datepicker" data-format="m/d/y"
										data-name="date" id="eventDate">
										<div class="input-prepend bfh-datepicker-toggle"
											data-toggle="bfh-datepicker">
											<span class="add-on"><i class="icon-calendar"></i></span> <input
												type="text" class="input-medium" name="date"
												value="<%=date%>">
										</div>
										<div class="bfh-datepicker-calendar">
											<table class="calendar table table-bordered">
												<thead>
													<tr class="months-header">
														<th class="month" colspan="4"><a class="previous"
															href="#"><i class="icon-chevron-left"></i></a> <span></span>
															<a class="next" href="#"><i
																class="icon-chevron-right"></i></a></th>
														<th class="year" colspan="3"><a class="previous"
															href="#"><i class="icon-chevron-left"></i></a> <span></span>
															<a class="next" href="#"><i
																class="icon-chevron-right"></i></a></th>
													</tr>
													<tr class="days-header">
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>


									<%
										if (errors.get("date") != null) {
									%>

									<span class="help-block" style="color: #e60000"> <%=errors.get("date")%></span>

									<%
										}
									%>


								</div>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="form-group col-sm-6">
									<!-- Title -->
									<label class="control-label" for="location">*Event
										Location:</label>
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
								<!-- Input control group -->
								<div class="form-group col-sm-6">
									<!-- Title -->
									<label class="control-label" for="eventStartTime">*Start
										Time:</label>
									<div class="controls">
										<div class="bfh-timepicker" id="eventStartTime"
											data-time="08:00" data-name="startTime">
											<div class="input-prepend bfh-timepicker-toggle"
												data-toggle="bfh-timepicker">
												<span class="add-on"><i class="icon-time"></i></span> <input
													name="startTime" type="text" class="input-medium"
													value="08:00">
											</div>
											<div class="bfh-timepicker-popover">
												<table class="table">
													<tbody>
														<tr>
															<td class="hour"><a class="next" href="#"><i
																	class="icon-chevron-up"></i></a><br> <input
																type="text" class="input-mini" readonly><br>
																<a class="previous" href="#"><i
																	class="icon-chevron-down"></i></a></td>
															<td class="separator">:</td>
															<td class="minute"><a class="next" href="#"><i
																	class="icon-chevron-up"></i></a><br> <input
																type="text" class="input-mini" readonly><br>
																<a class="previous" href="#"><i
																	class="icon-chevron-down"></i></a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>

										

									</div>
								</div>
								<!-- /Input control group -->

								<!-- Input control group -->
								<div class="form-group col-sm-6">
									<!-- Title -->
									<label class="control-label" for="eventEndTime">*End
										Time:</label>
									<div class="controls">
										<div class="bfh-timepicker" id="eventEndTime"
											data-time="08:00" data-name="endTime">
											<div class="input-prepend bfh-timepicker-toggle"
												data-toggle="bfh-timepicker">
												<span class="add-on"><i class="icon-time"></i></span> <input
													name="endTime" type="text" class="input-medium">
											</div>
											<div class="bfh-timepicker-popover">
												<table class="table">
													<tbody>
														<tr>
															<td class="hour"><a class="next" href="#"><i
																	class="icon-chevron-up"></i></a><br> <input
																type="text" class="input-mini" readonly><br>
																<a class="previous" href="#"><i
																	class="icon-chevron-down"></i></a></td>
															<td class="separator">:</td>
															<td class="minute"><a class="next" href="#"><i
																	class="icon-chevron-up"></i></a><br> <input
																type="text" class="input-mini" readonly><br>
																<a class="previous" href="#"><i
																	class="icon-chevron-down"></i></a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								<!-- /Input control group -->
							</div>
							<%
											if (errors.get("times") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("times")%></span>

										<%
											}
										%>
							<br>


							<div class="row">
								<!-- Input control group -->
								<div class="form-group col-sm-12">
									<!-- Title -->
									<label class="control-label" for="description">*Description:</label>
									<div class="controls">
										<textarea rows="5" id="description" name="description"
											placeholder="This is the event description."
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
								<div class="form-group col-sm-12">
									<div class="checkbox">
										<label> <input type="checkbox" name="public">
											This is a public event.
										</label>
										<div class="pull-right">
											<button type="reset" class="btn btn-danger">Clear</button>
											<button type="submit" class="btn btn-primary">Create
												Event</button>
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
	<script type="text/javascript"
		src="/Alumni-Tracker/content/BootstrapFormHelpers/dist/js/bootstrap-formhelpers.js"></script>
	<script type="text/javascript"
		src="/Alumni-Tracker/content/BootstrapFormHelpers/js/lang/en_US/bootstrap-formhelpers-datepicker.en_US.js"></script>
	<script type="text/javascript"
		src="/Alumni-Tracker/content/BootstrapFormHelpers/js/bootstrap-formhelpers-datepicker.js"></script>
	<script type="text/javascript"
		src="/Alumni-Tracker/content/BootstrapFormHelpers/js/lang/en_US/bootstrap-formhelpers-timepicker.en_US.js"></script>
	<script type="text/javascript"
		src="/Alumni-Tracker/content/BootstrapFormHelpers/js/bootstrap-formhelpers-timepicker.js"></script>
</body>
</html>