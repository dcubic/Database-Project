package ca.ubc.cs304.ui.updates;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.CitizenModel;
import ca.ubc.cs304.ui.TupleMenu;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.sql.SQLException;

public class UpdateCitizen extends TupleMenu {
	JComboBox<String> cidField;
	JTextField nameField;
	JTextField rankField;
	JTextField religiousDenominationField;
	JComboBox<String> unitField;

	public UpdateCitizen(DatabaseConnectionHandler dbHandler) {
		super("Update Citizen", dbHandler);

		try {
			cidField = new JComboBox(dbHandler.getAllCIDs().toArray());
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
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(e -> {
			try {
				Integer cid = Integer.parseInt((String) cidField.getSelectedItem());
				String name = nameField.getText();
				String religionsDenomination = religiousDenominationField.getText();
				String unit = (String) unitField.getSelectedItem();
				Integer rank = Integer.parseInt(rankField.getText());

				// TODO: what if we only want to update a single attribute?
				if (!religionsDenomination.isEmpty() && !name.isEmpty()) {
					System.out.format("Updating Citizen with CID \"%s\"", cid);
					dbHandler.updateCitizen(new CitizenModel(cid, unit, religionsDenomination, name, rank));
					System.out.println("Updating successful");
					dispose();
				}
			} catch (NumberFormatException exception) {
				System.err.println("CID and Rank must be integers");
			} catch (SQLException exception) {
				System.err.println("Updating failed");
				exception.printStackTrace();
			}
		});

		return updateButton;
	}
}
