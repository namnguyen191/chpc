package chpc.Statistics;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class StatisticsResults implements StatsPlug{

    SummaryStatistics apacheStats = new SummaryStatistics();

    public void loadValues(double[] values){
        for(double entry : values){
            apacheStats.addValue(entry);
        }
    }

    public String getStats(){
        String result = "";
        result += "Mean: " + apacheStats.getMean() + "\n";
        result += "Standard Deviation: " + apacheStats.getStandardDeviation() + "\n";
        result += "Min: " + apacheStats.getMin() + "\n";
        result += "Max: " + apacheStats.getMax() + "\n";

        return result;
    }
    
}
