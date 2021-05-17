package ca.ubc.cs304.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewTable extends JFrame {


    private final String[] columnNames;
    private final String[][] tuples;

    public ViewTable(String[] columnNames, String[][] tuples) {
        super("View Tables");
        this.columnNames = columnNames;
        this.tuples = tuples;
    }

    public void showFrame() {
        JTable table = new JTable(tuples, columnNames);
        JScrollPane tableScollPane = new JScrollPane(table);
        add(tableScollPane);
        setSize(500, 500);
        setVisible(true);
    }
}
