package edu.neumont.rzarkowski.api.model;

public class Species {
	
	private int id;
	private String scientificName;
	private String commonName;
	private String bodyType;
	private String homeWorld;
	
	public Species() {}

	public Species(int id, String scientificName, String commonName, String bodyType, String homeWorld) {
		this.id = id;
		this.scientificName = scientificName;
		this.commonName = commonName;
		this.bodyType = bodyType;
		this.homeWorld = homeWorld;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public String getHomeWorld() {
		return homeWorld;
	}

	public void setHomeWorld(String homeWorld) {
		this.homeWorld = homeWorld;
	}

	@Override
	public String toString() {
		return "Species [id=" + id + ", scientificName=" + scientificName + ", commonName=" + commonName + ", bodyType="
				+ bodyType + ", homeWorld=" + homeWorld + "]";
	}

}
