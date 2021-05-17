package ca.ubc.cs304.model;

public class MerchantModel {

	private final Integer CID;
	private final String hometown;

	public MerchantModel(Integer CID, String hometown) {
		this.CID = CID;
		this.hometown = hometown;
	}

	public Integer getCID() {
		return CID;
	}

	public String getHometown() {
		return hometown;
	}
}
