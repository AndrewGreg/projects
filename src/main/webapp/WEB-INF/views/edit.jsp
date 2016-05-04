<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.HashMap"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="edu.ben.template.model.Major"%>
<%@ page import="edu.ben.template.model.Title"%>
<%@ page import="edu.ben.template.model.Interest"%>
<%@ page import="edu.ben.template.model.UploadFile"%>
<%@ page import="java.util.ArrayList"%>
<%
	//User u = (User) request.getSession().getAttribute("user");
	UploadFile photo;
	//if(request.getAttribute("photo") != null){
	photo = (UploadFile) request.getAttribute("photo");
	//}
	User u = (User) request.getAttribute("profileUser");
	User currUser = (User) request.getAttribute("currUser");
	Title title = (Title) request.getAttribute("title");
	ArrayList<Major> m = (ArrayList<Major>) request.getAttribute("majors");
	ArrayList<Major> mi = (ArrayList<Major>) request.getAttribute("minors");
	ArrayList<Title> t = (ArrayList<Title>) request.getAttribute("titles");
	ArrayList<Interest> i = (ArrayList<Interest>) request.getAttribute("interests");
	ArrayList<Interest> uI = (ArrayList<Interest>) request.getAttribute("userInterests");
	ArrayList<ArrayList<Major>> c = (ArrayList<ArrayList<Major>>) request.getAttribute("concentrations");

	HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Account</title>
<jsp:include page="headerTemplate.jsp" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
    

var majors = [

              
	<% if (m != null && c != null) {
	for (int j = 0; j < m.size(); j++){ %>
	{
		name: '<%=m.get(j).getName()%>',
		c : [<%for (int k =0; k < c.get(j).size(); k++){%>
		     	"<%=c.get(j).get(k).getName()%>",
		     <%}%>
		     ]
		
	},
	<%}}%>

];

function findMajor(name) {
  var i = 0,
    len = majors.length;
  for (i; i < len; i += 1) {
    if (majors[i].name === name) {
      return majors[i];
    }
  }
  return null;
};

function addConcentration(value, text) {
  if (value !== '') {

    $("#concentration").append('<option value="' + value + '">' + text + '</option>');
  }
}

function addDoubleConcentration(value, text) {
  if (value !== '') {

    $("#doubleConcentration").append('<option value="' + value + '">' + text + '</option>');
  }
}

function addThirdConcentration(value, text) {
  if (value !== '') {

    $("#thirdConcentration").append('<option value="' + value + '">' + text + '</option>');
  }
}

var dropdown = [],
  i = 0,
  len = majors.length;
for (i; i < len; i += 1) {
  dropdown.push(majors[i].name);
}

$.each(dropdown, function(i, val) {
  $("#major").append("<option value=\"" + val + "\">" + val + "</option>");
});

$.each(dropdown, function(i, val) {
  $("#doubleMajor").append("<option value=\"" + val + "\">" + val + "</option>");
});

$.each(dropdown, function(i, val) {
  $("#thirdMajor").append("<option value=\"" + val + "\">" + val + "</option>");
});

