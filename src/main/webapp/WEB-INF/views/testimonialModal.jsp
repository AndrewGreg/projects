
<%
	String testimonialAttempt = request.getAttribute("testimonialAttempt") != null
			? (String) request.getAttribute("testimonialAttempt") : "";
	String testimonial = request.getAttribute("testimonial") != null
			? (String) request.getAttribute("testimonial") : "";
	String errors = request.getAttribute("errors") != null ? (String) request.getAttribute("errors") : "";
%>

<!-- Modal -->
<div class="modal fade" id="testimonialModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="padding: 35px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modalh4">
					<span class="glyphicon glyphicon-pencil"></span> Tell us what you
					think!
				</h4>
			</div>
			<div class="modal-body" style="padding: 40px 50px;">
				<%
					if (testimonialAttempt.equals("failure")) {
				%>
				<small id="testimonialErrorMsg"><%=errors%></small>

				<%
					}
				%>
				<form role="form" action="/Alumni-Tracker/testimonial" method="POST"
					name="testimonialForm">
					<div class="form-group" data-name="testimonial">
						<textarea class="form-control" rows="8" id="testimonial"
							name="testimonial" placeholder="I think..." class="form-control"
							autofocus><%=testimonial%></textarea>
					</div>
					<button type="submit" class="btn btn-theme btn-block">
						<span class="glyphicon glyphicon-send"></span> Submit
					</button>
				</form>
			</div>
		</div>

	</div>
</div>