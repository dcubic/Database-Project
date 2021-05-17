package ca.ubc.cs304.ui.insertions;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.CitizenModel;
import ca.ubc.cs304.ui.TupleMenu;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.sql.SQLException;

public class CitizenInsertMenu extends TupleMenu {
	JTextField cidField;
	JTextField nameField;
	JTextField rankField;
	JTextField religiousDenominationField;
	JComboBox<String> unitField;

	public CitizenInsertMenu(DatabaseConnectionHandler dbHandler) {
		super("Insert Citizen", dbHandler);

		try {
			cidField = new JTextField();
			nameField = new JTextField();
			rankField = new JTextField();
			religiousDenominationField = new JTextField();
			unitField = new JComboBox(dbHandler.getAllUnits().toArray());

			addAttribute("CID", cidField);
			addAttribute("Name", nameField);
			addAttribute("Rank", rankField);
			addAttribute("Religious Denomination", religiousDenominationField);
			addAttribute("Unit", unitField);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	protected JButton getSubmitButton() {
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(e -> {
			try {
				Integer cid = Integer.parseInt(cidField.getText());
				String name = nameField.getText();
				String religionsDenomination = religiousDenominationField.getText();
				String unit = (String) unitField.getSelectedItem();
				Integer rank = Integer.parseInt(rankField.getText());

				if (!religionsDenomination.isEmpty() && !name.isEmpty()) {
					System.out.format("Inserting Citizen with CID \"%s\", Name, \"%s\", etc...\n", cid, name);
					dbHandler.insertCitizen(new CitizenModel(cid, unit, religionsDenomination, name, rank));
					System.out.println("Insertion successful");
					dispose();
				}
			} catch (NumberFormatException exception) {
				System.err.println("CID and Rank must be integers");
			} catch (SQLException exception) {
				System.err.println("Insertion failed");
				exception.printStackTrace();
			}
		});

		return insertButton;
	}
}
