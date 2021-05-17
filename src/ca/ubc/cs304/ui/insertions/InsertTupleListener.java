package ca.ubc.cs304.ui.insertions;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.database.EntityTable;
import ca.ubc.cs304.ui.TupleMenu;

import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertTupleListener implements ActionListener {
	DatabaseConnectionHandler dbHandler;
	JComboBox<EntityTable> entityTables;

	public InsertTupleListener(JComboBox<EntityTable> entityTables, DatabaseConnectionHandler dbHandler) {
		this.dbHandler = dbHandler;
		this.entityTables = entityTables;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		EntityTable selectedEntity = (EntityTable) entityTables.getSelectedItem();
		TupleMenu insertWindow = null;
		switch (selectedEntity) {
			case AUTHORITY:
				insertWindow = new AuthorityInsertMenu(dbHandler);
				break;
			case ARMY:
				insertWindow = new ArmyInsertMenu(dbHandler);
				break;
			case CITIZEN:
				insertWindow = new CitizenInsertMenu(dbHandler);
				break;
			case HOLIDAY:
				insertWindow = new HolidayInsertMenu(dbHandler);
				break;
			case TRIBUTE_PAYMENT:
				insertWindow = new PaysTributeInsertMenu(dbHandler);
				break;
			default:
				// Tried to insert a tuple for a table without insertion support. PANIK!
				System.err.println("Uh oh...");
				return;
		}

		insertWindow.completeFrame();
	}
}
