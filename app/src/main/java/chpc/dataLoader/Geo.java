package chpc.dataLoader;

public class Geo {

    public String geo;
    public int fromYear;
    public int fromMonth;
    public int toYear;
    public int toMonth;
    public Geo(String geo, int fromYear, int fromMonth, int toYear, int toMonth) {
        this.geo = geo;
        this.fromYear = fromYear;
        this.fromMonth = fromMonth;
        this.toYear = toYear;
        this.toMonth = toMonth;
    }
}
