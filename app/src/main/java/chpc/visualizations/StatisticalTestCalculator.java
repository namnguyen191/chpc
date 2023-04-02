package chpc.visualizations;

import org.apache.commons.math3.stat.inference.MannWhitneyUTest;
import org.apache.commons.math3.stat.inference.TTest;

public class StatisticalTestCalculator {

    public static double performTTest(double[] series1, double[] series2) {
        TTest tTest = new TTest();
        return tTest.tTest(series1, series2);
    }

    public static double performMannWhitneyUTest(double[] series1, double[] series2) {
        MannWhitneyUTest mannWhitneyUTest = new MannWhitneyUTest();
        return mannWhitneyUTest.mannWhitneyUTest(series1, series2);
    }

    public static boolean canRejectNullHypothesis(double pValue, double significanceLevel) {
        return pValue < significanceLevel;
    }
}
