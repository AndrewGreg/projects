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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register Now!</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="navBar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col col-xs-12">
				<div class="page-header">
					<h2 style="padding-left: 100px;">Register</h2>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				<form action="/register" method="POST" name="register">


					<%
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
					%>

					<div class="form-group">
						<input type="text" class="form-control" name="firstName"
							placeholder="First Name *" value="<%=firstName%>" required>

						<%
							if (errors.get("firstName") != null) {
						%>

						<h6 style="color: red"><%=errors.get("firstName")%></h6>

						<%
							}
						%>
						<br>
						<input type="text" class="form-control" name="lastName"
							placeholder="Last Name *" value="<%=lastName%>" required>

						<%
							if (errors.get("lastName") != null) {
						%>

						<h6 style="color: red"><%=errors.get("lastName")%></h6>

						<%
							}
						%>
						<br>
						<input type="text" class="form-control" name="benEmail"
							placeholder="Benedictine E-mail *" value="<%=benEmail%>" required>

						<%
							if (errors.get("benEmail") != null) {
						%>

						<h6 style="color: red"><%=errors.get("benEmail")%></h6>

						<%
							}
						%>
						<br>
						<input type="text" class="form-control" name="personalEmail"
							placeholder="Personal E-mail" value="<%=personalEmail%>">

						<%
							if (errors.get("personalEmail") != null) {
						%>

						<h6 style="color: red"><%=errors.get("personalEmail")%></h6>

						<%
							}
						%>
						<br>
						<select name="gradYear" class="form-control" required>
							<option value="">Graduation Year (This option if none)</option>
						
						<%
						
						for(int year = 1900; year < 2100; year++) {
						
						%>
							<option value="<%=year %>"><%=year %></option>
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
						<select name="standing" class="form-control" required>
							<option value="">Standing</option>
							<option value="1">Student</option>
							<option value="2">Graduate</option>
							<option value="3">Faculty</option>
						</select>

						<%
							if (errors.get("standing") != null) {
						%>

						<h6 style="color: red"><%=errors.get("standing")%></h6>

						<%
							}
						%>
						<br>
						<input type="text" class="form-control" name="occupation"
							placeholder="Occupation" value="<%=occupation%>">

						<%
							if (errors.get("occupation") != null) {
						%>

						<h6 style="color: red"><%=errors.get("occupation")%></h6>

						<%
							}
						%>
						<br>
						<input type="text" class="form-control" name="title"
							placeholder="Title" value="<%=title%>">

						<%
							if (errors.get("title") != null) {
						%>

						<h6 style="color: red"><%=errors.get("title")%></h6>

						<%
							}
						%>
						<br>
						<input type="text" class="form-control" name="suffix"
							placeholder="Suffix" value="<%=suffix%>">

						<%
							if (errors.get("suffix") != null) {
						%>

						<h6 style="color: red"><%=errors.get("suffix")%></h6>

						<%
							}
						%>
						<br>
						<input type="password" class="form-control" name="password"
							placeholder="Password *" required><br> <input
							type="password" class="form-control" name="passConfirm"
							placeholder="Confirm Password *" required>

						<%
							if (errors.get("passwords") != null) {
						%>

						<h6 style="color: red"><%=errors.get("passwords")%></h6>

						<%
							}
						%>

					</div>
					<div>
						<p>* denotes required field.</p>
					</div>
					<button type="reset" class="btn btn-danger">Clear</button>
					<button type="submit" class="btn btn-primary">Register</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>