package ca.ubc.cs304.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RankAverageAmountModel {

    private final Integer rank;
    private final Double amountAverage;

    public RankAverageAmountModel(Integer rank, Double amountAverage) {
        this.rank = rank;
        this.amountAverage = amountAverage;
    }

    public Integer getRank() {
        return rank;
    }

    public Double getAmountAverage() {
        return amountAverage;
    }
}
