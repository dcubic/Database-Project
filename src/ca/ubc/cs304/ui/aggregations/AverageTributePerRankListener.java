package ca.ubc.cs304.ui.aggregations;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.ViewTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AverageTributePerRankListener implements ActionListener {
	private final DatabaseConnectionHandler dbHandler;

	public AverageTributePerRankListener(DatabaseConnectionHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String[][] results = dbHandler.getAverageTributeAmountsForEachRank();
			ViewTable resultWindow = new ViewTable(new String[] {"Rank", "Average amount"}, results);
			resultWindow.showFrame();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


}
