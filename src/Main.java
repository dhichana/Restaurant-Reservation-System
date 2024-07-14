import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.Class.forName;

public class Main {
    String url = "jdbc:mysql://localhost:3306/db_java";
    String username = "dhich";
    String password = "dhich";

    public void insertData(String name, String city){
        String query = "INSERT INTO person_info(name, city) VALUES(?, ?) ";
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connect.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, city);
            preparedStatement.executeUpdate();
            System.out.println("Connection established and Data Inserted Successfully");

        } catch (SQLException e){
            System.out.println("Connection Failed. Can't Insert Data");
            e.printStackTrace();
        } finally {
            if (connect != null){
                try{
                    connect.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
//    try{
//        Class.forName("com.mysql.cj.jdbc.Driver");
//    } catch(ClassNotFoundException e) {
//        System.err.println("MySQL JDBC Driver not found");
//        e.printStackTrace();
//        return;
//    }


    public static void main(String[] args) {
        Main db = new Main();
        Scanner in = new Scanner(System.in);

        while(true){
            String name = in.next();
            String city = in.next();
            db.insertData(name, city);
            System.out.println("Do you want to continue? y/n");
            String choice = in.next();
            if(choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")){
                break;
            }
        }

        in.close();
    }
}
