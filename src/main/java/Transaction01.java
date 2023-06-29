import java.sql.*;

public class Transaction01 {

    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "Password");
        Statement statement= con.createStatement();

        //TASK-1. Transfer amount of 1000 from account_nu:1234 to account_nu:5678
        // By default it is true,we have set this to false,now we are committing manually

        con.setAutoCommit(false);
        Savepoint sp= null;
        try {
            /*
            We have other queries
             */
            sp=con.setSavepoint();// rollback will start from here
            String query="Update accounts SET amount =amount+ ? WHERE account_nu=?";
            // Create preparedStatement
            PreparedStatement prs= con.prepareStatement(query);

            // First Update query starts here
            prs.setInt(1,-1000);
            prs.setInt(2,1234);
            prs.executeUpdate();
            // First Update query end  here
            // Since it is always true we will get exception
            // Then we will have problem to update second query

            if(true){
                throw new Exception();

            }

            // After exception this update will not run
            // Second Update query starts here
            prs.setInt(1,1000);
            prs.setInt(2,5678);
            prs.executeUpdate();
            // Second Update query end here
            con.commit();// commit mean Update
            prs.close();
            statement.close();
            con.close();

        }catch (Exception e){
            con.rollback(sp);// cancel all previous activities
        }



    }
}
