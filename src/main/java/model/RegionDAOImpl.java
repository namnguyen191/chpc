package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegionDAOImpl implements RegionDAO {
    List<Region> regions = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

    @Override
    public  List<Region> getRegions()  {

//        Date date1 = null;
//        try {
//            date1 = sdf.parse("2001-01");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        regions.add(new Region("Toronto",50.0,"2010"));
        regions.add(new Region("Toronto",40.1,"2011"));
        regions.add(new Region("Toronto",76.1,"2012"));
        regions.add(new Region("Toronto",75.1,"2013"));
        regions.add(new Region("Toronto",34.1,"2014"));
        regions.add(new Region("Toronto",66.1,"2015"));
        regions.add(new Region("Toronto",89.0,"2016"));
        regions.add(new Region("Toronto",45.7,"2017"));
        regions.add(new Region("Toronto",78.5,"2018"));

        regions.add(new Region("North York", 106.0,"2018"));
        regions.add(new Region("North York", 100.5,"2017"));
        regions.add(new Region("North York", 10.6,"2016"));
        regions.add(new Region("North York", 12.7,"2015"));
        regions.add(new Region("North York", 99.0,"2014"));
        regions.add(new Region("North York", 88.0,"2013"));
        regions.add(new Region("North York", 77.0,"2012"));
        regions.add(new Region("North York", 78.0,"2011"));
        regions.add(new Region("North York", 66.0,"2010"));

        return regions;
    }
}
