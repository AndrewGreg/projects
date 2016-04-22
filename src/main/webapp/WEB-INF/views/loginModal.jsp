
<%
	String loginAttempt = request.getAttribute("loginAttempt") != null
			? (String) request.getAttribute("loginAttempt") : "";
%>

<!-- Modal -->
<div class="modal fade" id="loginModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="padding: 35px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modalh4">
					<span class="glyphicon glyphicon-lock"></span> Login
				</h4>
			</div>
			<div class="modal-body" style="padding: 40px 50px;">
				<%
					if (loginAttempt.equals("failure")) {
				%>
				<small id="loginErrorMsg">Invalid login. Please try again.</small>

				<%
					}
				%>
				<form role="form" action="/Alumni-Tracker/login" method="POST" name="loginForm">
					<div class="form-group">
						<label for="usrname"><span
							class="glyphicon glyphicon-user"></span> Username</label> <input
							type="text" class="form-control" id="usrname"
							placeholder="Enter email" name="email" autofocus><br>
					</div>
					<div class="form-group">
						<label for="psw"><span
							class="glyphicon glyphicon-eye-open"></span> Password</label> <input
							type="password" class="form-control" id="psw"
							placeholder="Enter password" name="password"><br> <br>
					</div>
					<button type="submit" class="btn btn-theme btn-block">
						<span class="glyphicon glyphicon-off"></span> Login
					</button>
				</form>
			</div>
		</div>

	</div>
</div>