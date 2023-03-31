package chpc.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataStore {
  private static DataStore instance;

  private static Set<NHPIRecord> loadedData;

  private DataStore() {
    loadedData = new HashSet<NHPIRecord>();
  }

  public static DataStore getInstance() {
    if (instance == null) {
      instance = new DataStore();
    }

    return instance;
  }

  public void loadData(List<NHPIRecord> newData) {
    loadedData.addAll(newData);
  }

  public Set<NHPIRecord> getLoadedData() {
    return loadedData;
  }

  public HashMap<String, Set<NHPIRecord>> getGroupedLoadedData() {
    HashMap<String, Set<NHPIRecord>> groupedRecords = new HashMap<>();
    for (NHPIRecord r : loadedData) {
      var name = r.getGeo();
      if (!groupedRecords.containsKey(name)) {
        groupedRecords.put(name, new HashSet<>());
      }
      groupedRecords.get(name).add(r);
    }

    return groupedRecords;
  }

  public Set<String> getLoadedGeos(){
    HashMap<String, Set<NHPIRecord>> groupedRecords = getGroupedLoadedData();
    Set<String> groupedGeos = groupedRecords.keySet();
    return groupedGeos;
  }
}
