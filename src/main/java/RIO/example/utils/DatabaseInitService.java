package RIO.example.utils;

import RIO.example.readers.SqlReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {

    public static void main(String[] args) {
        String filePath = "src/main/java/RIO/example/SQL/init_db.sql";  // Шлях до SQL-файлу

        try {
            // отримуємо connection -зєднання з базою даних
            Connection connection = Database.getInstance().getConnection();

            // створюємо обєкт-sqlReader для читання файлу
            SqlReader sqlReader = new SqlReader();
            StringBuffer sqlBuffer = sqlReader.readSqlFromFile(filePath);

            // сплітимо запити по кожній порожній строці
            String[] queries = sqlBuffer.toString().split("\n\n");

            try {
                // відкриваємо транзакцію
                connection.setAutoCommit(false);

                // виконуємо кожен запит
                for (String query : queries) {
                    query = query.trim();
                    if (!query.isEmpty()) {
                        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                            preparedStatement.executeUpdate();
                            //System.out.println("Query executed: " + query);
                        } catch (SQLException e) {
                            System.err.println("Error executing SQL query: " + query);
                            e.printStackTrace();
                        }
                    }
                }

                // завершуємо транзакцію комітимо (підтверджуємо зміни)
                connection.commit();

            } catch (SQLException e) {
                // якщо виникає помилка, відкатуємо транзакцію
                connection.rollback();
                System.err.println("Transaction rolled back.");
                e.printStackTrace();
            } finally {
                // закриваємо зєднання
                connection.close();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
