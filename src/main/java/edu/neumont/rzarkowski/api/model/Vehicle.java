package edu.neumont.rzarkowski.api.model;

public class Vehicle {
	
	private int id;
	private String name;
	private String transportMethod;
	private String owner;
	
	public Vehicle() {}

	public Vehicle(int id, String name, String transportMethod, String owner) {
		this.id = id;
		this.name = name;
		this.transportMethod = transportMethod;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTransportMethod() {
		return transportMethod;
	}

	public void setTransportMethod(String transportMethod) {
		this.transportMethod = transportMethod;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", name=" + name + ", transportMethod=" + transportMethod + ", owner=" + owner
				+ "]";
	}

}
