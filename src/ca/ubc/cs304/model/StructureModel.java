package ca.ubc.cs304.model;

public class StructureModel {

	private final String relativeCoordinates;
	private final String location;

	private final String dimensions;
	private final String structureType;

	public StructureModel(String relativeCoordinates, String location,
	                      String dimensions, String structureType) {
		this.relativeCoordinates = relativeCoordinates;
		this.location = location;
		this.dimensions = dimensions;
		this.structureType = structureType;
	}

	public String getRelativeCoordinates() {
		return relativeCoordinates;
	}

	public String getLocation() {
		return location;
	}

	public String getDimensions() {
		return dimensions;
	}

	public String getStructureType() {
		return structureType;
	}
}
