/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package chpc;

import java.util.Properties;

import chpc.models.Db;
import chpc.views.MainUI;

public class App {
  public static void main(String[] args) {
    String uri = "jdbc:postgresql://localhost:5432/nhpi";
    Properties props = new Properties();
    props.setProperty("user", "admin");
    props.setProperty("password", "admin");
    props.setProperty("ssl", "false");
    Db.initializeDb(uri, props);

    MainUI.getInstance();
  }
}