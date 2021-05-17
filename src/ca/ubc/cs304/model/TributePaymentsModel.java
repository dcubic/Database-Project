package ca.ubc.cs304.model;

public class TributePaymentsModel {

	private final Integer CID;
	private final String title;

	private final String tributeType;
	private final Integer amount;

	public TributePaymentsModel(Integer CID, String title, String tributeType, Integer amount) {
		this.CID = CID;
		this.title = title;
		this.tributeType = tributeType;
		this.amount = amount;
	}

	public Integer getCID() {
		return CID;
	}

	public String getTitle() {
		return title;
	}

	public String getTributeType() {
		return tributeType;
	}

	public Integer getAmount() {
		return amount;
	}
}
