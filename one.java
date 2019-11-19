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
    state.executeQuery("create table Employee(Emp_ID INT, birthday String, first_name String, last_name String, gender varchar(5), workday String)row format delimited fields terminated by ',' lines terminated by '\n';");
    System.out.println("!!!!!!!!!!Running 2nd query!!!!!!!!!!");
    state.executeQuery("create table Salary(Emp_ID INT, start_date String, end_date String, salary String)row format delimited fields terminated by ',' lines terminated by '\n';");
  }
}