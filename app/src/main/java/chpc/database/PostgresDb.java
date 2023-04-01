package chpc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * A singleton class that manage Database connection
 */
public class PostgresDb implements DbInterface {
  private static PostgresDb instance;
  private static Connection conn;
  private static String postgresDriver = "jdbc:postgresql://";

  /**
   * private constructor to make the Db class a Singleton
   */
  private PostgresDb() {
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
  public static void initializeDb(String url, Properties properties) {
    try {
      conn = DriverManager.getConnection(PostgresDb.postgresDriver + url, properties);
      PostgresDb.instance = new PostgresDb();
    } catch (Exception e) {
      System.out.println("Could not connect to DB! Terminating application");
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  public static PostgresDb getInstance() {
    if (PostgresDb.instance != null) {
      return PostgresDb.instance;
    }
    throw new Error("DB has not been initialized yet");
  }

  /**
   * Get the connection for the current Db, return null if Db has not been
   * initialized
   * 
   * @return Connection object
   */
  public Connection getConnection() {
    if (PostgresDb.instance == null) {
      System.out.println("No connection has been established! Please initilize Db.");
    }

    return PostgresDb.conn;
  }

  public static void main(String[] args) {
    String uri = "localhost:5432/nhpi";
    Properties props = new Properties();
    props.setProperty("user", "admin");
    props.setProperty("password", "admin");
    props.setProperty("ssl", "false");
    PostgresDb.initializeDb(uri, props);
    Connection conn = PostgresDb.getInstance().getConnection();
    System.out.println(conn);
  }
}
