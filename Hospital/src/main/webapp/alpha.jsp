<%@page import="com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream"%>
<%@page import="co.edu.unbosque.model.DiseasesDTO"%>
<%@page import="co.edu.unbosque.model.persistence.DiseasesDAO"%>
<%@page import="java.util.Arrays"%>
<%@page import="co.edu.unbosque.util.Vertex"%>
<%@page import="co.edu.unbosque.util.Graph"%>
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
	<%PatientDAO pdao=new PatientDAO();
	PatientDTO alpha=new PatientDTO();
	DiseasesDAO disses=new DiseasesDAO();
	DiseasesDTO dalpha=new DiseasesDTO();
	boolean calculate=false;
	Graph networ=new Graph();
	for(PatientDTO transmitter: pdao.getListPatients().values()) {
		if(networ.getVertex(new Vertex<PatientDTO>(transmitter))==null) {
			networ.addVertex(new Vertex<PatientDTO>(transmitter));
		}
		for(long infected: transmitter.getInfectedByMe()) {
			PatientDTO infe=pdao.getListPatients().getValue(infected);
			if(networ.getVertex(new Vertex<PatientDTO>(infe))==null) {
				networ.addVertex(new Vertex<PatientDTO>(infe));
			}
			networ.addEdge(new Vertex<PatientDTO>(transmitter), new Vertex<PatientDTO>(infe), infected);
		}
	}
	if(networ.root()!=null){
		alpha=(PatientDTO)networ.root().getInfo();
		dalpha=disses.getListDiseases().getValue(alpha.getDocument());
		calculate=true;
	}
	if(!calculate){
		for(PatientDTO pat:pdao.getListPatients().values()){
			float porcentage=(pat.getInfectedByMe().size()/pdao.getListPatients().size())*100;
			if(porcentage>=70){
				alpha=pat;
				dalpha=disses.getListDiseases().getValue(alpha.getDocument());
				calculate=true;
				break;
			}
		}
	}
	%>
	<div class="card bg-success card-custom mx-auto text-white">
		<div class="card-body">
			<h1>ALPHA PATIENT</h1>
			<form action="patientcontrollerservlet" method="post" class="row">
				<div class="col-md-6 my-auto">
					<div class="input-group flex-nowrap input-jp ">
						<span class="input-group-text">Name</span> <input type="text"
							class="form-control" placeholder="Name" aria-label="Name"
							aria-describedby="addon-wrapping" id="name" name="name" value="<%=calculate?alpha.getName():"none"%>" readonly="readonly">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Birthday</span> <input type="text"
							class="form-control" id="fecha" name="fecha"value="<%=calculate?alpha.getBirthDay().toString():"none"%>" readonly="readonly">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Document</span> <input
							type="text" class="form-control" placeholder="Document"
							aria-label="Document" aria-describedby="addon-wrapping"
							id="document" name="document" value="<%=calculate?alpha.getDocument():"none"%>" readonly="readonly">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Nacionality</span> <input
							type="text" class="form-control" placeholder="Nacionality"
							aria-label="Document" aria-describedby="addon-wrapping"
							id="nacionality" name="nacionality"value="<%=calculate?alpha.getNacionality():"none"%>" readonly="readonly">
					</div>
				</div>
				<div class="col-md-6">
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Congested places</span> <input
							type="text" class="form-control"
							placeholder="Place 1, Place 2, Place 3...."
							aria-label="Congested Places" aria-describedby="addon-wrapping"
							id="cplaces" name="cplaces" value="<%=calculate?Arrays.toString(alpha.getCongestedPlaces()):"none"%>" readonly="readonly">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Disease name</span> <input
							type="text" class="form-control" placeholder="Disease name"
							aria-label="Disease name" aria-describedby="addon-wrapping"
							id="disname" name="disname" value="<%=calculate?dalpha.getName():"none"%>" readonly="readonly">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Disease cause</span> <input
							type="text" class="form-control" placeholder="Cause"
							aria-label="Cause" aria-describedby="addon-wrapping" id="cause"
							name="cause" value="<%=calculate?dalpha.getCause():"none"%>" readonly="readonly">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Disease severity</span> <input
							type="text" class="form-control" placeholder="Severity"
							aria-label="Severity" aria-describedby="addon-wrapping"
							id="severity" name="severity" value="<%=calculate?dalpha.getSeverity():"none"%>" readonly="readonly">
					</div>
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