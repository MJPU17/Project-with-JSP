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
	<div class="card bg-success card-custom mx-auto text-white">
		<div class="card-body">
			<h1>UPDATE PATIENT</h1>
			<form action="patientcontrollerservlet" method="post" class="row">
				<div class="col-md-6 my-auto">
				<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Document</span> <select
							class="form-select " id="antdocument" name="antdocument">
							<option>Select patient to update</option>
							<%
							PatientDAO pat=new PatientDAO();
							for (long aux:pat.getListPatients().keySet()) {
							%>
							<option value="<%=aux%>"><%=aux%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="input-group flex-nowrap input-jp ">
						<span class="input-group-text">Name</span> <input type="text"
							class="form-control" placeholder="Name" aria-label="Name"
							aria-describedby="addon-wrapping" id="name" name="name">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Birthday</span> <input type="date"
							class="form-control" id="fecha" name="fecha">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Document</span> <input
							type="number" class="form-control" placeholder="Document"
							aria-label="Document" aria-describedby="addon-wrapping"
							id="document" name="document">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Nacionality</span> <select
							class="form-select " id="country" name="country">
							<option>Selecciona un pa�s</option>
							<%
							String[] arr = { "Afganist�n", "Albania", "Alemania", "Andorra", "Angola", "Antigua y Barbuda", "Arabia Saudita",
									"Argelia", "Argentina", "Armenia", "Australia", "Austria", "Azerbaiy�n", "Bahamas", "Banglad�s", "Barbados",
									"Bar�in", "B�lgica", "Belice", "Ben�n", "Bielorrusia", "Birmania", "Bolivia", "Bosnia y Herzegovina",
									"Botsuana", "Brasil", "Brun�i", "Bulgaria", "Burkina Faso", "Burundi", "But�n", "Cabo Verde", "Camboya",
									"Camer�n", "Canad�", "Catar", "Chad", "Chile", "China", "Chipre", "Ciudad del Vaticano", "Colombia", "Comoras",
									"Corea del Norte", "Corea del Sur", "Costa de Marfil", "Costa Rica", "Croacia", "Cuba", "Dinamarca", "Dominica",
									"Ecuador", "Egipto", "El Salvador", "Emiratos �rabes Unidos", "Eritrea", "Eslovaquia", "Eslovenia", "Espa�a",
									"Estados Unidos", "Estonia", "Etiop�a", "Filipinas", "Finlandia", "Fiyi", "Francia", "Gab�n", "Gambia",
									"Georgia", "Ghana", "Granada", "Grecia", "Guatemala", "Guinea", "Guinea-Bis�u", "Guyana", "Hait�", "Honduras",
									"Hungr�a", "India", "Indonesia", "Irak", "Ir�n", "Irlanda", "Islandia", "Islas Marshall", "Islas Salom�n",
									"Israel", "Italia", "Jamaica", "Jap�n", "Jordania", "Kazajist�n", "Kenia", "Kirguist�n", "Kiribati", "Kuwait",
									"Laos", "Lesoto", "Letonia", "L�bano", "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo",
									"Macedonia del Norte", "Madagascar", "Malasia", "Malaui", "Maldivas", "Mal�", "Malta", "Marruecos", "Mauricio",
									"Mauritania", "M�xico", "Micronesia", "Moldavia", "M�naco", "Mongolia", "Montenegro", "Mozambique", "Namibia",
									"Nauru", "Nepal", "Nicaragua", "N�ger", "Nigeria", "Noruega", "Nueva Zelanda", "Om�n", "Pa�ses Bajos",
									"Pakist�n", "Palaos", "Panam�", "Pap�a Nueva Guinea", "Paraguay", "Per�", "Polonia", "Portugal", "Reino Unido",
									"Rep�blica Centroafricana", "Rep�blica Checa", "Rep�blica del Congo", "Rep�blica Democr�tica del Congo",
									"Rep�blica Dominicana", "Ruanda", "Rumania", "Rusia", "Samoa", "San Crist�bal y Nieves", "San Marino",
									"San Vicente y las Granadinas", "Santa Luc�a", "Santo Tom� y Pr�ncipe", "Senegal", "Serbia", "Seychelles",
									"Sierra Leona", "Singapur", "Siria", "Somalia", "Sri Lanka", "Suazilandia", "Sud�frica", "Sud�n",
									"Sud�n del Sur", "Suecia", "Suiza", "Surinam", "Tailandia", "Tanzania", "Tayikist�n", "Timor Oriental", "Togo",
									"Tonga", "Trinidad y Tobago", "T�nez", "Turkmenist�n", "Turqu�a", "Tuvalu", "Ucrania", "Uganda", "Uruguay",
									"Uzbekist�n", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Yibuti", "Zambia", "Zimbabue" };
							for (String pais : arr) {
							%>
							<option value="<%=pais%>"><%=pais%></option>
							<%
							}
							%>
						</select>
					</div>
				</div>
				<div class="col-md-6 my-auto">
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Congested places</span> <input
							type="text" class="form-control"
							placeholder="Place 1, Place 2, Place 3...."
							aria-label="Congested Places" aria-describedby="addon-wrapping"
							id="cplaces" name="cplaces">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Disease name</span> <input
							type="text" class="form-control" placeholder="Disease name"
							aria-label="Disease name" aria-describedby="addon-wrapping"
							id="disname" name="disname">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Disease cause</span> <input
							type="text" class="form-control" placeholder="Cause"
							aria-label="Cause" aria-describedby="addon-wrapping" id="cause"
							name="cause">
					</div>
					<div class="input-group flex-nowrap input-jp">
						<span class="input-group-text">Disease severity</span> <input
							type="number" class="form-control" placeholder="Severity"
							aria-label="Severity" aria-describedby="addon-wrapping"
							id="severity" name="severity">
					</div>
				</div>
				<input type="hidden" name="method" value="update"> <input
					type="submit" class="btn btn-light button-enter mx-auto"
					value="Update">
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