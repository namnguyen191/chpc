package chpc.statisticaltest;

/**
 * Interface for statistics
 * Any object implementing this interface can be used in lieu of StatisticsResults to customise the statistics that the user wants
 */
public interface StatsPlug {
    public void loadValues(double[] values);
    public String getStats();
}
