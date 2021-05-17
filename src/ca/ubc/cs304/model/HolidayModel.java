package ca.ubc.cs304.model;

import java.sql.Date;

public class HolidayModel {

	private final String name;
	private final String associatedReligion;

	private Date date;

	public HolidayModel(String name, String associatedReligion, Date date) {
		this.name = name;
		this.associatedReligion = associatedReligion;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public String getAssociatedReligion() {
		return associatedReligion;
	}

	public Date getDate() {
		return date;
	}

}
