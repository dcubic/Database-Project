package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public abstract class TupleMenu extends JFrame {

	protected DatabaseConnectionHandler dbHandler;
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gridBagConstraints;
	private int attributeCount;

	public TupleMenu(String windowLabel, DatabaseConnectionHandler dbHandler) {
		super(windowLabel);
		this.dbHandler = dbHandler;

		attributeCount = 0;
		this.gridBagLayout = new GridBagLayout();
		this.gridBagConstraints = new GridBagConstraints();
	}

	public void addAttribute(String attributeKey, JComponent fieldSetter) {
		// Label
		JLabel attributeLabel = new JLabel(attributeKey);
		gridBagConstraints.fill = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = attributeCount;
		gridBagLayout.setConstraints(attributeLabel, gridBagConstraints);
		add(attributeLabel);

		// Setter
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 5;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = attributeCount;
		gridBagLayout.setConstraints(fieldSetter, gridBagConstraints);
		add(fieldSetter);

		attributeCount++;
	}

	public void completeFrame() {
		// Add insert button
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = attributeCount;
		gridBagConstraints.weightx = 1;
		JButton insertButton = getSubmitButton();
		gridBagLayout.setConstraints(insertButton, gridBagConstraints);
		add(insertButton);

		setLayout(gridBagLayout);
		setSize(500, 50 + attributeCount * 50); // TODO: is 50 * attributeCount reasonable scaling?
		this.setVisible(true);
	}

	public DatabaseConnectionHandler getDbHandler() {
		return dbHandler;
	}

	protected abstract JButton getSubmitButton();
}
