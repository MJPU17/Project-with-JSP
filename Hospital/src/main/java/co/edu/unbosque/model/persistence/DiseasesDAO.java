package co.edu.unbosque.model.persistence;

import java.sql.SQLException;

import co.edu.unbosque.controller.DBConnection;
import co.edu.unbosque.model.DiseasesDTO;
import co.edu.unbosque.util.MyMap;

public class DiseasesDAO implements CRUDoperation<Long, DiseasesDTO> {
	
	private MyMap<Long, DiseasesDTO> listDiseases;
	private DBConnection dbcon;
	
	public DiseasesDAO() {
		dbcon=new DBConnection();
		listDiseases=loadData();
		if(listDiseases==null)listDiseases=new MyMap<>();
	}
	
	public MyMap<Long, DiseasesDTO> getListDiseases() {
		return listDiseases;
	}
	
	public void setListDiseases(MyMap<Long, DiseasesDTO> listDiseases) {
		this.listDiseases = listDiseases;
	}

	@Override
	public void create(Long key, DiseasesDTO info) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConnect().prepareStatement("INSERT INTO disease (document, dname, cause, severity) VALUES (?,?,?,?);"));
			dbcon.getPreparedstatement().setLong(1, info.getDocument());
			dbcon.getPreparedstatement().setString(2, info.getName());
			dbcon.getPreparedstatement().setString(3, info.getCause());
			dbcon.getPreparedstatement().setInt(4, info.getSeverity());
			dbcon.getPreparedstatement().executeUpdate();
			dbcon.close();
			listDiseases.put(key, info);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Long key, DiseasesDTO info) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConnect().prepareStatement("UPDATE disease SET document=?, dname=?, cause=?, severity=? WHERE document=?;"));
			dbcon.getPreparedstatement().setLong(1, info.getDocument());
			dbcon.getPreparedstatement().setString(2, info.getName());
			dbcon.getPreparedstatement().setString(3, info.getCause());
			dbcon.getPreparedstatement().setInt(4, info.getSeverity());
			dbcon.getPreparedstatement().setLong(5, key);
			dbcon.getPreparedstatement().executeUpdate();
			dbcon.close();
			listDiseases.remove(key);
			listDiseases.put(info.getDocument(), info);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long key) {
		dbcon.initConnection();
		try {
			dbcon.setPreparedstatement(dbcon.getConnect().prepareStatement("DELETE FROM disease WHERE document=?;"));
			dbcon.getPreparedstatement().setLong(1, key);
			dbcon.getPreparedstatement().executeUpdate();
			dbcon.close();
			listDiseases.remove(key);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public MyMap<Long, DiseasesDTO> loadData(){
		MyMap<Long, DiseasesDTO> data=new MyMap<>();
		dbcon.initConnection();
		try {
			dbcon.setStatement(dbcon.getConnect().createStatement());
			dbcon.setResultset(dbcon.getStatement().executeQuery("SELECT *FROM disease;"));
			while(dbcon.getResultset().next()) {
				long document=dbcon.getResultset().getLong("document");
				String name=dbcon.getResultset().getString("dname");
				String cause=dbcon.getResultset().getString("cause");
				int severity=dbcon.getResultset().getInt("severity");
				data.put(document, new DiseasesDTO(document, name, cause, severity));
			}
			return data;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
