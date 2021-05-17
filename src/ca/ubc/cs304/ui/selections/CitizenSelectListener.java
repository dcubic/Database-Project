package ca.ubc.cs304.ui.selections;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.database.EntityTable;
import ca.ubc.cs304.ui.ViewTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CitizenSelectListener implements ActionListener {
	private final DatabaseConnectionHandler dbHandler;
    private JTextField selectCitizenField;

    public CitizenSelectListener(DatabaseConnectionHandler dbHandler, JTextField selectCitizenField) {
		this.dbHandler = dbHandler;
        this.selectCitizenField = selectCitizenField;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String[][] results = dbHandler.selectCitizensByName(selectCitizenField.getText());
			ViewTable resultWindow = new ViewTable(new String[] {"CID", "Unit", "Religious Denomination", "Name", "Rank"}, results);
			resultWindow.showFrame();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


}
