package ca.ubc.cs304.ui.insertions;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.TributePaymentsModel;
import ca.ubc.cs304.ui.TupleMenu;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.sql.SQLException;

public class PaysTributeInsertMenu extends TupleMenu {
	JComboBox<String> cidField;
	JComboBox<String> titleField;
	JTextField tributeTypeField;
	JTextField amountField;

	public PaysTributeInsertMenu(DatabaseConnectionHandler dbHandler) {
		super("Insert PaysTribute", dbHandler);

		try {
			cidField = new JComboBox(dbHandler.getAllCIDs().toArray());
			titleField = new JComboBox(dbHandler.getAllTitles().toArray());
			// TODO: Tribute type should be a combobox with an enum, not string
			tributeTypeField = new JTextField();
			amountField = new JTextField();

			addAttribute("CID", cidField);
			addAttribute("Title", titleField);
			addAttribute("Tribute Type", tributeTypeField);
			addAttribute("Amount", amountField);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	protected JButton getSubmitButton() {
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(e -> {
			try {
				Integer cid = Integer.valueOf((String) cidField.getSelectedItem());
				String title = (String) titleField.getSelectedItem();
				String tributeType = tributeTypeField.getText();
				Integer amount = amountField.getText().equals("") ? 0 : Integer.valueOf(amountField.getText());

				if (!title.isEmpty()) {
					System.out.format("Inserting TributePayment with CID \"%s\", Title, \"%s\", etc...\n", cid, title);
					dbHandler.insertPaysTribute(new TributePaymentsModel(cid, title, tributeType, amount));
					System.out.println("Insertion successful");
					dispose();
				}
			} catch (NumberFormatException exception) {
				exception.printStackTrace();
			} catch (SQLException exception) {
				System.err.println("Insertion failed");
				exception.printStackTrace();
			}
		});

		return insertButton;
	}
}
