<%@ page import="edu.ben.template.model.User"%>
<%@ page import="edu.ben.template.dao.UserDao"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<User> rsvpList;
	if (request.getAttribute("rsvpList") != null) {
		rsvpList = (ArrayList<User>) request.getAttribute("rsvpListArray");
	} else {
		rsvpList = new ArrayList<User>();
	}
%>


<!-- Modal -->
<div class="modal fade" id="rsvpList" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="padding: 35px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					List of Attendants <span class="glyphicon glyphicon-ok"></span>
				</h4>
			</div>
			<div class="modal-body" style="padding: 40px 50px;">

				<div class="page-content table-content">

					<div class="row">
						<div class="table-responsive">
							<span class="counter pull-right"></span>
							<table id=indextable class="table table-hover results">
								<thead>
									<tr>
										<th class="text-center">First Name</th>
										<th class="text-center">Last Name </th>
										<th class="text-center">Status</th>
									</tr>
									<tr class="warning no-result">
										<td colspan="4"><i class="fa fa-warning"></i> No one has
											Rsvp yet.</td>
									</tr>
								</thead>
								<tbody>
									<%
										if (rsvpList.size() == 0) {
									%>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr class="warning no-result">
										<td colspan="4"><i class="fa fa-warning"></i>No one has
											Rsvp yet.</td>
									</tr>

									<%
										} else {
											String role = "";

											for (int i = 0; i < rsvpList.size(); i++) {
												if (rsvpList.get(i).isActive()) {
													if (rsvpList.get(i).getRole() == 1) {
														role = "Student";
													} else if (rsvpList.get(i).getRole() == 2) {
														role = "Alumni";
													} else if (rsvpList.get(i).getRole() == 3) {
														role = "Faculty";
													} else if (rsvpList.get(i).getRole() == 4) {
														role = "Admin";
													} else {
														role = "Role Error.";
													}

													String firstName = rsvpList.get(i) != null && rsvpList.get(i).getFirstName() != null
															? rsvpList.get(i).getFirstName() : "N/A";
													String lastName = rsvpList.get(i) != null && rsvpList.get(i).getLastName() != null
															? rsvpList.get(i).getLastName() : "N/A";
									%>

									<tr class='clickable-row row-link'
										data-href='/Alumni-Tracker/user/<%=rsvpList.get(i).getId()%>'>
										<td align="center"><%=firstName%></td>
										<td align="center"><%=lastName%></td>
										<td align="center"><%=role%></td>
									</tr>
									<%
										}
											}
										}
									%>
								</tbody>
							</table>
							<!--//table-->
						</div>
						<!--//table-responsive-->
					</div>
				</div>

			</div>
		</div>
	</div>
</div>