package RIO.example.utils;

import RIO.example.readers.PropertyReader;

import java.sql.*;

public class Database {
    private static final Database INSTANCE = new Database(); // робимо статичне поле INSTANCE що містить єдиний екземпляр класу Database.
    private static Connection connection;

    private Database() {   //приватний конструктор що вказує на сінглтон  - ініціалізуємо зєднання з БД
        String url = PropertyReader.getConnectionUrlForPostgres();
        String user = PropertyReader.getUserForPostgres();
        String pass = PropertyReader.getPasswordForPostgres();

        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(String.format("SQL exception. Can not create connection. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not create connection");
        }
    }
    public static Database getInstance() {
        return INSTANCE;
    }
    public Connection getConnection() {
        return connection;
    }
    public void insertClient(String name) {
        String insertQuery = "INSERT INTO client (name) VALUES (?) RETURNING id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                System.out.println("Inserted client with id: " + id);
            } else {
                throw new RuntimeException("Error getting generated id after insert");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting client: " + e.getMessage());
        }
    }
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection: " + e.getMessage());
        }
    }
}
