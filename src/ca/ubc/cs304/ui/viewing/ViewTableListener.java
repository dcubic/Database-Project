package ca.ubc.cs304.ui.viewing;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.database.EntityTable;
import ca.ubc.cs304.ui.ViewTable;

import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ViewTableListener implements ActionListener {


    private final JComboBox<EntityTable> targetTable;
    private final DatabaseConnectionHandler dbHandler;

    public ViewTableListener(JComboBox<EntityTable> targetTable, DatabaseConnectionHandler dbHandler) {

        this.targetTable = targetTable;
        this.dbHandler = dbHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tableName = ((EntityTable) targetTable.getSelectedItem()).getTableName();
        try {
            String[] columnNames = dbHandler.getColumnNames(tableName);
            String[][] tuples = dbHandler.getAllTuples(tableName, columnNames.length);
            ViewTable viewTableMenu = new ViewTable(columnNames, tuples);
            viewTableMenu.showFrame();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
