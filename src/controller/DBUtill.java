package controller;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtill {
  public static Connection getConnection() {
    String filePath = "db.properties";
    Properties pt = new Properties();
    Connection conn = null;
    try (FileReader reader = new FileReader(filePath)) {
      pt.load(reader);
      Class.forName("oracle.jdbc.driver.OracleDriver");
      String url = pt.getProperty("url");
      String user = pt.getProperty("user");
      String pw = pt.getProperty("pw");
      conn = DriverManager.getConnection(url, user, pw);
    } catch (IOException | ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }
}
