package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RegionDAOImpl implements RegionDAO {
    List<Region> regions = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

    public RegionDAOImpl(){
        JDBC jdbc = new JDBC();
        regions= jdbc.getRegions("British","2021-05","2022-12");
    }

    @Override
    public  List<Region> getRegions()  {

        return regions;
    }

    public HashMap<String,List<Region>> getSortedRegions(){
        HashMap<String,List<Region>> sortedRegions = new HashMap<>();
        String name = "";
        for(Region r : regions){
            name = r.getRegionName();
            if(!sortedRegions.containsKey(name)){
                sortedRegions.put(name,new ArrayList<>());
            }
            sortedRegions.get(name).add(r);
        }

        return sortedRegions;
    }
}
