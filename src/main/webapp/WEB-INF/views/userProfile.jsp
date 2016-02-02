<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="navBar.jsp" />
	<br>

	<!-- Start of Profile -->
	<div class="container">
		<div class="row">
			<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
				<A href="edit.jsp">Edit Profile</A> <A href="logout.jsp">Logout</A>

				<!-- 	<p class=" text-info">May 05,2014,03:00 pm</p> -->
			</div>
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Access name from database</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<!-- Grab picture from databse. -->
								<img alt="User Pic" src="content/img/BenedictineLogo.gif"
									class="img-circle img-responsive" height=300px width=300px>
							</div>
							<br>


							<!-- Start of information -->
							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Current Occupation:</td>
											<td>Access from Database</td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>
										<tr>
											<td>Year Expected to Graduate:</td>
											<td>Access from Database</td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>
										<tr>
											<td>Areas of interest:</td>
											<td>Access from Database</td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>

										<tr>
										<tr>
											<td>Biography:</td>
											<td>Access from Database</td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>
										<tr>
											<td>Experiences:</td>
											<td>Access from Database</td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>
										<tr>
											<td>Email</td>
											<td><a href="mailfrom:info@support.com">Pull email
													from database</a></td>
									</tbody>
								</table>

								<a href="#" class="btn btn-primary">Settings</a> <a href="#"
									class="btn btn-primary">Another button</a>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a data-original-title="Broadcast Message" data-toggle="tooltip"
							type="button" class="btn btn-sm btn-primary"><i
							class="glyphicon glyphicon-envelope"></i></a> <span
							class="pull-right"> <a href="edit.html"
							data-original-title="Edit this user" data-toggle="tooltip"
							type="button" class="btn btn-sm btn-warning"><i
								class="glyphicon glyphicon-edit"></i></a> <a
							data-original-title="Remove this user" data-toggle="tooltip"
							type="button" class="btn btn-sm btn-danger"><i
								class="glyphicon glyphicon-remove"></i></a>
						</span>
					</div>

				</div>
			</div>
		</div>
	</div>

<jsp:include page="footer.jsp" />

</body>
</html>