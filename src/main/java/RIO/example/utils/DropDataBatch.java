package RIO.example.utils;

import RIO.example.readers.SqlReader;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;

public class DropDataBatch {
    public static void main(String[] args) {
        String filePath = "src/main/java/RIO/example/SQL/Drop_init_Table.sql";  // шлях до файлу

        try {
            // отримуємо зєднання з базою даних
            Connection connection = Database.getInstance().getConnection();
            SqlReader sqlReader = new SqlReader();
            StringBuffer sqlBuffer = sqlReader.readSqlFromFile(filePath);
            // розділяємо запити по кожній порожній строці
            String[] queries = sqlBuffer.toString().split("\n\n");

            // Використовуємо batch update для виконання запитів
            try (Statement statement = connection.createStatement()) {
                for (String query : queries) {
                    query = query.trim();
                    if (!query.isEmpty()) {
                        statement.addBatch(query);
                    }
                }

                // Виконуємо пакетне оновлення
                int[] updateCounts = statement.executeBatch();
                System.out.println("Batch update executed. Update counts: " + Arrays.toString(updateCounts));
            } catch (Exception e) {
                System.err.println("Error executing SQL batch update");
                e.printStackTrace();
            }

            // закриваємо зєднання
            Database.getInstance().closeConnection();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
