package ca.ubc.cs304.ui.aggregations;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.ViewTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CountReligiousSoldiersListener implements ActionListener {
	private final DatabaseConnectionHandler dbHandler;

	public CountReligiousSoldiersListener(DatabaseConnectionHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String[][] results = dbHandler.countReligiousSoldiers();
			ViewTable resultWindow = new ViewTable(new String[] {"Denomination", "Count"}, results);
			resultWindow.showFrame();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


}