<%
if (u.getRole() < 3 ){
%>

<%
	if (u.getMajor() != null && u.getMajor().size() > 0 && u.getMajor().get(0) != null){
%>

	$('[name=major]').val('<%=u.getMajor().get(0).getName()%>');
	$(function () {
	    $("select#major").load();
	    <%
		if (u.getConcentration() != null && !u.getConcentration().equals(null)){
		
			for (Major con: u.getConcentration()){
				if (con.getParent().getName().equals(u.getMajorAtIndex(0).getName())){
		%>
		$('[name=concentration]').val('<%=con.getName()%>'); 
		<%
		}}}
		%>
	});
	
<%
	}
%>

<%
	if (u.getMajor() != null && u.getMajor().size() > 1 && u.getMajor().get(1) != null){
%>
			$('[name=doubleMajor]').val('<%=u.getMajor().get(1).getName()%>');
			$(function () {
			    $("select#doubleMajor").load();
			    <%
				if (u.getConcentration() != null && !u.getConcentration().equals(null)){
				
					for (Major con: u.getConcentration()){
						if (con.getParent().getName().equals(u.getMajorAtIndex(1).getName())){
				%>
				$('[name=doubleConcentration]').val('<%=con.getName()%>'); 
				<%
				}}}
				%>
			});

<%
}
%>

<%
if (u.getMajor() != null && u.getMajor().size() > 2 && u.getMajor().get(2) != null){
%>
		$('[name=thirdMajor]').val('<%=u.getMajor().get(2).getName()%>');
		$(function () {
		    $("select#thirdMajor").load();
		    <%
			if (u.getConcentration() != null && !u.getConcentration().equals(null)){
			
				for (Major con: u.getConcentration()){
					if (con.getParent().getName().equals(u.getMajorAtIndex(2).getName())){
			%>
			$('[name=thirdConcentration]').val('<%=con.getName()%>'); 
			<%
			}}}
			%>
		});

<%
}
%>

<%
}else {
%>

$('#majorOne').hide();
$('#majorTwo').hide();
$('#majorThree').hide();
$('#minors').hide();

<%
}
%>



$('#major').on('load', function() {
	  var optionSelected = $("option:selected", this);
	  var valueSelected = this.value;
	  if (valueSelected !== 'Select') {
	    $('#concentration').children().slice(1).remove();
	    var selectedMajor = findMajor(valueSelected);
	    var i = 0,
	      len = selectedMajor.c.length;
	    $('#majorTwo').show();
	    if (len > 0) {
	      for (i; i < len; i += 1) {
	        addConcentration(selectedMajor.c[i], selectedMajor.c[i]);
	      }
	      $('#concentration').show();
	      $('#concentrationLabel').show();
	      
	    } else {
	      $('#concentration').hide();
	      $('#concentrationLabel').hide();
	    }
	  } else {
	    $('#concentration').hide();
	    $('#concentrationLabel').hide();
	    $('#majorTwo').hide();
	    $('#majorThree').hide();
	  }
	});
	
$('#major').on('load', function() {
	  var optionSelected = $("option:selected", this);
	  var valueSelected = this.value;
	  if (valueSelected !== 'Select') {
	    $('#concentration').children().slice(1).remove();
	    var selectedMajor = findMajor(valueSelected);
	    var i = 0,
	      len = selectedMajor.c.length;
	    $('#majorTwo').show();
	    if (len > 0) {
	      for (i; i < len; i += 1) {
	        addConcentration(selectedMajor.c[i], selectedMajor.c[i]);
	      }
	      $('#concentration').show();
	      $('#concentrationLabel').show();
	      
	    } else {
	      $('#concentration').hide();
	      $('#concentrationLabel').hide();
	    }
	  } else {
	    $('#concentration').hide();
	    $('#concentrationLabel').hide();
	    $('#majorTwo').hide();
	    $('#majorThree').hide();
	  }
	});

$('#major').on('change', function() {
  var optionSelected = $("option:selected", this);
  var valueSelected = this.value;
  if (valueSelected !== 'Select') {
    $('#concentration').children().slice(1).remove();
    var selectedMajor = findMajor(valueSelected);
    var i = 0,
      len = selectedMajor.c.length;
    $('#majorTwo').fadeIn();
    if (len > 0) {
      for (i; i < len; i += 1) {
        addConcentration(selectedMajor.c[i], selectedMajor.c[i]);
      }
      $('#concentration').fadeIn();
      $('#concentrationLabel').fadeIn();
      
    } else {
      $('#concentration').fadeOut();
      $('#concentrationLabel').fadeOut();
    }
  } else {
    $('#concentration').fadeOut();
    $('#concentrationLabel').fadeOut();
    $('#majorTwo').fadeOut();
    $('#majorThree').fadeOut();
  }
});

