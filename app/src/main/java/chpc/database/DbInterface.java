package chpc.database;

import java.sql.Connection;

public interface DbInterface {
  public Connection getConnection();
}
