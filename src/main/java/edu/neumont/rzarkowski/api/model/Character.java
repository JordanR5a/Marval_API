package edu.neumont.rzarkowski.api.model;

import java.util.Arrays;

public class Character {
	
	private int id;
	private String name;
	private String species;
	private String[] powers;
	
	public Character() {}

	public Character(int id, String name, String[] powers, String species) {
		this.id = id;
		this.name = name;
		this.powers = powers;
		this.species = species;
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

	public String[] getPowers() {
		return powers;
	}

	public void setPowers(String[] powers) {
		this.powers = powers;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", species=" + species + ", powers=" + Arrays.toString(powers)
				+ "]";
	}

	

}
