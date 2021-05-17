package ca.ubc.cs304.model;

public class PeasantModel {

	private final Integer CID;
	private final String type;

	public PeasantModel(Integer CID, String type) {
		this.CID = CID;
		this.type = type;
	}

	public Integer getCID() {
		return CID;
	}

	public String getType() {
		return type;
	}
}
