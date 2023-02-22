package model;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface RegionDAO {
      List<Region> getRegions() ;
     HashMap<String,List<Region>> getSortedRegions();
}
