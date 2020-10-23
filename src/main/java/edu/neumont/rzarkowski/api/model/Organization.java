package edu.neumont.rzarkowski.api.model;

import java.util.Arrays;

public class Organization {
	
	private int id;
	private String name;
	private String[] members;
	
	public Organization() {}

	public Organization(int id, String name, String[] members) {
		this.id = id;
		this.name = name;
		this.members = members;
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

	public String[] getMembers() {
		return members;
	}

	public void setMembers(String[] members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", members=" + Arrays.toString(members) + "]";
	}

}
