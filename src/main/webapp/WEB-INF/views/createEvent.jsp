<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%
	HashMap<String, String> errors;
	if (request.getAttribute("errors") != null) {
		errors = (HashMap<String, String>) request.getAttribute("errors");
	} else {
		errors = new HashMap<String, String>();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create an event</title>
<jsp:include page="header.jsp" />
<script src="content/jQuery/bootstrap-datepicker.js"></script>
<link href="content/css/datepicker.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="navBar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col col-xs-12">
				<div class="page-header">
					<h2 style="padding-left: 100px;">Create a new event</h2>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				<form action="/createEvent" method="POST" name="createEvent">

					<%
						String name = (request.getParameter("name") == null) ? "" : (String) request.getParameter("name");
						String description = (request.getParameter("description") == null)
								? ""
								: (String) request.getParameter("description");
					%>
					<div class="row">
						<div class="form-group">
							<div class="col-lg-6">
								<input type="text" class="form-control" name="name"
									placeholder="Event name *" value="<%=name%>" required>

								<%
									if (errors.get("name") != null) {
								%>

								<h6 style="color: red"><%=errors.get("name")%></h6>

								<%
									}
								%>
							</div>
							<div class="col-lg-2">
								<div class="form-group">
									<input class="datepicker" placeholder="Event date *" name="date">
								</div>

								<script>
									$('.datepicker').datepicker();
								</script>
							</div>
						</div>
					</div>

					<div class="form-group">
						<textarea class="form-control" rows="8" name="description"
							placeholder="Event description *"></textarea>

						<%
							if (errors.get("description") != null) {
						%>

						<h6 style="color: red"><%=errors.get("description")%></h6>

						<%
							}
						%><br>
					</div>

					<div>
						<p>* denotes required field.</p>
					</div>
					<button type="reset" class="btn btn-danger">Clear</button>
					<button type="submit" class="btn btn-primary">Create
						event</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>