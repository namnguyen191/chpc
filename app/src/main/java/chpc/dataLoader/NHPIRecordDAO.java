package chpc.dataLoader;

import java.sql.SQLException;
import java.util.List;

public interface NHPIRecordDAO {
  public List<NHPIRecord> getAllRecords() throws SQLException;

  public List<NHPIRecord> getRecordsByGeoAndDateRange(Geo select_geo) throws SQLException;

  public List<String> getAllGeos() throws SQLException;

  public void resetAndSeed() throws SQLException;
}
