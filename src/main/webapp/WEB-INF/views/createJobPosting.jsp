<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="edu.ben.template.model.JobPosting"%>
<%@ page import="edu.ben.template.model.User"%>
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
<title>Create a posting</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="navBar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col col-xs-12">
				<div class="page-header">
					<h2 style="padding-left: 100px;">Create a new job posting</h2>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				<form action="/createJobPosting" method="POST"
					name="createJobPosting">

					<%
						String name = (request.getParameter("name") == null) ? "" : (String) request.getParameter("name");
						String company = (request.getParameter("company") == null) ? "" : (String) request.getParameter("company");
						String description = (request.getParameter("description") == null) ? ""
								: (String) request.getParameter("description");
					%>

					<div class="form-group">

						<input type="text" class="form-control" name="name"
							placeholder="Job name *" value="<%=name%>" required>

						<%
							if (errors.get("name") != null) {
						%>

						<h6 style="color: red"><%=errors.get("name")%></h6>

						<%
							}
						%>
						<br> <input type="text" class="form-control" name="company"
							placeholder="Company *" value="<%=company%>" required>

						<%
							if (errors.get("company") != null) {
						%>

						<h6 style="color: red"><%=errors.get("company")%></h6>

						<%
							}
						%>
						<br>

						<textarea class="form-control" rows="8" name="description"
							placeholder="Job description *"></textarea>

						<%
							if (errors.get("description") != null) {
						%>

						<h6 style="color: red"><%=errors.get("description")%></h6>

						<%
							}
						%>

						<br>
					</div>
					<div>
						<p>* denotes required field.</p>
					</div>
					<button type="reset" class="btn btn-danger">Clear</button>
					<button type="submit" class="btn btn-primary">Create
						posting</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>