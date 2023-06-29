import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","Password");
        Statement statement=con.createStatement();

        //TASK-1. Update pass_grade to 475 of Mathematics department (use PreparedStatement)
        System.out.println("************ TASK-1 **************");

        // Normal String query
       // String query1="UPDATE department SET pass_grade=475 WHERE department LIKE 'mathematics ";
        //String query2="UPDATE department SET pass_grade=475 WHERE department LIKE 'literature ";
        // Parameterized Query

        String query1="UPDATE departments SET pass_grade= ? WHERE department ILIKE ?";
        // Create prepared statement
        PreparedStatement prs=con.prepareStatement(query1);
        // Set values for parameters
        prs.setInt(1,475);
        prs.setString(2,"mathematics");
        // Execute the prepared statement
       int numOfUpdatedRows= prs.executeUpdate();
        System.out.println("Updated data num"+ numOfUpdatedRows );

        //TASK-1. Update pass_grade to 455 of literature department (use PreparedStatement)

        System.out.println("************ TASK-2 **************");
        prs.setInt(1,455);
        prs.setString(2,"literature");
        int numOfUpdatedRows2=prs.executeUpdate();
        System.out.println("Updated: "+ numOfUpdatedRows2 );
        prs.close();






    }
}
