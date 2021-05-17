package ca.ubc.cs304.ui.insertions;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.ArmyModel;
import ca.ubc.cs304.ui.TupleMenu;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.sql.SQLException;

public class ArmyInsertMenu extends TupleMenu {
	private JTextField unitField;
	private JComboBox titleField;

	public ArmyInsertMenu(DatabaseConnectionHandler dbHandler) {
		super("Insert Army", dbHandler);

		try {
			unitField = new JTextField();
			titleField = new JComboBox(dbHandler.getAllTitles().toArray());

			addAttribute("Unit", unitField);
			addAttribute("Title", titleField);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected JButton getSubmitButton() {
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(e -> {
			String unit = unitField.getText();
			String title = (String) titleField.getSelectedItem();

			if (!unit.isEmpty() && title != null && !title.isEmpty()) {
				try {
					System.out.format("Inserting Army with Unit \"%s\" and Title \"%s\"\n", unit, title);
					dbHandler.insertArmy(new ArmyModel(unit, title));
					System.out.println("Insertion successful");
					dispose();
				} catch (SQLException ex) {
					System.err.println("Insertion failed");
					ex.printStackTrace();
				}
			}
		});

		return insertButton;
	}
}
