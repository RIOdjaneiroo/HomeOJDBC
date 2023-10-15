package RIO.example.utils;
import RIO.example.readers.SqlReader;
import java.sql.Connection;
import java.sql.Statement;

public class DropData {

    public static void main(String[] args) {
        String filePath = "src/main/java/RIO/example/SQL/Drop_init_Table.sql";  // шлях до файлу

        try {
            // отримуємо зєднання з базою даних
            Connection connection = Database.getInstance().getConnection();
            SqlReader sqlReader = new SqlReader();
            StringBuffer sqlBuffer = sqlReader.readSqlFromFile(filePath);
            // розділяємо запити по кожній порожній строці
            String[] queries = sqlBuffer.toString().split("\n\n");

            // иконуємо кожен запит
            for (String query : queries) {
                query = query.trim();
                if (!query.isEmpty()) {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute(query);
                        System.out.println("Query executed: " + query);
                    } catch (Exception e) {
                        System.err.println("Error executing SQL query: " + query);
                        e.printStackTrace();
                    }
                }
            }

            // закриваємо зєднання
            Database.getInstance().closeConnection();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

