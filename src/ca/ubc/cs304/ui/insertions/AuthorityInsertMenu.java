package ca.ubc.cs304.ui.insertions;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.AuthorityModel;
import ca.ubc.cs304.ui.TupleMenu;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.sql.SQLException;

public class AuthorityInsertMenu extends TupleMenu {
	JTextField titleField;

	public AuthorityInsertMenu(DatabaseConnectionHandler dbHandler) {
		super("Insert Authority", dbHandler);

		titleField = new JTextField();

		addAttribute("Title", titleField);
	}

	@Override
	protected JButton getSubmitButton() {
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(e -> {
			String title = titleField.getText();
			if (!title.isEmpty()) {
				try {
					System.out.format("Inserting Authority with Title \"%s\"\n", title);
					dbHandler.insertAuthority(new AuthorityModel(title));
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
