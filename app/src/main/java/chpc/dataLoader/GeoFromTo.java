package chpc.dataLoader;

public class GeoFromTo {

    String geo;
    String fromDate;
    String toDate;

    public GeoFromTo(String regionName, String from, String to){
        geo = regionName;
        fromDate = from;
        toDate = to;
    }

    public String getGeo(){
        return geo;
    }

    public String getFrom(){
        return fromDate;
    }

    public String getTo(){
        return toDate;
    }

    public String toString(){
        return new String(geo + ", " + fromDate + " - " + toDate);
    }
}
