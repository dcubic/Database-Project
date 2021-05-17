package ca.ubc.cs304.model;

public class ArmyModel {

	private final String unit;

	private final String title;

	public ArmyModel(String unit, String title) {
		this.unit = unit;
		this.title = title;
	}

	public String getUnit() {
		return unit;
	}

	public String getTitle() {
		return title;
	}

}
