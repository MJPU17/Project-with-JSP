package co.edu.unbosque.model.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.PatientDTO;
import co.edu.unbosque.util.MyLinkedList;
import co.edu.unbosque.util.MyMap;

public class PatientDAO implements CRUDoperation<Long, PatientDTO>{
	
	private MyMap<Long, PatientDTO> listPatients;
	private DBConnection dbcon;
	
	public PatientDAO() {
		dbcon=new DBConnection();
		listPatients=loadData();
		if(listPatients==null)listPatients=new MyMap<>();
	}

	public MyMap<Long, PatientDTO> getListPatients() {
		return listPatients;
	}

	public void setListPatients(MyMap<Long, PatientDTO> listPatients) {
		this.listPatients = listPatients;
	}

	public DBConnection getDbcon() {
		return dbcon;
	}

	public void setDbcon(DBConnection dbcon) {
		this.dbcon = dbcon;
	}

	@Override
	public void create(Long key, PatientDTO info) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConnect().prepareStatement("INSERT INTO patient (pname, birthday, document, nacionality, congestedplaces, infectedbyme) VALUES (?,?,?,?,?,?);"));
			dbcon.getPreparedstatement().setString(1, info.getName());
			dbcon.getPreparedstatement().setDate(2,Date.valueOf(info.getBirthDay()));
			dbcon.getPreparedstatement().setLong(3, info.getDocument());
			dbcon.getPreparedstatement().setString(4, info.getNacionality());
			String congestedplaces="";
			for(int i=0;i<info.getCongestedPlaces().length;i++) {
				congestedplaces+=info.getCongestedPlaces()[i];
				if(i!=info.getCongestedPlaces().length-1)congestedplaces+=",";
			}
			dbcon.getPreparedstatement().setString(5, congestedplaces);
			dbcon.getPreparedstatement().setString(6, "");
			dbcon.getPreparedstatement().executeUpdate();
			dbcon.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		listPatients.put(key, info);
	}

	@Override
	public void update(Long key, PatientDTO info) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConnect().prepareStatement("UPDATE patient SET pname=?, birthday=?, document=?, nacionality=?, congestedplaces=? WHERE document=?;"));
			dbcon.getPreparedstatement().setString(1, info.getName());
			dbcon.getPreparedstatement().setDate(2,Date.valueOf(info.getBirthDay()));
			dbcon.getPreparedstatement().setLong(3, info.getDocument());
			dbcon.getPreparedstatement().setString(4, info.getNacionality());
			String congestedplaces="";
			for(int i=0;i<info.getCongestedPlaces().length;i++) {
				congestedplaces+=info.getCongestedPlaces()[i];
				if(i!=info.getCongestedPlaces().length-1)congestedplaces+=",";
			}
			dbcon.getPreparedstatement().setString(5, congestedplaces);
			dbcon.getPreparedstatement().setLong(6, key);
			dbcon.getPreparedstatement().executeUpdate();
			dbcon.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		updateAllDocument(key, info.getDocument());
		info.setInfectedByMe(listPatients.getValue(key).getInfectedByMe());
		listPatients.remove(key);
		listPatients.put(info.getDocument(), info);
	}

	@Override
	public void delete(Long key) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConnect().prepareStatement("DELETE FROM patient WHERE document=?;"));
			dbcon.getPreparedstatement().setLong(1, key);
			dbcon.getPreparedstatement().executeUpdate();
			dbcon.close();
			deleteAllDocument(key);
			listPatients.remove(key);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addInfected(long transmitter, long infected) {
		listPatients.getValue(transmitter).getInfectedByMe().addLast(infected);
		String infectedbyme="";
		PatientDTO tra=listPatients.getValue(transmitter);
		for(int i=0;i<tra.getInfectedByMe().size();i++) {
			infectedbyme+=tra.getInfectedByMe().get(i).getInfo();
			if(i!=tra.getCongestedPlaces().length-1)infectedbyme+=",";
		}
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConnect().prepareStatement("UPDATE patient SET infectedbyme=? WHERE document=?;"));
			dbcon.getPreparedstatement().setString(1, infectedbyme);
			dbcon.getPreparedstatement().setLong(2, transmitter);
			dbcon.getPreparedstatement().executeUpdate();
			dbcon.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public MyMap<Long,PatientDTO> loadData() {
		MyMap<Long, PatientDTO> data=new MyMap<>();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConnect().createStatement());
			dbcon.setResultset(dbcon.getStatement().executeQuery("SELECT *FROM patient;"));
			while(dbcon.getResultset().next()) {
				String name=dbcon.getResultset().getString("pname");
				LocalDate date=dbcon.getResultset().getDate("birthday").toLocalDate();
				long document=dbcon.getResultset().getLong("document");
				String nacionality=dbcon.getResultset().getString("nacionality");
				String congestedplaces[]=dbcon.getResultset().getString("congestedplaces").split(",");
				String infecteds[]=dbcon.getResultset().getString("infectedbyme").split(",");
				MyLinkedList<Long> infectedByMe=new MyLinkedList<>();
				for(String aux:infecteds) {
					if(!aux.equals(""))infectedByMe.addLast(Long.parseLong(aux));
				}
				PatientDTO patient=new PatientDTO(name, date, document, nacionality, congestedplaces);
				patient.setInfectedByMe(infectedByMe);
				data.put(document, patient);
				
			}
			return data;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateAllDocument(long antdocument, long newdocuent) {
		for(PatientDTO pat: listPatients.values()) {
			for(int i=0;i<pat.getInfectedByMe().size();i++) {
				if(pat.getInfectedByMe().get(i).getInfo()==antdocument) {
					listPatients.getValue(pat.getDocument()).getInfectedByMe().set(i, newdocuent);
					String infectedbyme="";
					for(int j=0;j<pat.getInfectedByMe().size();j++) {
						infectedbyme+=pat.getInfectedByMe().get(j).getInfo();
						if(j!=pat.getCongestedPlaces().length-1)infectedbyme+=",";
					}
					dbcon.initConnection();
					try {
						dbcon.setPreparedstatement(dbcon.getConnect().prepareStatement("UPDATE patient SET infectedbyme=? WHERE document=?;"));
						dbcon.getPreparedstatement().setString(1, infectedbyme);
						dbcon.getPreparedstatement().setLong(2, pat.getDocument());
						dbcon.getPreparedstatement().executeUpdate();
						dbcon.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	public void deleteAllDocument(long antdocument) {
		for(PatientDTO pat: listPatients.values()) {
			for(int i=0;i<pat.getInfectedByMe().size();i++) {
				if(pat.getInfectedByMe().get(i).getInfo()==antdocument) {
					listPatients.getValue(pat.getDocument()).getInfectedByMe().remove(i);
					String infectedbyme="";
					for(int j=0;j<pat.getInfectedByMe().size();j++) {
						infectedbyme+=pat.getInfectedByMe().get(j).getInfo();
						if(j!=pat.getCongestedPlaces().length-1)infectedbyme+=",";
					}
					dbcon.initConnection();
					try {
						dbcon.setPreparedstatement(dbcon.getConnect().prepareStatement("UPDATE patient SET infectedbyme=? WHERE document=?;"));
						dbcon.getPreparedstatement().setString(1, infectedbyme);
						dbcon.getPreparedstatement().setLong(2, pat.getDocument());
						dbcon.getPreparedstatement().executeUpdate();
						dbcon.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
