package ca.ubc.cs304.model;

public class WorksOnModel {

	private final Integer CID;
	private final String location;
	private final String title;

	private final Integer salary;

	public WorksOnModel(Integer CID, String location, String title, Integer salary) {
		this.CID = CID;
		this.location = location;
		this.title = title;
		this.salary = salary;
	}

	public Integer getCID() {
		return CID;
	}

	public String getLocation() {
		return location;
	}

	public String getTitle() {
		return title;
	}

	public Integer getSalary() {
		return salary;
	}
}
