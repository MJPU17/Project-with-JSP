package co.edu.unbosque.model;

public class DiseasesDTO {
	
	private long document;
	private String name;
	private String cause;
	private int severity;
	
	public DiseasesDTO() {}

	public DiseasesDTO(long document ,String name, String cause, int severity) {
		this.document=document;
		this.name = name;
		this.cause = cause;
		this.severity = severity;
	}
	
	public long getDocument() {
		return document;
	}
	
	public void setDocument(long document) {
		this.document = document;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

}
