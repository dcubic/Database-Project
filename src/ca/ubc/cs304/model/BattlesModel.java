package ca.ubc.cs304.model;

import java.time.LocalDate;

public class BattlesModel {

	private final String army1;
	private final String army2;

	private final LocalDate date;

	public BattlesModel(String army1, String army2, LocalDate date) {
		this.army1 = army1;
		this.army2 = army2;
		this.date = date;
	}

	public String getArmy1() {
		return army1;
	}

	public String getArmy2() {
		return army2;
	}

	public LocalDate getDate() {
		return date;
	}
}
