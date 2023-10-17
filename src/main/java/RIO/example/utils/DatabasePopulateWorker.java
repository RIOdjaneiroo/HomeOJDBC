package RIO.example.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabasePopulateWorker {
    public static void main(String[] args) {
        Connection connection = Database.getInstance().getConnection();

        if (connection != null) {
            try {
                String sqlTemplate = "INSERT INTO worker (name, birthday, levels_id, salary) VALUES (?, CAST(? AS DATE), ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlTemplate);

                preparedStatement.setString(1, "Johnatan Week");
                preparedStatement.setString(2, "1989-05-16");
                preparedStatement.setInt(3, 3);
                preparedStatement.setInt(4, 2000);
                preparedStatement.addBatch();

                preparedStatement.setString(1, "Alina Karakova");
                preparedStatement.setString(2, "1987-06-22");
                preparedStatement.setInt(3, 2);
                preparedStatement.setInt(4, 1500);
                preparedStatement.addBatch();

                int[] results = preparedStatement.executeBatch();
                System.out.println("Rows affected: " + results.length);

                preparedStatement.close();
            } catch (Exception e) {
                System.err.println("Error executing SQL script: " + e.getMessage());
            }
        } else {
            System.err.println("Database connection failed");
        }
    }
}

