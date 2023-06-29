import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Step 1-Register driver-optional

        Class.forName("org.postgresql.Driver");
        //Step 2-Create Connection-to get connected to Database
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user",
                "Password");

        // Step 3- Create Statement-to execute SQL queries
        Statement statement=con.createStatement();
        // to test if we have created connection to Database
        System.out.println("Connected successfully");

        // Step 4-Execute SQL queries
        //TASK: create a table named "employee" with column names of :
        // "employee_id", "employee_name", "salary"

       boolean sql1 = statement.execute("CREATE TABLE employee (employee_id INT, employee_name VARCHAR(50), salary REAL)");
        System.out.println("sql1: +sql1");

        // Execute method returns boolean value and can be used for DDL(data definition language)
        // or DQl(Data Query language)
        // When is used with DDL will return false
        // When is used with DQL(SELECT) will return true


        //TASK 2: add Varchar (20) column name "city" to employee table
        String query="ALTER TABLE employee ADD COLUMN city VARCHAR(20)";
        boolean sql2=statement.execute(query);
        System.out.println("sql2 :"+sql2);

        //TASK 3: Delete employee table from SCHEMA
        String query1="DROP TABLE employee";
        statement.execute(query1);

        // Step 5- Close connection and statement
        statement.close();
        con.close();



    }
}
