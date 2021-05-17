package ca.ubc.cs304.ui.deletion;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.TupleMenu;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.sql.SQLException;

public class DeleteCitizen extends TupleMenu {
	JComboBox<String> cidField;

	public DeleteCitizen(DatabaseConnectionHandler dbHandler) {
		super("Delete Citizen", dbHandler);
		try {
			cidField = new JComboBox(dbHandler.getAllCIDs().toArray());

			addAttribute("CID", cidField);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	protected JButton getSubmitButton() {
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(e -> {
			try {
				Integer cid = Integer.parseInt((String) cidField.getSelectedItem());
				System.out.format("Deleting Citizen with CID \"%s\"", cid);
				dbHandler.deleteCitizen(cid);
				System.out.println("Deletion successful");
				dispose();
			} catch (NumberFormatException exception) {
				System.err.println("CID must be an integer");
			} catch (SQLException exception) {
				System.err.println("Deletion failed");
				exception.printStackTrace();
			}
		});

		return deleteButton;
	}
}
