package ca.ubc.cs304.model;

public class LiveStockModel {

	private final Integer tagID;

	private final String species;
	private final String breed;

	public LiveStockModel(Integer tagID, String species, String breed) {
		this.tagID = tagID;
		this.species = species;
		this.breed = breed;
	}

	public Integer getTagID() {
		return tagID;
	}

	public String getSpecies() {
		return species;
	}

	public String getBreed() {
		return breed;
	}
}
