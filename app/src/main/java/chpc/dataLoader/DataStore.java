package chpc.dataLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DataStore {
  private static DataStore instance;

  private static HashMap<String, List<NHPIRecord>> loadedDataGroup;

  private DataStore() {
    loadedDataGroup = new HashMap<String, List<NHPIRecord>>();
  }

  public static DataStore getInstance() {
    if (instance == null) {
      instance = new DataStore();
    }

    return instance;
  }

  public void loadData(String groupName, List<NHPIRecord> newData) {
    loadedDataGroup.put(groupName, newData);
  }

  public List<NHPIRecord> getLoadedDataForGroup(String groupName) {
    return loadedDataGroup.get(groupName);
  }

  public Set<String> getAllLoadedDataGroups() {
    return loadedDataGroup.keySet();
  }

  public boolean checkGroupAlreadyLoaded(String groupName) {
    return loadedDataGroup.containsKey(groupName);
  }

  public void removeGroupData(String groupName) {
    loadedDataGroup.remove(groupName);
  }

  // public HashMap<String, Set<NHPIRecord>> getGroupedLoadedData() {
  // HashMap<String, Set<NHPIRecord>> groupedRecords = new HashMap<>();
  // for (NHPIRecord r : loadedData) {
  // var name = r.getGeo();
  // if (!groupedRecords.containsKey(name)) {
  // groupedRecords.put(name, new HashSet<>());
  // }
  // groupedRecords.get(name).add(r);
  // }

  // return groupedRecords;
  // }
}
