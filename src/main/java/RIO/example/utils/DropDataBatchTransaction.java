package RIO.example.utils;

import RIO.example.readers.SqlReader;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;

public class DropDataBatchTransaction {
    public static void main(String[] args) {
        String filePath = "src/main/java/RIO/example/SQL/Drop_init_Table.sql";  // шлях до файлу

        try {
            // отримуємо зєднання з базою даних
            Connection connection = Database.getInstance().getConnection();
            SqlReader sqlReader = new SqlReader();
            StringBuffer sqlBuffer = sqlReader.readSqlFromFile(filePath);
            // розділяємо запити по кожній порожній строці
            String[] queries = sqlBuffer.toString().split("\n\n");

            try {
                // встановлюємо ручне керовану транзакцію
                connection.setAutoCommit(false);

                // використовуємо batch update для додавання запитів до пакету
                try (Statement statement = connection.createStatement()) {
                    for (String query : queries) {
                        query = query.trim();
                        if (!query.isEmpty()) {
                            statement.addBatch(query);
                        }
                    }

                    // виконуємо пакетне оновлення
                    int[] updateCounts = statement.executeBatch();
                    System.out.println("Batch update executed. Update counts: " + Arrays.toString(updateCounts));
                }

                // застосовуємо зміни
                connection.commit();

            } catch (Exception e) {
                // відміна транзакції у разі помилки
                connection.rollback();
                System.err.println("Error executing SQL batch update. Transaction rolled back.");
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
