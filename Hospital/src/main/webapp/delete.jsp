<%@page import="co.edu.unbosque.model.PatientDTO"%>
<%@page import="co.edu.unbosque.model.persistence.PatientDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link
	href="https://fonts.googleapis.com/css?family=Titillium+Web:300&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="styles/styles.css">
<title>HOSPITAL</title>
</head>
<body>
	<p></p>
	<div class="card bg-success card-custom text-white">
		<div class="card-body">
			<h1>DELETE PATIENT</h1>
			<form action="patientcontrollerservlet" method="post" class="row">
				<div class="input-group flex-nowrap input-jp">
					<span class="input-group-text">Document</span> <select
						class="form-select " id="antdocument" name="antdocument">
						<option>Select patient to delete</option>
						<%
						PatientDAO pat = new PatientDAO();
						for (long aux : pat.getListPatients().keySet()) {
						%>
						<option value="<%=aux%>"><%=aux%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="input-group flex-nowrap input-jp">
					<input type="hidden" name="method" value="delete"> <input
						type="submit" class="btn btn-light button-enter mx-auto"
						value="Delete">
				</div>
			</form>
		</div>
	</div>
	<nav class="main-menu">
		<ul>
			<li><a href="index.jsp"> <i class="fa fa-home fa-2x"></i> <span
					class="nav-text"> Home</span>
			</a></li>
			<li class="has-subnav"><a href="enter.jsp"> <i
					class="fa fa-ambulance fa-2x"></i> <span class="nav-text">
						Enter Patient</span>
			</a></li>
			<li class="has-subnav"><a href="update.jsp"> <i
					class="fa fa-sync fa-2x"></i> <span class="nav-text"> Update
						Patient</span>
			</a></li>
			<li class="has-subnav"><a href="delete.jsp"> <i
					class="fa fa-trash fa-2x"></i> <span class="nav-text"> Delete
						Patient</span>
			</a></li>
			<li class="has-subnav"><a href="show.jsp"> <i
					class="fa fa-table fa-2x"></i> <span class="nav-text"> Show
						Patients</span>
			</a></li>
			<li class="has-subnav"><a href="infection.jsp"> <i
					class="fa fa-virus fa-2x"></i> <span class="nav-text">Enter
						infection</span>
			</a></li>
			<li class="has-subnav"><a href="alpha.jsp"> <i
					class="fa fa-thermometer fa-2x"></i> <span class="nav-text">
						Alpha Patient </span>
			</a></li>
			<li class="has-subnav"><a href="network.jsp"> <i
					class="fa fa-network-wired fa-2x"></i> <span class="nav-text">
						Hospital Network</span>
			</a></li>
		</ul>
	</nav>
</body>
</html>