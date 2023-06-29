import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
        // Create connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "Password");
        // Create statement
        Statement statement = con.createStatement();



        //TASK-1. GET/SELECT  "country_name" from countries table with ID between 5 and 10



        String query1="SELECT country_name FROM countries WHERE id BETWEEN 5 AND 10";
        boolean sql1=statement.execute(query1);
        System.out.println("sql1 :" +sql1);

        ResultSet resultSet=statement.executeQuery(query1);

        //System.out.println(resultSet.next());
        resultSet.next();
        System.out.println(resultSet.getString("country_name"));
        resultSet.next();
        System.out.println(resultSet.getString("country_name"));

       // resultSet.next();
       // System.out.println(resultSet.getString("country_name"));
        while(resultSet.next()){
             System.out.println(resultSet.getString("country_name"));

             //ResultSet method() has method such first()-it will give us first value
            //  last()-it will give us last value,next()-it will give us next value
            // There is no Method do iterate backward

            //TASK - 2: Get "phone_code" and "country_name" from countries table,
            // whose phone code is greater than 200

            System.out.println("**************** TASK 3 ***********************");
            String query2= "SELECT phone_code,country_name FROM countries WHERE phone_code>500";
            statement.executeQuery(query2);
            ResultSet resultSet2=statement.executeQuery(query2);
            while(resultSet2.next()){
                System.out.println(resultSet2.getInt("phone_code")+" -- "+
                        resultSet2.getString("country_name"));
            }
            //TASK-3. Get all information about the developers whose salary is lowest
            System.out.println("**************** TASK 3 ***********************");

            String query3="SELECT * FROM developers WHERE salary=(SELECT MIN(salary)FROM developers)";
            ResultSet resultSet3=statement.executeQuery(query3);
            while(resultSet3.next()){
                System.out.println(resultSet3.getInt("id")+ " -- "+ resultSet3.getString("name") +
                        " -- " + resultSet3.getString("prog_lang") + " -- " +resultSet3.getDouble("salary"));
            }
            //TASK - 4 : Display students' name and grade whose grades are higher than passing grade of departments.
            System.out.println("**************** TASK  4 ***********************");

            String query4="SELECT name,grade FROM students WHERE grade>( SELECT AVG(pass_grade) FROM departments)";
            ResultSet resultSet4=statement.executeQuery(query4);
            while(resultSet4.next()){
                System.out.println(resultSet4.getString("name") + " -- " + resultSet4.getInt("grade"));


            }
            statement.close();
            con.close();


        }


    }

        }


