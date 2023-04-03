package chpc.dataLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DataStore {
  private static DataStore instance;

  private static HashMap<String, List<NHPIRecord>> loadedDataGroup;

  private static List<GeoFromTo> loadedOptions;

  private static HashMap<String, List<NHPIRecord>> loadedPredictions;

  private DataStore() {
    loadedDataGroup = new HashMap<String, List<NHPIRecord>>();
    loadedOptions = new ArrayList<GeoFromTo>();
    loadedPredictions = new HashMap<String, List<NHPIRecord>>();
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

  public void loadPrediction(String title, List<NHPIRecord> predictedData) {
    loadedPredictions.put(title, predictedData);
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

  public void loadGeoFromTos(GeoFromTo setOfParams) {
    loadedOptions.add(setOfParams);
  }

  public List<NHPIRecord> getPredictedData(String title) {
    return loadedPredictions.get(title);
  }

  public Set<String> getPredictionTitles() {
    return loadedPredictions.keySet();
  }
}
