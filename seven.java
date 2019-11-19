import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
public class Hive_java{
  private static String driver = "org.apache.hive.jdbc.HiveDriver";  //driver used for hiveserver2
  public static void main(String[] args) throws SQLException {
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }
    Connection connect = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");   //connecting to default database
    Statement state = connect.createStatement();
    System.out.println("!!!!!!!!!!Running 1st query!!!!!!!!!!");
    state.executeQuery("set hive.exec.dynamic.partition.mode=nonstrict");
    System.out.println("!!!!!!!!!!Running 2nd query!!!!!!!!!!");
    state.executeQuery("create table year_part(AthletName String, Country String, Closing_Date String, Sport String, Age Int, Gold_medals INT, Silver_medals Int, Bronze_medals Int, Total_medals Int) PARTITIONED BY(year Int);");
    System.out.println("!!!!!!!!!!Running 3rd query!!!!!!!!!!");
    state.executeQuery("INSERT OVERWRITE TABLE year_part PARTITION(year)
			SELECT AthletName, Country, Closing_Date, Sport, Age, Gold_medals, Silver_medals, Bronze_medals, Total_medals from  Olympic;");
  }
}