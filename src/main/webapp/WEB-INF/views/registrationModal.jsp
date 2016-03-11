<!-- Modal -->
<div class="modal fade" id="registerModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="padding: 35px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-pencil"></span> Register
				</h4>
			</div>
			<div class="modal-body" style="padding: 40px 50px;">
				<form role="form" action="/register" method="POST" name="registrationForm">
				
				<!-- Finish the registration form -->
					<div class="form-group">
						<label for="usrname"><span
							class="glyphicon glyphicon-user"></span> Username</label> <input
							type="text" class="form-control" id="usrname"
							placeholder="Enter email" name="email"><br>
					</div>
					<div class="form-group">
						<label for="psw"><span
							class="glyphicon glyphicon-eye-open"></span> Password</label> <input
							type="text" class="form-control" id="psw"
							placeholder="Enter password" name="password"><br> <br>
					</div>
					<button type="submit" class="btn btn-theme btn-block">
						<span class="glyphicon glyphicon-off"></span> Register
					</button>
				</form>
			</div>
		</div>
	</div>
</div>