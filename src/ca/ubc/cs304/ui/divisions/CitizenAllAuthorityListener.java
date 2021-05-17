package ca.ubc.cs304.ui.divisions;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.CitizenModel;
import ca.ubc.cs304.ui.ViewTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class CitizenAllAuthorityListener implements ActionListener {
    private final DatabaseConnectionHandler dbHandler;
    private final static int COLUMN_COUNT = 5;
    private final static String[] COLUMN_NAMES = {"CID", "Unit", "Religious Denomination", "Name", "Rank"};

    public CitizenAllAuthorityListener(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            List<CitizenModel> citizenModelsSatisfyingCondition =
                    dbHandler.getAllCitizensWhoPayTributeToAllAuthorities();
            int tupleCount = citizenModelsSatisfyingCondition.size();
            String[][] resultTuples = new String[tupleCount][COLUMN_COUNT];

            for (int i = 0; i < tupleCount; i++) {
                CitizenModel citizenNext = citizenModelsSatisfyingCondition.get(i);
                resultTuples[i][0] = Integer.toString(citizenNext.getCID());
                resultTuples[i][1] = citizenNext.getUnit();
                resultTuples[i][2] = citizenNext.getReligiousDenomination();
                resultTuples[i][3] = citizenNext.getName();
                resultTuples[i][4] = Integer.toString(citizenNext.getRank());
            }

            ViewTable resultWindow = new ViewTable(COLUMN_NAMES, resultTuples);
            resultWindow.showFrame();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
