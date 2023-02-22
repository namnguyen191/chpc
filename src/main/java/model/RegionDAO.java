package model;

import java.util.HashMap;
import java.util.List;

public interface RegionDAO {
      List<Region> getRegions() ;
      HashMap<String,List<Region>> getSortedRegions();
      void loadData(String name, String from, String end);
}
