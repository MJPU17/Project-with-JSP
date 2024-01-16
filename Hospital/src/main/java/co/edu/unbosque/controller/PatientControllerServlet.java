package co.edu.unbosque.controller;

import java.io.IOException;
import java.time.LocalDate;

import co.edu.unbosque.model.DiseasesDTO;
import co.edu.unbosque.model.PatientDTO;
import co.edu.unbosque.model.persistence.DiseasesDAO;
import co.edu.unbosque.model.persistence.PatientDAO;
import co.edu.unbosque.util.Graph;
import co.edu.unbosque.util.Vertex;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PatientControllerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5712255457663606062L;
	private PatientDAO pdao;
	private DiseasesDAO ddao;
	private Graph network;
	
	public PatientControllerServlet() {
		pdao=new PatientDAO();
		ddao=new DiseasesDAO();
		network=loadNetWork();
		System.out.println(pdao.getListPatients().toString());
		System.out.println(ddao.getListDiseases().toString());
		System.out.println(network.toString());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method=req.getParameter("method");
		if(method.equals("enter")) {
			String name=req.getParameter("name");
			String date=req.getParameter("fecha");
			String document=req.getParameter("document");
			String country=req.getParameter("country");
			String cplaces=req.getParameter("cplaces");
			String dname=req.getParameter("disname");
			String cause=req.getParameter("cause");
			String severity=req.getParameter("severity");
			if(!name.isEmpty()&&!date.isEmpty()&&!document.isEmpty()&&!country.isEmpty()&&!cplaces.isEmpty()&&!dname.isEmpty()&&!cause.isEmpty()&&!severity.isEmpty()) {
				if(!pdao.getListPatients().containsKey(Long.parseLong(document))) {
					PatientDTO patient=new PatientDTO(name, LocalDate.parse(date), Long.parseLong(document), country, cplaces.split(","));
					DiseasesDTO disease=new DiseasesDTO(Long.parseLong(document), dname, cause, Integer.parseInt(severity));
					pdao.create(patient.getDocument(), patient);
					ddao.create(disease.getDocument(), disease);
					network.addVertex(new Vertex<PatientDTO>(patient));;
					RequestDispatcher rd= req.getRequestDispatcher("enter.jsp");
					rd.forward(req, resp);
				}
			}
			System.out.println(pdao.getListPatients().toString());
			System.out.println(ddao.getListDiseases().toString());
			System.out.println(network.toString());
			
		}
		else if(method.equals("infection")) {
			String transmitter=req.getParameter("transmitter");
			String infected=req.getParameter("infected");
			if(!transmitter.isEmpty()&&!infected.isEmpty()) {
				long tra=Long.parseLong(transmitter);
				long inf=Long.parseLong(infected);
				if(pdao.getListPatients().containsKey(tra)&&pdao.getListPatients().containsKey(inf)) {
					pdao.addInfected(tra, inf);
					PatientDTO tran=pdao.getListPatients().getValue(tra);
					PatientDTO infe=pdao.getListPatients().getValue(inf);
					network.addEdge(new Vertex<PatientDTO>(tran), new Vertex<PatientDTO>(infe), 1);
					RequestDispatcher rd= req.getRequestDispatcher("infection.jsp");
					rd.forward(req, resp);
				}
				System.out.println(network.toString());
			}
		}
		else if(method.equals("update")) {
			String antdocument=req.getParameter("antdocument");
			String name=req.getParameter("name");
			String date=req.getParameter("fecha");
			String document=req.getParameter("document");
			String country=req.getParameter("country");
			String cplaces=req.getParameter("cplaces");
			String dname=req.getParameter("disname");
			String cause=req.getParameter("cause");
			String severity=req.getParameter("severity");
			if(!antdocument.replaceAll("^[0-9]", "").isEmpty()&&!name.isEmpty()&&!date.isEmpty()&&!document.isEmpty()&&!country.isEmpty()&&!cplaces.isEmpty()&&!dname.isEmpty()&&!cause.isEmpty()&&!severity.isEmpty()) {
				if(!pdao.getListPatients().containsKey(Long.parseLong(document))||Long.parseLong(antdocument)==Long.parseLong(document)) {
					PatientDTO patient=new PatientDTO(name, LocalDate.parse(date), Long.parseLong(document), country, cplaces.split(","));
					DiseasesDTO disease=new DiseasesDTO(Long.parseLong(document), dname, cause, Integer.parseInt(severity));
					pdao.update(Long.parseLong(antdocument), patient);
					ddao.update(Long.parseLong(antdocument), disease);
					System.out.println(pdao.getListPatients().toString());
					System.out.println(ddao.getListDiseases().toString());
					System.out.println(network.toString());
					network=loadNetWork();
					RequestDispatcher rd= req.getRequestDispatcher("update.jsp");
					rd.forward(req, resp);
				}
			}
		}
		else if(method.equals("delete")) {
			String antdocument=req.getParameter("antdocument");
			if(!antdocument.replaceAll("^[0-9]", "").isEmpty()) {
				pdao.delete(Long.parseLong(antdocument));
				System.out.println(pdao.getListPatients().toString());
				System.out.println(ddao.getListDiseases().toString());
				System.out.println(network.toString());
				network=loadNetWork();
				RequestDispatcher rd= req.getRequestDispatcher("delete.jsp");
				rd.forward(req, resp);
			}
		}
	}
	
	public Graph loadNetWork() {
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
		return networ;
	}

}
