package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegionDAOImplement implements RegionDAO {
    private static RegionDAOImplement instance;
    private List<Region> regions;
    private JDBC jdbc;

    private RegionDAOImplement(){
        jdbc = new JDBC();
    }
    public static synchronized RegionDAOImplement getInstance() {
        if(instance == null){
            instance = new RegionDAOImplement();
        }
        return instance;
    }

    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");


    public void loadData(String name, String from, String end)  {
        regions= jdbc.getRegions(name,from,end);
        System.out.println(regions);
    }

    @Override
    public  List<Region> getRegions() {
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
