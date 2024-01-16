package co.edu.unbosque.model;

import java.time.LocalDate;
import java.util.Arrays;

import co.edu.unbosque.util.MyLinkedList;

public class PatientDTO {
	
	private String name;
	private LocalDate birthDay;
	private long document;
	private String nacionality;
	private String[] congestedPlaces;
	private MyLinkedList<Long> infectedByMe;
	
	public PatientDTO() {}

	public PatientDTO(String name, LocalDate birthDay, long document, String nacionality,String[] congestedPlaces) {
		this.name = name;
		this.birthDay = birthDay;
		this.document = document;
		this.nacionality = nacionality;
		this.congestedPlaces = congestedPlaces;
		this.infectedByMe=new MyLinkedList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public long getDocument() {
		return document;
	}

	public void setDocument(long document) {
		this.document = document;
	}

	public String getNacionality() {
		return nacionality;
	}

	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public String[] getCongestedPlaces() {
		return congestedPlaces;
	}

	public void setCongestedPlaces(String[] congestedPlaces) {
		this.congestedPlaces = congestedPlaces;
	}

	public MyLinkedList<Long> getInfectedByMe() {
		return infectedByMe;
	}

	public void setInfectedByMe(MyLinkedList<Long> infectedByMe) {
		this.infectedByMe = infectedByMe;
	}

	@Override
	public String toString() {
		return "PatientDTO [name=" + name + ", birthDay=" + birthDay + ", document=" + document + ", nacionality="
				+ nacionality + ",congestedPlaces="+ Arrays.toString(congestedPlaces) + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PatientDTO))return false;
		PatientDTO patient=(PatientDTO)obj;
		if(document!=patient.document)return false;
		return true;
	}
	
	
}
