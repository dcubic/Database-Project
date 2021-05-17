package ca.ubc.cs304.ui.insertions;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAllListener implements ActionListener {

    private DatabaseConnectionHandler dbHandler;

    public CreateAllListener(DatabaseConnectionHandler dbHandler) {

        this.dbHandler = dbHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dbHandler.databaseSetup();
    }
}
