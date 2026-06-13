package be.thomasmore.germantrio.service;

import be.thomasmore.germantrio.model.CarModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComparisonService {

    public ComparisonResult compare(CarModel leftCar, CarModel rightCar) {
        List<ComparisonRow> rows = new ArrayList<>();

        rows.add(compareHigherIsBetter("Horsepower",
                leftCar.getHorsepower(), rightCar.getHorsepower(),
                leftCar.getHorsepower() + " HP", rightCar.getHorsepower() + " HP"));

        rows.add(compareHigherIsBetter("Torque",
                leftCar.getTorqueNm(), rightCar.getTorqueNm(),
                leftCar.getTorqueNm() + " Nm", rightCar.getTorqueNm() + " Nm"));

        rows.add(compareHigherIsBetter("Drivetrain",
                drivetrainRank(leftCar.getDrivetrain()), drivetrainRank(rightCar.getDrivetrain()),
                leftCar.getDrivetrain(), rightCar.getDrivetrain()));

        rows.add(compareLowerIsBetter("Weight",
                leftCar.getWeightKg(), rightCar.getWeightKg(),
                leftCar.getWeightKg() + " kg", rightCar.getWeightKg() + " kg"));

        rows.add(compareLowerIsBetter("0-100 km/h",
                leftCar.getZeroToHundred(), rightCar.getZeroToHundred(),
                leftCar.getZeroToHundred() + " s", rightCar.getZeroToHundred() + " s"));

        rows.add(compareLowerIsBetter("Base Price",
                leftCar.getBasePriceEur(), rightCar.getBasePriceEur(),
                "EUR " + leftCar.getBasePriceEur(), "EUR " + rightCar.getBasePriceEur()));

        int leftWins = 0;
        int rightWins = 0;

        for (ComparisonRow row : rows) {
            if ("WIN".equals(row.getLeftResult())) {
                leftWins++;
            }
            if ("WIN".equals(row.getRightResult())) {
                rightWins++;
            }
        }

        String overallWinner = "DRAW";
        if (leftWins > rightWins) {
            overallWinner = "LEFT";
        } else if (rightWins > leftWins) {
            overallWinner = "RIGHT";
        }

        return new ComparisonResult(overallWinner, rows);
    }

    private ComparisonRow compareHigherIsBetter(String label, int leftValue, int rightValue,
                                                String leftDisplay, String rightDisplay) {
        return compare(label, Integer.compare(leftValue, rightValue), leftDisplay, rightDisplay);
    }

    private ComparisonRow compareLowerIsBetter(String label, int leftValue, int rightValue,
                                               String leftDisplay, String rightDisplay) {
        return compare(label, Integer.compare(rightValue, leftValue), leftDisplay, rightDisplay);
    }

    private ComparisonRow compareLowerIsBetter(String label, BigDecimal leftValue, BigDecimal rightValue,
                                               String leftDisplay, String rightDisplay) {
        return compare(label, rightValue.compareTo(leftValue), leftDisplay, rightDisplay);
    }

    private ComparisonRow compare(String label, int comparison, String leftDisplay, String rightDisplay) {
        if (comparison > 0) {
            return new ComparisonRow(label, leftDisplay, rightDisplay, "WIN", "LOSE");
        }
        if (comparison < 0) {
            return new ComparisonRow(label, leftDisplay, rightDisplay, "LOSE", "WIN");
        }
        return new ComparisonRow(label, leftDisplay, rightDisplay, "DRAW", "DRAW");
    }

    private int drivetrainRank(String drivetrain) {
        if ("AWD".equalsIgnoreCase(drivetrain)) {
            return 3;
        }
        if ("FWD".equalsIgnoreCase(drivetrain)) {
            return 2;
        }
        if ("RWD".equalsIgnoreCase(drivetrain)) {
            return 1;
        }
        return 0;
    }

    public static class ComparisonResult {
        private final String overallWinner;
        private final List<ComparisonRow> rows;

        public ComparisonResult(String overallWinner, List<ComparisonRow> rows) {
            this.overallWinner = overallWinner;
            this.rows = rows;
        }

        public String getOverallWinner() {
            return overallWinner;
        }

        public List<ComparisonRow> getRows() {
            return rows;
        }
    }

    public static class ComparisonRow {
        private final String label;
        private final String leftValue;
        private final String rightValue;
        private final String leftResult;
        private final String rightResult;

        public ComparisonRow(String label, String leftValue, String rightValue, String leftResult, String rightResult) {
            this.label = label;
            this.leftValue = leftValue;
            this.rightValue = rightValue;
            this.leftResult = leftResult;
            this.rightResult = rightResult;
        }

        public String getLabel() {
            return label;
        }

        public String getLeftValue() {
            return leftValue;
        }

        public String getRightValue() {
            return rightValue;
        }

        public String getLeftResult() {
            return leftResult;
        }

        public String getRightResult() {
            return rightResult;
        }
    }
}
