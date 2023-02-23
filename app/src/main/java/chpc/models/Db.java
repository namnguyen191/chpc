package chpc.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * A singleton class that manage Database connection
 */
public class Db {
  private static Connection conn;

  /**
   * private constructor to make the Db class a Singleton
   */
  private Db() {
  }

  /**
   * Initialize the Db using the provided Uri and Properties. This must be called
   * at least once at some point in the application. If the connection failed,
   * exit the application.
   * 
   * @param uri        the Uri of the Db to connect to, for example:
   *                   jdbc:postgresql://localhost:5432/nhpi
   * @param properties any extra properties, could be null
   */
  public static void initializeDb(String uri, Properties properties) {
    try {
      conn = DriverManager.getConnection(uri, properties);
    } catch (Exception e) {
      System.out.println("Could not connect to DB! Terminating application");
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  /**
   * Get the connection for the current Db, return null if Db has not been
   * initialized
   * 
   * @return Connection object
   */
  public static Connection getConnection() {
    if (Db.conn == null) {
      System.out.println("No connection has been established! Please initilize Db.");
    }

    return Db.conn;
  }

  public static void main(String[] args) {
    String uri = "jdbc:postgresql://localhost:5432/nhpi";
    Properties props = new Properties();
    props.setProperty("user", "admin");
    props.setProperty("password", "admin");
    props.setProperty("ssl", "false");
    Db.initializeDb(uri, props);
    Connection conn = Db.getConnection();
    System.out.println(conn);
  }
}
