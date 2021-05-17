package ca.ubc.cs304.model;

public class PlotOfLandModel {
	private final String location;

	private final String title;

	private final String landType;
	private final String landUsage;

	public PlotOfLandModel(String location, String title, String landType, String landUsage) {
		this.location = location;
		this.title = title;
		this.landType = landType;
		this.landUsage = landUsage;
	}

	public String getLocation() {
		return location;
	}

	public String getTitle() {
		return title;
	}

	public String getLandType() {
		return landType;
	}

	public String getLandUsage() {
		return landUsage;
	}
}
