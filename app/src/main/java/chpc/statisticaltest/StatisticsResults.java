package chpc.statisticaltest;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * Class for getting statistics from a dataset
 */
public class StatisticsResults implements StatsPlug{

    SummaryStatistics apacheStats = new SummaryStatistics();

    /**
     * Load values into SummaryStatistics object in StatisticsResult
     * @Wparam values Array of doubles representing values meant to be inserted for statistical processing
     */
    public void loadValues(double[] values){
        for(double entry : values){
            apacheStats.addValue(entry);
        }
    }

    /**
     * Method to get statistics after loading in values
     * @return string containing results
     */
    public String getStats(){
        String result = "";
        result += "Mean: " + apacheStats.getMean() + "\n";
        result += "Standard Deviation: " + apacheStats.getStandardDeviation() + "\n";
        result += "Min: " + apacheStats.getMin() + "\n";
        result += "Max: " + apacheStats.getMax() + "\n";

        return result;
    }
    
}
