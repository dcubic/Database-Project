package ca.ubc.cs304.ui.viewing;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.TupleMenu;
import ca.ubc.cs304.ui.ViewTable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectCitizen extends TupleMenu {
	JCheckBox nameField;
	JCheckBox rankField;
	JCheckBox religiousDenominationField;
	JCheckBox unitField;

	public ProjectCitizen(DatabaseConnectionHandler dbHandler) {
		super("Update Citizen", dbHandler);

		nameField = new JCheckBox();
		rankField = new JCheckBox();
		religiousDenominationField = new JCheckBox();
		unitField = new JCheckBox();

		addAttribute("Name", nameField);
		addAttribute("Rank", rankField);
		addAttribute("Religious Denomination", religiousDenominationField);
		addAttribute("Unit", unitField);
	}

	@Override
	protected JButton getSubmitButton() {
		JButton updateButton = new JButton("Project");
		updateButton.addActionListener(e -> {
			try {
				ArrayList<String> columns= new ArrayList<String>();

				columns.add("CID");
				if (nameField.isSelected())
					columns.add("Name");
				if (religiousDenominationField.isSelected())
					columns.add("ReligiousDenomination");
				if (rankField.isSelected())
					columns.add("Rank");
				if (unitField.isSelected())
					columns.add("Unit");

				System.out.println("Performing a projection on Citizens");
				ViewTable resultWindow = new ViewTable(columns.toArray(new String[columns.size()]), dbHandler.projectCitizen(columns));
				System.out.println("Projection successful");
				resultWindow.showFrame();
				dispose();
			} catch (SQLException exception) {
				System.err.println("Projection failed");
				exception.printStackTrace();
			}
		});

		return updateButton;
	}
}
