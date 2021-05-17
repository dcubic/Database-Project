package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.database.EntityTable;
import ca.ubc.cs304.ui.aggregations.AverageTributePerRankListener;
import ca.ubc.cs304.ui.aggregations.CountAuthorityTributesListener;
import ca.ubc.cs304.ui.aggregations.CountReligiousSoldiersListener;
import ca.ubc.cs304.ui.deletion.DropAllListener;
import ca.ubc.cs304.ui.divisions.CitizenAllAuthorityListener;
import ca.ubc.cs304.ui.insertions.CreateAllListener;
import ca.ubc.cs304.ui.insertions.InsertTupleListener;
import ca.ubc.cs304.ui.deletion.DeleteCitizen;
import ca.ubc.cs304.ui.joinselect.CitizenJoinHolidayListener;
import ca.ubc.cs304.ui.selections.CitizenSelectListener;
import ca.ubc.cs304.ui.updates.UpdateCitizen;
import ca.ubc.cs304.ui.viewing.ProjectCitizen;
import ca.ubc.cs304.ui.viewing.ViewTableListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class MainMenu extends JFrame {

	private DatabaseConnectionHandler dbHandler = null;
	private final static int WIDTH_OF_MENU = 225;
	private final static int HEIGHT_OF_MENU = 550;

	public MainMenu(DatabaseConnectionHandler dbHandler) {
		super("Main Menu");
		this.dbHandler = dbHandler;
	}

	public void showFrame() {
		JComboBox<EntityTable> targetTable = new JComboBox<EntityTable>();
		targetTable.setModel(new DefaultComboBoxModel<EntityTable>(EntityTable.values()));
		add(targetTable);

		// Insertions
		JButton insertTuple = new JButton("Insert Tuple");
		insertTuple.addActionListener(new InsertTupleListener(targetTable, dbHandler));
		add(insertTuple);

		// Viewing
		JButton viewTable = new JButton("View table");
		viewTable.addActionListener(new ViewTableListener(targetTable, dbHandler));
		add(viewTable);

		// Count soldiers for each religious denomination
		JButton religiousSoldierCount = new JButton("Soldier Count");
		religiousSoldierCount.addActionListener(new CountReligiousSoldiersListener(dbHandler));
		add(religiousSoldierCount);

		// Update citizen
		JButton updateCitizen = new JButton("Update Citizen");
		updateCitizen.addActionListener(e -> {
			new UpdateCitizen(dbHandler).completeFrame();
		});
		add(updateCitizen);

        // Select Citizen
        JTextField selectCitizenField = new JTextField();
        selectCitizenField.setColumns(15);
        add(selectCitizenField);

        JButton selectCitizenButton = new JButton("Select citizen's by name");
        selectCitizenButton.addActionListener(new CitizenSelectListener(dbHandler, selectCitizenField));
        add(selectCitizenButton);

        // Delete citizen
		JButton deleteCitizen = new JButton("Delete Citizen");
		deleteCitizen.addActionListener(e -> {
			new DeleteCitizen(dbHandler).completeFrame();
		});
		add(deleteCitizen);

		// Project citizen
		JButton projectCitizen = new JButton("Project Citizen");
		projectCitizen.addActionListener(e -> {
			new ProjectCitizen(dbHandler).completeFrame();
		});
		add(projectCitizen);

		// Average Tribute Payments (HAVING)
		JTextField minTributes = new JTextField();
		minTributes.setColumns(10);
		add(minTributes);
		JButton authorityTributesCount = new JButton("Authority Tributes Count");
		authorityTributesCount.addActionListener(new CountAuthorityTributesListener(dbHandler, minTributes));
		add(authorityTributesCount);

        JButton averageTributeButton = new JButton("Average Tributes By Rank");
        averageTributeButton.addActionListener(new AverageTributePerRankListener(dbHandler));
        add(averageTributeButton);

        // CitizenHolidayJoin Interface

        JTextField holidayConditionTextField = new JTextField();
        holidayConditionTextField.setColumns(15);
        add(holidayConditionTextField);

        JButton citizenHolidayJoinButton = new JButton("Citizens w/ Holiday");
        citizenHolidayJoinButton.addActionListener(new CitizenJoinHolidayListener(dbHandler, holidayConditionTextField));
        add(citizenHolidayJoinButton);

        // CitizenAllAuthorityTribute Division Interface

        JButton citizensTributingAllAuthoritiesButton = new JButton("Citizens paying all Authorities");
        citizensTributingAllAuthoritiesButton.addActionListener(new CitizenAllAuthorityListener(dbHandler));
        add(citizensTributingAllAuthoritiesButton);

        JButton dropTablesButton = new JButton("Drop ALL");
        dropTablesButton.addActionListener(new DropAllListener(dbHandler));
        add(dropTablesButton);

        JButton createTablesButton = new JButton("Create ALL");
        createTablesButton.addActionListener(new CreateAllListener(dbHandler));
        add(createTablesButton);

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setSize(WIDTH_OF_MENU, HEIGHT_OF_MENU);
        setVisible(true);
    }
}
