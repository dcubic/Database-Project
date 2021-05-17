package ca.ubc.cs304.model;

public class CelebratesModel {

	private final Integer CID;
	private final String name;
	private final String associatedReligion;

	public CelebratesModel(Integer CID, String name, String associatedReligion) {
		this.CID = CID;
		this.name = name;
		this.associatedReligion = associatedReligion;
	}

	public Integer getCID() {
		return CID;
	}

	public String getName() {
		return name;
	}

	public String getAssociatedReligion() {
		return associatedReligion;
	}
}
