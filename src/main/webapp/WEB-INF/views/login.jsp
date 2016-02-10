<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="javax.servlet.http.HttpSession"%>

<%
	HttpSession ses = request.getSession();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<!-- Bootstrap Core CSS -->
<link href="content/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="content/css/login.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="navBar.jsp" />

	<div class="container">


		<div class="wrapper">

			<div class="row" style="margin-top: 150px">
				<div
					class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
					<%
						if (request.getAttribute("invalid") != null) {
					%>
					<div class="alert alert-danger">
						<strong>Incorrect login!</strong> Please try again.
					</div>
					<%
						request.setAttribute("invalid", null);
						}

					%>

					<form action="login" method="post" name="LoginForm"
						class="form-signin">
						<h3 class="form-signin-heading">Login</h3>
						<hr class="colorgraph">
						<br> <input type="text" class="form-control" name="email"
							placeholder="Email" required="" autofocus="" /> <input
							type="password" class="form-control" name="password"
							placeholder="Password" required="" />

						<button class="btn btn-lg btn-primary btn-block" name="Submit"
							value="Login" type="Submit">Login</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>