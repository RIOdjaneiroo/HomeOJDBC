package RIO.example.utils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
public class DbPopulateProjectPST {
    private Database database;

    public DbPopulateProjectPST(Database database) {
        this.database = database;
    }

    public void insertProject(String projectName, long clientId, Date startDate, Date finishDate) {
        String insertQuery = "INSERT INTO project (project_name, client_id, start_date, finish_date) VALUES (?, ?, CAST(? AS DATE), CAST(? AS DATE))";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(insertQuery)) {
            preparedStatement.setString(1, projectName);
            preparedStatement.setLong(2, clientId);
            preparedStatement.setDate(3, startDate);
            preparedStatement.setDate(4, finishDate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting project: " + e.getMessage());
        }
    }
}