$('#doubleMajor').on('load', function() {
	  var optionSelected = $("option:selected", this);
	  var valueSelected = this.value;
	  if (valueSelected !== 'Select') {
	    $('#doubleConcentration').children().slice(1).remove();
	    var selectedMajor = findMajor(valueSelected);
	    var i = 0,
	      len = selectedMajor.c.length;
	    $('#majorThree').show();
	    if (len > 0) {
	      for (i; i < len; i += 1) {
	        addDoubleConcentration(selectedMajor.c[i], selectedMajor.c[i]);
	      }
	      $('#doubleConcentration').show();
	      $('#doubleConcentrationLabel').show();
	     
	    } else {
	      $('#doubleConcentration').hide();
	      $('#doubleConcentrationLabel').hide();
	    }
	  } else {
	    $('#doubleConcentration').hide();
	    $('#doubleConcentrationLabel').hide();
	    $('#majorThree').hide();
	  }
	});

$('#doubleMajor').on('change', function() {
  var optionSelected = $("option:selected", this);
  var valueSelected = this.value;
  if (valueSelected !== 'Select') {
    $('#doubleConcentration').children().slice(1).remove();
    var selectedMajor = findMajor(valueSelected);
    var i = 0,
      len = selectedMajor.c.length;
    $('#majorThree').fadeIn();
    if (len > 0) {
      for (i; i < len; i += 1) {
        addDoubleConcentration(selectedMajor.c[i], selectedMajor.c[i]);
      }
      $('#doubleConcentration').fadeIn();
      $('#doubleConcentrationLabel').fadeIn();
     
    } else {
      $('#doubleConcentration').fadeOut();
      $('#doubleConcentrationLabel').fadeOut();
    }
  } else {
    $('#doubleConcentration').fadeOut();
    $('#doubleConcentrationLabel').fadeOut();
    $('#majorThree').fadeOut();
  }
});

$('#thirdMajor').on('change', function() {
  var optionSelected = $("option:selected", this);
  var valueSelected = this.value;
  if (valueSelected !== 'Select') {
    $('#thirdConcentration').children().slice(1).remove();
    var selectedMajor = findMajor(valueSelected);
    var i = 0,
      len = selectedMajor.c.length;
    if (len > 0) {
      for (i; i < len; i += 1) {
        addThirdConcentration(selectedMajor.c[i], selectedMajor.c[i]);
      }
      $('#thirdConcentration').fadeIn();
      $('#thirdConcentrationLabel').fadeIn();
      
    } else {
      $('#thirdConcentration').fadeOut();
      $('#thirdConcentrationLabel').fadeOut();
    }
  } else {
    $('#thirdConcentration').fadeOut();
    $('#thirdConcentrationLabel').fadeOut();
  }
});

