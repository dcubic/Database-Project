package ca.ubc.cs304.model;

public class CitizenModel {
	private final Integer CID;

	private final String unit;

	private final String religiousDenomination;
	private final String name;
	private final Integer rank;

	public CitizenModel(Integer CID, String unit,
	                    String religiousDenomination, String name, Integer rank) {
		this.CID = CID;
		this.unit = unit;
		this.religiousDenomination = religiousDenomination;
		this.name = name;
		this.rank = rank;
	}

	public Integer getCID() {
		return CID;
	}

	public String getUnit() {
		return unit;
	}

	public String getReligiousDenomination() {
		return religiousDenomination;
	}

	public String getName() {
		return name;
	}

	public Integer getRank() {
		return rank;
	}

}
