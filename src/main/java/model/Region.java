package model;

import java.util.Date;

public class Region {
    private String regionName;
    private double NHPI;
   // private Date period;
    private String period;

//    public Region() {
//    }

    public Region(String regionName, Double NHPI, String period) {
        this.regionName = regionName;
        this.NHPI = NHPI;
        this.period = period;
    }

    public String getRegionName() {
        return regionName;
    }

    public Double getNHPI() {
        return NHPI;
    }


    public String getPeriod() {
        return period;
    }





    //this function is used to test output
    @Override
    public String toString() {
        return "Region{" +
                "regionName='" + regionName + '\'' +
                ", NHPI=" + NHPI +
                ", period=" + period +
                '}';
    }
}
