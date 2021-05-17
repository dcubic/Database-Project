package ca.ubc.cs304.ui.insertions;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.HolidayModel;
import ca.ubc.cs304.ui.TupleMenu;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.sql.SQLException;

public class HolidayInsertMenu extends TupleMenu {
	JTextField associatedReligionField;
	JTextField nameField;

	public HolidayInsertMenu(DatabaseConnectionHandler dbHandler) {
		super("Insert Holiday", dbHandler);

		associatedReligionField = new JTextField();
		nameField = new JTextField();

		addAttribute("Associated Religion", associatedReligionField);
		addAttribute("Name", nameField);
	}

	@Override
	protected JButton getSubmitButton() {
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(e -> {
			String associatedReligion = associatedReligionField.getText();
			String name = nameField.getText();

			if (!associatedReligion.isEmpty() && !name.isEmpty()) {
				try {
					System.out.format("Inserting Holiday with Religion \"%s\" and Name \"%s\"\n", associatedReligion, name);
					// TODO: This was the previous implementation, why is date null?
					dbHandler.insertHoliday(new HolidayModel(associatedReligion, name, null));
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