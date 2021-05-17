package ca.ubc.cs304.database;

public enum EntityTable {

	ARMY("Army"),
	AUTHORITY("Authority"),
	CITIZEN("Citizen"),
	HOLIDAY("Holiday"),
    CELEBRATES("Celebrates"),
	TRIBUTE_PAYMENT("TributePayments");

	private final String tableName;

	EntityTable(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public static EntityTable fromTableName(String tableName) {
		switch (tableName.toLowerCase()) {
			case "army":
				return ARMY;
			case "authority":
				return AUTHORITY;
			case "citizen":
				return CITIZEN;
			case "holiday":
				return HOLIDAY;
            case "celebrates":
                return CELEBRATES;
			case "tributepayments":
				return TRIBUTE_PAYMENT;
			default:
				return null;
		}
	}

	@Override
	public String toString() {
		return this.getTableName();
	}
}