$('#thirdMajor').on('load', function() {
	  var optionSelected = $("option:selected", this);
	  var valueSelected = this.value;
	  if (valueSelected !== 'Select') {
	    $('#thirdConcentration').children().slice(1).remove();
	    var selectedMajor = findMajor(valueSelected);
	    var i = 0,
	      len = selectedMajor.c.length;
	    if (len > 0) {
	      for (i; i < len; i += 1) {
	        addThirdConcentration(selectedMajor.c[i], selectedMajor.c[i]);
	      }
	      $('#thirdConcentration').show();
	      $('#thirdConcentrationLabel').show();
	      
	    } else {
	      $('#thirdConcentration').hide();
	      $('#thirdConcentrationLabel').hide();
	    }
	  } else {
	    $('#thirdConcentration').hide();
	    $('#thirdConcentrationLabel').hide();
	  }
	});

});
</script>
</head>
<body>
	<jsp:include page="navBarTemplate.jsp" />
	<div class="container">

		<div class="row">

			<div class="col-xs-12 col-sm-10 col-sm-offset-1 well">
				<div class="panel panel-success">

					<div class="panel-body">
						<h3 class="text-center text-danger">Edit Account</h3>
						<h4 class="text-center"><%=u.getEmail()%></h4>

						<form action="/Alumni-Tracker/edit/<%=u.getId()%>" method="post"
							enctype="multipart/form-data">

							<%
								if (errors != null && errors.get("title") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("title")%></p>
							</div>
							<%
								}
							%>

							<%
								if (errors != null && errors.get("fName") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("fName")%></p>
							</div>
							<%
								}
							%>

							<%
								if (errors != null && errors.get("lName") != null) {
							%>
							<div class="col-xs-12 ">
								<p class="alert alert-danger text-center"><%=errors.get("lName")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12 col-sm-2">
								<div class="form-group">
									<label class="control-label">Title: </label> <select
										class="form-control" name="title" id="title">
										<option>Select</option>
										<%
											if (t != null && t.size() != 0) {
												for (Title e : t) {
										%>
										<option
											<%if (title != null && !title.equals(null) && title.getName().equals(e.getName())) {%>
											selected <%}%>><%=e.getName()%></option>
										<%
											}
											}
										%>
									</select>
								</div>
							</div>

							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
									<label class="control-label">First Name:</label> <input
										type="text" class="form-control" name="fName" id="fName"
										placeholder="Enter First Name"
										<%if (u.getFirstName() != null) {%>
										value="<%=u.getFirstName()%>" <%}%> required />
								</div>
							</div>


							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
									<label class="control-label">Last Name:</label> <input
										type="text" class="form-control" name="lName" id="lName"
										placeholder="Enter Last Name"
										<%if (u.getLastName() != null) {%>
										value="<%=u.getLastName()%>" <%}%> />
								</div>
							</div>

							<%
								if (errors != null && errors.get("suffix") != null) {
							%>
							<div class="col-xs-12 col-sm-2">
								<p class="alert alert-danger text-center"><%=errors.get("suffix")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12 col-sm-2">
								<div class="form-group">
									<label class="control-label">Suffix:</label> <input type="text"
										class="form-control" name="suffix" id="suffix"
										placeholder="Enter Suffix" <%if (u.getSuffix() != null) {%>
										value="<%=u.getSuffix()%>" <%}%> />
								</div>
							</div>

							<%
								if (errors != null && errors.get("personalEmail") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("personalEmail")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12">
								<div class="form-group">
									<label class="control-label">Personal Email:</label> <input
										type="email" class="form-control" name="personalEmail"
										id="personalEmail" placeholder="example@web.com"
										<%if (u.getPersonalEmail() != null) {%>
										value="<%=u.getPersonalEmail()%>" <%}%> />
								</div>
							</div>

							<%
								if (u.getRole() == 4) {
							%>
							<!-- DOES THIS WORK?? -->
							<div visibility="hidden">

								<%
									}
								%>
								<%
									if (errors != null && errors.get("graduationYear") != null) {
								%>
								<div class="col-xs-12">
									<p class="alert alert-danger text-center"><%=errors.get("graduationYear")%></p>
								</div>
								<%
									}
								%>
								<div class="row" id="majorOne">
								<div class="ccol-xs-12 col-sm-2">
									<div class="form-group">
										<label class="control-label">Graduation Year:<%
											if (u.getRole() == 1 || currUser.getRole() == 4) {
										%>
										</label>
										<jsp:useBean id="today" class="java.util.Date" />
										<fmt:formatDate pattern="yyyy" value="${today}"
											var="current_year" />
										<select class="form-control" name="graduationYear"
											id="graduationYear">
											<option>Select</option>
											<%
												for (int r = 1960; r <= 2025; r++) {
											%>
											<option
												<%if (u.getGraduationYear() != 0 && u.getGraduationYear() == r) {%>
												selected <%}%>><%=r%></option>
											<%
												}
											%>
										</select>
										<%
											} else if (u.getRole() == 2) {
										%>
										<div class="">
											<input type="hidden" name="graduationYear"
												id="graduationYear">
											<%
												if (u.getGraduationYear() != 0) {
											%>
											<h3><%=u.getGraduationYear()%></h3>
											<%
												}
											%>
										</div>
										<%
											}
										%>
									</div>
								</div>

								<%
									if (errors != null && errors.get("major") != null) {
								%>
								<div class="col-xs-12">
									<p class="alert alert-danger text-center"><%=errors.get("major")%></p>
								</div>
								<%
									}
								%>

								<div class="col-xs-12 col-sm-4">
									<div class="form-group">
										<label class="control-label" >Major:</label><%
											if (u.getRole() == 1 || currUser.getRole() == 4) {
										%>
										<select class="form-control" name="major" id="major">
											<%-- <option>Select</option>
											<%
												if (m != null && m.size() != 0) {
														for (Major major : m) {
											%>
											<option
												<%if (u.getMajor() != null && u.getMajor().size() > 0 && u.getMajor().get(0) != null
								&& u.getMajor().get(0).getName().equals(major.getName())) {%>
												selected <%}%>><%=major.getName()%></option>
											<%
												}
													}
											%>
										</select> --%>
										
      											<option value="Select">Select</option>
  										  </select>	
										<%
											} else if (u.getRole() == 2) {
										%>
										<div class="">
											<input type="hidden" name="major" id="major">
											<%
												if (u.getRole() == 2 && u.getMajor().size() > 0 && u.getMajor().get(0) != null) {
											%>
											<h3><%=u.getMajor().get(0).getName()%></h3>
											<%
												}
											%>
										</div>
										<%
											}
										%>
									</div>

								</div>
								
								<div class="col-xs-12 col-sm-6">								
									<div class="form-group" id="concentrationGroup">
										<label class="control-label" id="concentrationLabel" style="display:none;">Concentration:</label>
								    <select class="form-control" name="concentration" id="concentration" style="display:none;">
								      <option value="Select">Select</option>
								    </select>
									</div>
								</div>

								</div>
								<%
									if (errors != null && errors.get("doubleMajor") != null) {
								%>
								<div class="col-xs-12">
									<p class="alert alert-danger text-center"><%=errors.get("doubleMajor")%></p>
								</div>
								<%
									}
								%>

								<%--CHANGE VISIBILITY FOR USER WITH MAJOR ALREADY --%>
								<div class="row" id="majorTwo" style="display:none;">
								<div class="col-xs-12 col-sm-6">
									<div class="form-group">
										<label class="control-label">Double Major:</label><%
											if (u.getRole() == 1 || currUser.getRole() == 4) {
										%>
										 <select class="form-control doubleMajor" name="doubleMajor"
											id="doubleMajor">
											<%-- <option>Select</option>
											<%
												if (m != null && m.size() != 0) {
														for (Major major : m) {
											%>
											<option
												<%if (u.getMajor() != null && u.getMajor().size() > 1 && u.getMajor().get(1) != null
								&& u.getMajor().get(1).getName().equals(major.getName())) {%>
												selected <%}%>><%=major.getName()%></option>
											<%
												}
													}
											%>
										</select>--%>
										
											<option value="Select">Select</option>
  										</select>	
										<%
											} else if (u.getRole() == 2) {
										%>
										<div class="">
											<input type="hidden" name="doubleMajor" id="doubleMajor">
											<%
												if (u.getRole() == 2 && u.getMajor().size() > 1 && u.getMajor().get(1) != null) {
											%>
											<h3><%=u.getMajor().get(1).getName()%></h3>
											<%
												}
											%>
										</div>
										<%
											}
										%>
									</div>

								</div>
								
								<div class="col-xs-12 col-sm-6">								
									<div class="form-group">
										<label class="control-label" id="doubleConcentrationLabel" style="display:none;">Concentration:</label>
								    <select class="form-control" name="doubleConcentration" id="doubleConcentration" style="display:none;">
								      <option value="Select">Select</option>
								    </select>
									</div>
								</div>
								</div>

								<%
									if (errors != null && errors.get("thirdMajor") != null) {
								%>
								<div class="col-xs-12">
									<p class="alert alert-danger text-center"><%=errors.get("thirdMajor")%></p>
								</div>
								<%
									}
								%>

								<div class="row" id="majorThree" style="display:none;">
								<div class="col-xs-12 col-sm-6">
									<div class="form-group">
										<label class="select-label">Third Major:</label><%
											if (u.getRole() == 1 || currUser.getRole() == 4) {
										%>
										 <select class="form-control" name="thirdMajor"
											id="thirdMajor">
											<%--<option>Select</option>
											<%
												if (m != null && m.size() != 0) {
														for (Major major : m) {
											%>
											<option
												<%if (u.getMajor() != null && u.getMajor().size() > 2 && u.getMajor().get(2) != null
								&& u.getMajor().get(2).getName().equals(major.getName())) {%>
												selected <%}%>><%=major.getName()%></option>
											<%
												}
													}
											%>
										</select> --%>
										
											<option value="Select">Select</option>
  										</select>
										
										<%
											} else if (u.getRole() == 2) {
										%>
										<div class="">
											<input type="hidden" name="thirdMajor" id="thirdMajor">
											<%
												if (u.getRole() == 2 && u.getMajor().size() > 2 && u.getMajor().get(2) != null) {
											%>
											<h3><%=u.getMajor().get(2).getName()%></h3>
											<%
												}
											%>
										</div>
										<%
											}
										%>
									</div>
								</div>
								
								<div class="col-xs-12 col-sm-6">								
									<div class="form-group">
										<label class="control-label" id="thirdConcentrationLabel" style="display:none;">Concentration:</label>
								    <select class="form-control" name="thirdConcentration" id="thirdConcentration" style="display:none;">
								      <option value="Select">Select</option>
								    </select>
									</div>
								</div>
								</div>


							<div class="row" id="minors">
								<%
									if (errors != null && errors.get("minor") != null) {
								%>
								<div class="col-xs-12">
									<p class="alert alert-danger text-center"><%=errors.get("minor")%></p>
								</div>
								<%
									}
								%>

								<div class="col-xs-12 col-sm-4">
									<div class="form-group">
										<label class="control-label">Minor:<%
											if (u.getRole() == 1 || currUser.getRole() == 4) {
										%> 
										</label> <select class="form-control" name="minor" id="minor">
											<option>Select</option>
											<%
												if (mi != null && mi.size() != 0) {
														for (Major minor : mi) {
											%>
											<option
												<%if (u.getMinor() != null && u.getMinor().size() > 0 && u.getMinor().get(0) != null
								&& u.getMinor().get(0).getName().equals(minor.getName())) {%>
												selected <%}%>><%=minor.getName()%></option>
											<%
												}
													}
											%>
										</select>
										<%
											} else if (u.getRole() == 2) {
										%>
										<div class="">
											<input type="hidden" name="minor" id="minor">
											<%
												if (u.getRole() == 2 && u.getMinor().size() > 0 && u.getMinor().get(0) != null) {
											%>
											<h3><%=u.getMinor().get(0).getName()%></h3>
											<%
												}
											%>
										</div>
										<%
											}
										%>
									</div>

								</div>

								<%
									if (errors != null && errors.get("secondMinor") != null) {
								%>
								<div class="col-xs-12">
									<p class="alert alert-danger text-center"><%=errors.get("secondMinor")%></p>
								</div>
								<%
									}
								%>

								<div class="col-xs-12 col-sm-4">
									<div class="form-group">
										<label class="control-label">Double Minor:<%
											if (u.getRole() == 1 || currUser.getRole() == 4) {
										%> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
										</label> <select class="form-control" name="secondMinor"
											id="secondMinor">
											<option>Select</option>
											<%
												if (mi != null && mi.size() != 0) {
														for (Major minor : mi) {
											%>
											<option
												<%if (u.getMinor() != null && u.getMinor().size() > 1 && u.getMinor().get(1) != null
								&& u.getMinor().get(1).getName().equals(minor.getName())) {%>
												selected <%}%>><%=minor.getName()%></option>
											<%
												}
													}
											%>
										</select>
										<%
											} else if (u.getRole() == 2) {
										%>
										<div class="">
											<input type="hidden" name="secondMinor" id="secondMinor">
											<%
												if (u.getRole() == 2 && u.getMinor().size() > 1 && u.getMinor().get(1) != null) {
											%>
											<h3><%=u.getMinor().get(1).getName()%></h3>
											<%
												}
											%>
										</div>
										<%
											}
										%>
									</div>

								</div>

								<%
									if (errors != null && errors.get("thirdMinor") != null) {
								%>
								<div class="col-xs-12">
									<p class="alert alert-danger text-center"><%=errors.get("thirdMinor")%></p>
								</div>
								<%
									}
								%>

								<div class="col-xs-12 col-sm-4">
									<div class="form-group">
										<label class="select-label">Third Minor:<%
											if (u.getRole() == 1 || currUser.getRole() == 4) {
										%> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
										</label> <select class="form-control" name="thirdMinor"
											id="thirdMinor">
											<option>Select</option>
											<%
												if (mi != null && mi.size() != 0) {
														for (Major minor : mi) {
											%>
											<option
												<%if (u.getMinor() != null && u.getMinor().size() > 2 && u.getMinor().get(2) != null
								&& u.getMinor().get(2).getName().equals(minor.getName())) {%>
												selected <%}%>><%=minor.getName()%></option>
											<%
												}
													}
											%>
										</select>
										<%
											} else if (u.getRole() == 2) {
										%>
										<div class="">
											<input type="hidden" name="thirdMinor" id="thirdMinor">
											<%
												if (u.getRole() == 2 && u.getMinor().size() > 2 && u.getMinor().get(2) != null) {
											%>
											<h3><%=u.getMinor().get(2).getName()%></h3>
											<%
												}
											%>
										</div>
										<%
											}
										%>
									</div>


								</div>
								</div>



								<%
									if (u.getRole() == 4) {
								%>
							</div>
							<%
								}
							%>



							<%
								if (errors != null && errors.get("occupation") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("occupation")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12">
								<div class="form-group">
									<label class="control-label">Occupation:</label> <input
										type="text" class="form-control" name="occupation"
										id="occupation" placeholder="Enter Occupation"
										<%if (u.getOccupation() != null && !u.getOccupation().equals("") ) {%>
										value="<%=u.getOccupation()%>" <%}%> />
								</div>
							</div>
							
							<%
								if (errors != null && errors.get("company") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("company")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12">
								<div class="form-group">
									<label class="control-label">Company:</label> <input
										type="text" class="form-control" name="company"
										id="company" placeholder="Enter Company"
										<%if (u.getCompany() != null && !u.getCompany().equals("") ) {%>
										value="<%=u.getCompany()%>" <%}%> />
								</div>
							</div>

							<div class="text-center">
								<div class="col-sm-6" align="center">
									<%--<img src="/content/img/empty-profile.png"
										class="avatar img-circle img-thumbnail" alt="profilePic">
									 <input type="file" name="profile" id="" value=""> <br>--%>
								</div>
							</div>
							<br>

							<%
								if (errors != null && errors.get("biography") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("biography")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-6">
								<div class="form-group">
									<label class="control-label">Biography:</label>
									<textarea type="text" class="form-control" name="biography"
										id="biography" placeholder="Enter Biography" rows="5"><%
											if (u.getBiography() != null && !u.getBiography().equals("") ) {
										%><%=u.getBiography()%><%
											}
										%></textarea>
								</div>
							</div>


							<div class="text-center">
								<div class="col-sm-6" align="center">

									<%--
								<%
									if(request.getAttribute("photo") == null){
										//if(photo.getData() == null){
								%>
       							 <img src="/Alumni-Tracker/content/img/empty-profile.png" class="avatar img-circle img-thumbnail" alt="profilePic">
       							<%}else{%>
       								<img src="/getImage/<%=photo.getId()%>.jpeg" class="avatar img-circle img-thumbnail" alt="profilePic">
       							<%        							
       									}
									//}
       							%>
									<input type="file" accept="image/jpeg" name="profile" id="" value="" >
									<br>
									</div>
     						 </div>
     						 <br>
--%>
									<%
										if (request.getAttribute("photo") == null) {
											//if(photo.getData() == null){
									%>
									<img src="/Alumni-Tracker/content/img/empty-profile.png"
										class="avatar img-circle img-thumbnail" alt="profilePic">
									<%
										} else {
									%>
									<img
										src="/Alumni-Tracker/getImage/<%=photo.getProfile().getId()%>.do"
										width="128" height="128"
										class="avatar img-circle img-thumbnail" alt="profilePic">
									<%
										}
										//}
									%>
									<input type="file" accept="image/*" name="profile" id=""
										value=""> <br>
								</div>
							</div>
							<br>


							<%
								if (errors != null && errors.get("interests") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("interests")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-6">
								<div class="form-group">
									<label class="control-label">Areas of Interest: </label> <select
										multiple class="form-control" name="interests" id="interests"
										size="5">
										<%
											if (!i.equals(null)) {
												for (Interest interest : uI) {
										%>
										<option value="<%=interest.getName()%>" selected><%=interest.getName()%></option>
										<%
											}
											}
										%>
										<%
											if (!i.equals(null)) {
												for (Interest interest : i) {
										%>
										<option value="<%=interest.getName()%>"><%=interest.getName()%></option>
										<%
											}
											}
										%>
									</select> <small><b>Note:</b> Hold "ctrl" to select multiple</small>
								</div>
							</div>
							<br>

							<%
								if (errors != null && errors.get("experience") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("experience")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-6">
								<div class="form-group">
									<label class="control-label">Experience:</label>
									<textarea type="text" class="form-control" name="experience"
										id="experience" placeholder="Enter Experience" rows="5"><%
											if (u.getExperience() != null && !u.getExperience().equals("")) {
										%><%=u.getExperience()%><%
											}
										%></textarea>
								</div>
							</div>
							
							<div class="col-xs-12">
								<div class="form-group">
									<input type="checkbox" id="display" name="display" value="bool"
									<% if (u.getToPublic() == 1) { %>checked<%} %>
									> Allow my information to be displayed in the directory!
								</div>
							</div>
							<%
								if (errors != null && errors.get("password") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("password")%></p>
							</div>
							<%
								}
							%>
							<br>
							<div class="col-xs-12 col-sm-6">
								<div class="form-group">
									<label class="control-label">Password:</label> <input
										type="password" class="form-control" name="password"
										id="password" placeholder="Enter Password" />
								</div>
								<small><b>Note:</b> Password is not required for profile
									changes</small>
							</div>
							<div class="col-xs-12 col-sm-6">
								<div class="form-group">
									<label class="control-label">Confirm Password:</label><input
										type="password" class="form-control" name="confirmPassword"
										id="confirmPassword" placeholder="Confirm Password" />
								</div>
							</div>

							<br>
							<div class="col-sm-6" align="left">
								<label>Upload Resume:</label> <input type="file"
									accept=".docx, .pdf" name="resume" id="" value=""> <br>
							</div>
							<!--  <button class="btn btn-primary" name="Upload" value="Upload"
								type="Submit">Upload</button>-->
							<div class="col-xs-6"></div>
							<div class="col-xs-3">
								<button type="reset" onclick="location.href='/Alumni-Tracker/user/<%=u.getId()%>'"
									class="btn btn btn-danger btn-lg btn-block">
									<b>Cancel</b>
								</button>
							</div>	
							
							<div class="col-xs-3">
								<button type="submit"
									class="btn btn btn-success btn-lg btn-block">
									<b>Submit Changes</b>
								</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>

	</div>





	<jsp:include page="footerTemplate.jsp" />
</body>
</html>