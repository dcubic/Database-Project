package ca.ubc.cs304.model;

import java.time.LocalDate;

public class TradesModel {

	private final Integer citizen1CID;
	private final Integer citizen2CID;

	private final String goodsTraded;
	private final LocalDate date;

	public TradesModel(Integer citizen1CID, Integer citizen2CID,
	                   String goodsTraded, LocalDate date) {
		this.citizen1CID = citizen1CID;
		this.citizen2CID = citizen2CID;
		this.goodsTraded = goodsTraded;
		this.date = date;
	}

	public Integer getCitizen1CID() {
		return citizen1CID;
	}

	public Integer getCitizen2CID() {
		return citizen2CID;
	}

	public String getGoodsTraded() {
		return goodsTraded;
	}

	public LocalDate getDate() {
		return date;
	}
}
