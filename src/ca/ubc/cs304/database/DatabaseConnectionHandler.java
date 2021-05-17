package ca.ubc.cs304.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ca.ubc.cs304.model.*;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
    // private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertHoliday(HolidayModel holidayModel) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Holiday VALUES (?,?,?)");
            ps.setString(1, holidayModel.getName());
            ps.setString(2, holidayModel.getAssociatedReligion());
            if (holidayModel.getDate() == null)
                ps.setNull(3, Types.DATE);
            else
                ps.setDate(3, holidayModel.getDate());
            ps.executeUpdate();
            connection.commit();

            ps.close();
    }

    public void insertAuthority(AuthorityModel authorityModel) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Authority VALUES (?)");
        ps.setString(1, authorityModel.getTitle());
        ps.executeUpdate();
        connection.commit();

        ps.close();
    }

    public List<String> getAllTitles() throws SQLException {
        List<String> authorityTitles = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Title FROM Authority");
        while (rs.next()) {
            authorityTitles.add(rs.getString("Title"));
        }
        return authorityTitles;
    }

    public void insertArmy(ArmyModel armyModel) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Army VALUES (?,?)");
        ps.setString(1, armyModel.getUnit());
        ps.setString(2, armyModel.getTitle());

        ps.executeUpdate();
        connection.commit();

        ps.close();
    }

    public List<String> getAllUnits() throws SQLException {
        List<String> authorityTitles = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Unit FROM Army");
        while (rs.next()) {
            authorityTitles.add(rs.getString("Unit"));
        }
        return authorityTitles;
    }

    public void insertCitizen(CitizenModel citizenModel) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Citizen VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, citizenModel.getCID());
            ps.setString(2, citizenModel.getUnit());
            ps.setString(3, citizenModel.getReligiousDenomination());
            ps.setString(4, citizenModel.getName());
            ps.setInt(5, citizenModel.getRank());
            ps.executeUpdate();
            ps.getConnection().commit();
            ps.close();
    }

    /**
     * @return citizensSatisfyingCondition
     * @throws SQLException
     */
    public String[][] selectCitizensByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM Citizen WHERE Name = '"+ name +"'");

        List<String[]> tuples = new ArrayList<>();
        while(resultSet.next()){
            tuples.add(new String[] {String.valueOf(resultSet.getInt(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    String.valueOf(resultSet.getInt(5))});
        }

        String[][] tuplesArray = new String[tuples.size()][2];
        return tuples.toArray(tuplesArray);
    }

    public String[][] projectCitizen(ArrayList<String> columns) throws SQLException {
    	int numColumns = columns.size();
        if (numColumns < 5)
        	System.err.format("Tried to project citizen given column count %d", numColumns);

		String query = String.format("SELECT CID %s %s %s %s FROM CITIZEN",
				            (columns.contains("Name") ? ", Name" : ""),
				            (columns.contains("Rank") ? ", Rank" : ""),
				            (columns.contains("ReligiousDenomination") ? ", ReligiousDenomination" : ""),
				            (columns.contains("Unit") ? ", Unit" : ""));

	    Statement statement = connection.createStatement();
	    ResultSet resultSet = statement.executeQuery(query);
	    List<String[]> tupleList = new ArrayList<>();
	    while(resultSet.next()){
		    String[] tuple = new String[numColumns];
		    for (int i = 0; i < numColumns; i++) {
			    tuple[i] = (String.valueOf(resultSet.getObject(i + 1)));
		    }
		    tupleList.add(tuple);
	    }
	   return tupleList.toArray(new String[tupleList.size()][numColumns]);
    }

    public void deleteCitizen(Integer CID) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM Citizen WHERE CID = ?");
        ps.setInt(1, CID);

        int rowCount = ps.executeUpdate();
        if (rowCount == 0) {
            System.out.println(WARNING_TAG + " Citizen " + CID + " does not exist!");
        }
        ps.getConnection().commit();
        ps.close();

        // TODO ensure that the ON DELETE CASCADE actually works and that Celebrates(CID, ...) is deleted also
        //  maybe do this in code using an assert?
        //  could just do this visually
    }

    // Given a citizen model, update the citizens table.
    public void updateCitizen(CitizenModel citizenModel)  throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE Citizen " +
                        "SET Unit = ?, " +
                        "ReligiousDenomination = ?, " +
                        "Name = ?, " +
                        "Rank = ? " +
                        "WHERE CID = ?");
        preparedStatement.setString(1, citizenModel.getUnit());
        preparedStatement.setString(2, citizenModel.getReligiousDenomination());
        preparedStatement.setString(3, citizenModel.getName());
        preparedStatement.setInt(4, citizenModel.getRank());
        preparedStatement.setInt(5, citizenModel.getCID());

        int rowCount = preparedStatement.executeUpdate();
        if (rowCount == 0) {
            System.out.println(WARNING_TAG + " Citizen " + citizenModel.getCID() + " does not exist!");
        }

        preparedStatement.close();
    }

    //TODO: Possibly might delete this, not used
    public void insertCelebrates(CelebratesModel celebratesModel) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO Celebrates VALUES (?, ?, ?)");
        preparedStatement.setInt(1, celebratesModel.getCID());
        preparedStatement.setString(2, celebratesModel.getAssociatedReligion());
        preparedStatement.setString(3, celebratesModel.getName());

        preparedStatement.executeUpdate();
        preparedStatement.getConnection().commit();
        preparedStatement.close();
    }

    /**
     *
     * @param conditionalClause String specifying the user selected condition; Set to some default
     *                          boolean TRUE if such a value is possible; otherwise use a String like
     *                          "1 > 0"
     * @return allCitizensSatisfyingClause
     * @throws SQLException
     */
    public List<CitizenModel> selectCitizensJoinHoliday(String conditionalClause) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                "FROM Citizen ci1 " +
                "WHERE ci1.CID IN (" +
                "SELECT ci2.CID " +
                "FROM Citizen ci2, Celebrates ce, Holiday h " +
                "WHERE ci2.CID = ce.CID AND " +
                "ce.Name = h.Name AND " +
                "ce.AssociatedReligion = h.AssociatedReligion AND " +
                "(?))");

        preparedStatement.setString(1, conditionalClause);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<CitizenModel> allCitizensSatisfyingClause = new ArrayList<>();
        while (resultSet.next()) {
            Integer CID = resultSet.getInt("CID");
            String unit = resultSet.getString("Unit");
            String religiousDenomination = resultSet.getString("ReligiousDenomination");
            String name = resultSet.getString("Name");
            Integer rank = resultSet.getInt("Rank");
            CitizenModel citizenModel =
                    new CitizenModel(CID, unit, religiousDenomination, name, rank);
            allCitizensSatisfyingClause.add(citizenModel);
        }

        return allCitizensSatisfyingClause;
    }

    public List<CitizenModel> getAllCitizensWhoPayTributeToAllAuthorities() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM Citizen c " +
                "WHERE NOT EXISTS (SELECT a.Title " +
                "FROM Authority a " +
                "WHERE NOT EXISTS (SELECT tp.Title " +
                "FROM TributePayments tp " +
                "WHERE tp.CID = c.CID " +
                "AND tp.Title = a.title))");

        List<CitizenModel> allCitizensSatisfyingClause = new ArrayList<>();
        while (resultSet.next()) {
            Integer CID = resultSet.getInt("CID");
            String unit = resultSet.getString("Unit");
            String religiousDenomination = resultSet.getString("ReligiousDenomination");
            String name = resultSet.getString("Name");
            Integer rank = resultSet.getInt("Rank");
            CitizenModel citizenModel =
                    new CitizenModel(CID, unit, religiousDenomination, name, rank);
            allCitizensSatisfyingClause.add(citizenModel);
        }

        return allCitizensSatisfyingClause;
    }

    public List<String> getAllCIDs() throws SQLException {
        List<String> citizenCIDs = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT CID FROM Citizen");
        while (rs.next()) {
            citizenCIDs.add(String.valueOf(rs.getInt("CID")));
        }
        return citizenCIDs;
    }

    /**
     *
     * @return A list of RankAverageAmountModel objects, which is just a wrapper class to encapsulate
     *         both the rank and average amount of tribute payment for citizens of that rank
     * @throws SQLException
     */
    public String[][] getAverageTributeAmountsForEachRank() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Rank, AVG(Amount) " +
                "FROM (SELECT c.Rank, tp.Amount " +
                "FROM Citizen c, TributePayments tp " +
                "WHERE c.CID = tp.CID) " +
                "GROUP BY Rank");

        List<String[]> tuples = new ArrayList<>();
        while(resultSet.next()){
            tuples.add(new String[] {String.valueOf(resultSet.getInt(1)), String.valueOf(resultSet.getInt(2))});
        }
        String[][] tuplesArray = new String[tuples.size()][2];
        return tuples.toArray(tuplesArray);
    }

    public void insertPaysTribute(TributePaymentsModel paysTributeModel) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO TributePayments VALUES (?, ?, ?, ?)");
        ps.setInt(1, paysTributeModel.getCID());
        ps.setString(2, paysTributeModel.getTitle());
        ps.setString(3, paysTributeModel.getTributeType());
        ps.setInt(4, paysTributeModel.getAmount());
        ps.executeUpdate();
        ps.getConnection().commit();
        ps.close();
    }

    public String[] getColumnNames(String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT column_name FROM ALL_TAB_COLUMNS WHERE table_name = '" + tableName.toUpperCase() + "'");
        List<String> columnNames = new ArrayList<>();
        while(rs.next()){
            columnNames.add(rs.getString("COLUMN_NAME"));
        }
        String[] columnNamesArray = new String[columnNames.size()];
        return columnNames.toArray(columnNamesArray);
    }

    public String[][] getAllTuples(String tableName, int numberOfColumns) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        List<String[]> tuples = new ArrayList<>();
        while(rs.next()){
            List<String> tuple = new ArrayList<>();
            for (int i = 1; i <= numberOfColumns; i++) {
                tuple.add(String.valueOf(rs.getObject(i)));
            }
            String[] tupleArray = new String[numberOfColumns];
            tuples.add(tuple.toArray(tupleArray));
        }
        String[][] tuplesArray = new String[tuples.size()][numberOfColumns];
        return tuples.toArray(tuplesArray);
    }

	public String[][] countReligiousSoldiers() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"SELECT c.ReligiousDenomination, COUNT (c.CID) FROM Citizen c GROUP BY c.ReligiousDenomination");
		List<String[]> tuples = new ArrayList<>();
		while(resultSet.next()){
			tuples.add(new String[] {resultSet.getString(1), resultSet.getString(2)});
		}
		String[][] tuplesArray = new String[tuples.size()][2];
		return tuples.toArray(tuplesArray);
	}

    /**
     *
     * @param minTributes the minimum number of tributes required for the having clause
     * @throws SQLException
     */
	public String[][] getAuthoritiesWithMinTributaries(int minTributes) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT DISTINCT Title, COUNT(*) FROM TributePayments GROUP BY Title HAVING COUNT(*) > " + minTributes);
        List<String[]> tuples = new ArrayList<>();
        while(resultSet.next()){
            tuples.add(new String[]{resultSet.getString(1), resultSet.getString(2)});
        }
        String[][] tuplesArray = new String[tuples.size()][2];
        return tuples.toArray(tuplesArray);
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void databaseSetup() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE Authority (" +
                    "Title varchar2(20) PRIMARY KEY)");
            statement.executeUpdate("CREATE TABLE Army (" +
                    "Unit varchar2(20) NOT NULL PRIMARY KEY," +
                    "Title varchar2(20)," +
                    "FOREIGN KEY (Title) REFERENCES Authority(Title))");
            statement.executeUpdate("CREATE TABLE Holiday (" +
                    "Name varchar2(20), " +
                    "AssociatedReligion varchar2(20), " +
                    "Start_Date DATE, " +
                    "PRIMARY KEY (Name, AssociatedReligion))");
            statement.executeUpdate("CREATE TABLE Citizen (" +
                    "CID INTEGER PRIMARY KEY, " +
                    "Unit varchar2(20), " +
                    "ReligiousDenomination varchar2(20), " +
                    "Name varchar2(20), " +
                    "Rank INTEGER, " +
                    "FOREIGN KEY (Unit) REFERENCES Army(Unit))");
            statement.executeUpdate("CREATE TABLE Celebrates (" +
                    "CID INTEGER, " +
                    "AssociatedReligion varchar2(20), " +
                    "Name varchar2(20), " +
                    "PRIMARY KEY (CID, Name, AssociatedReligion), " +
                    "FOREIGN KEY (CID) REFERENCES Citizen(CID) ON DELETE CASCADE, " +
                    "FOREIGN KEY (Name, AssociatedReligion) REFERENCES Holiday(Name, AssociatedReligion) ON DELETE CASCADE)");

            statement.executeUpdate("CREATE TABLE TributePayments (" +
                    "CID INTEGER, " +
                    "Title varchar2(20), " +
                    "TributeType varchar2(20), " +
                    "Amount INTEGER, " +
                    "PRIMARY KEY (CID, Title), " +
                    "FOREIGN KEY (CID) REFERENCES Citizen(CID) ON DELETE CASCADE, " +
                    "FOREIGN KEY (Title) REFERENCES Authority(Title) ON DELETE CASCADE)");

            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            e.printStackTrace();
            System.out.println("Failed to create all tables");
        }

        // TODO Populate Database
//		BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
//		insertBranch(branch1);
//
//		BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
//		insertBranch(branch2);
    }

    public void dropAllTables() {
        try {
            Statement statement = connection.createStatement();
            for (EntityTable e : EntityTable.values()) {
	            try {
		            statement.execute("DROP TABLE " + e.getTableName() + " cascade constraints");
	            } catch (SQLException ex){
		            System.out.println("We failed to drop " + e.getTableName() + " tables");
	            }
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
