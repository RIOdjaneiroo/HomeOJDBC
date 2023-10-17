package RIO.example.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestConnectPreperedSt {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        String sqlTemplate = "SELECT name FROM worker WHERE id::TEXT LIKE ?";
        Connection connection = Database.getInstance().getConnection();
        try {

            for (int i = 1; i <= 9; i++) {
                PreparedStatement queryStatement = connection.prepareStatement(sqlTemplate);
                queryStatement.setString(1, i+"%");

                ResultSet searchResult = queryStatement.executeQuery();

                while (searchResult.next()) {
                    String workerName = searchResult.getString("name");
                    System.out.println("Worker name where " + i + ": " + workerName);
                }

                searchResult.close();
                queryStatement.close();
            }
        }catch (Exception e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }
}
