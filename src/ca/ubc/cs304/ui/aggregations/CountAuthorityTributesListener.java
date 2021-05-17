package ca.ubc.cs304.ui.aggregations;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.ViewTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CountAuthorityTributesListener implements ActionListener {
	private final DatabaseConnectionHandler dbHandler;
	private JTextField minTributes;

	public CountAuthorityTributesListener(DatabaseConnectionHandler dbHandler, JTextField minTributes) {
		this.dbHandler = dbHandler;
		this.minTributes = minTributes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String[][] results = dbHandler.getAuthoritiesWithMinTributaries(Integer.parseInt(minTributes.getText()));
			ViewTable resultWindow = new ViewTable(new String[]{"Authorities", "Count"}, results);
			resultWindow.showFrame();
		} catch (NumberFormatException ex) {
			System.err.println("Minimum Tribute Count must be an integer");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


}
