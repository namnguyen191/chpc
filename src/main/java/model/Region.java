package model;

import java.util.Date;

public class Region {
    private String regionName;
    private Double NHPI;
   // private Date period;
   private String period;

    public Region() {
    }

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

//    public Date getPeriod() {
//        return period;
//    }


    public String getPeriod() {
        return period;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public void setNHPI(Double NHPI) {
        this.NHPI = NHPI;
    }

//    public void setPeriod(Date period) {
//        this.period = period;
//    }

    @Override
    public String toString() {
        return "Region{" +
                "regionName='" + regionName + '\'' +
                ", NHPI=" + NHPI +
                ", period=" + period +
                '}';
    }
}
