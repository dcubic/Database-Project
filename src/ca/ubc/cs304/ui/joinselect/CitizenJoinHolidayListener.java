package ca.ubc.cs304.ui.joinselect;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.CitizenModel;
import ca.ubc.cs304.ui.ViewTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class CitizenJoinHolidayListener implements ActionListener {
    private final DatabaseConnectionHandler dbHandler;
    private JTextField conditionTextField;
    private final static int COLUMN_COUNT = 5;
    private final static String[] COLUMN_NAMES = {"CID", "Unit", "Religious Denomination", "Name", "Rank"};

    public CitizenJoinHolidayListener(DatabaseConnectionHandler dbHandler, JTextField conditionTextField) {
        this.dbHandler = dbHandler;
        this.conditionTextField = conditionTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            List<CitizenModel> citizenModelsSatisfyingCondition =
                    dbHandler.selectCitizensJoinHoliday(conditionTextField.getText());
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
