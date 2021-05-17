package ca.ubc.cs304.ui.deletion;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.ViewTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DropAllListener implements ActionListener {
	private final DatabaseConnectionHandler dbHandler;

	public DropAllListener(DatabaseConnectionHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dbHandler.dropAllTables();
	}
}
