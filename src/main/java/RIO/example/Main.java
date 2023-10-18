package RIO.example;

import RIO.example.entities.LongestProject;
import RIO.example.entities.MaxProjectCountClient;
import RIO.example.entities.MaxSalaryWorker;
import RIO.example.entities.YoungestOldestWorkers;
import RIO.example.utils.DatabaseQueryService;
import RIO.example.utils.Database;
import RIO.example.utils.DbPopulateProjectPST;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome!");
        System.out.println("------------------");

        Database database = Database.getInstance();

        // вставка даних для кожного клієнта
        database.insertClient( "Taras");
        database.insertClient( "Konsrantin");
        database.insertClient( "Adesaniy");
        database.insertClient( "Mr.Sergio");
        database.insertClient( "Elton");

        DbPopulateProjectPST projectDAO = new DbPopulateProjectPST(database);

        // Вставка даних про проекти
        projectDAO.insertProject("Filosophi", 1, Date.valueOf("2022-05-15"), Date.valueOf("2022-06-15"));
        projectDAO.insertProject("Psihology", 2, Date.valueOf("2022-06-25"), Date.valueOf("2022-07-05"));
        projectDAO.insertProject("ProjectGOIT", 3, Date.valueOf("2022-10-20"), Date.valueOf("2022-12-21"));
        projectDAO.insertProject("Mathematica", 4, Date.valueOf("2022-11-10"), Date.valueOf("2023-02-11"));
        projectDAO.insertProject("Geopoliticy", 5, Date.valueOf("2022-10-01"), Date.valueOf("2023-03-15"));
        projectDAO.insertProject("3D printing", 1, Date.valueOf("2023-01-09"), Date.valueOf("2023-05-13"));
        projectDAO.insertProject("3D Bilding", 2, Date.valueOf("2023-01-17"), Date.valueOf("2023-08-30"));
        projectDAO.insertProject("STARTAP 1", 3, Date.valueOf("2023-02-27"), Date.valueOf("2023-09-01"));
        projectDAO.insertProject("KIBER_SPORT", 4, Date.valueOf("2023-03-16"), Date.valueOf("2023-05-07"));
        projectDAO.insertProject("Project B", 5, Date.valueOf("2023-04-19"), Date.valueOf("2023-08-28"));

        database.closeConnection();







//        DatabaseQueryService databaseQueryService = new DatabaseQueryService();
//
//        // викликаємо метод для знаходження найтривалішого проекту
//        List<LongestProject> longestProjects = databaseQueryService.findLongestProject();
//        // виводимо результат роботи методу
//        for (LongestProject longestProject : longestProjects) {
//            System.out.println("Project ID: " + longestProject.getProjectId()+" Months: " + longestProject.getMonths());
//        }
//        System.out.println("------------------");
//        // викликаємо метод для знаходження клієнтів і кількості проектів
//        List<MaxProjectCountClient> maxProjectsClients = databaseQueryService.findMaxProjectsClientFromFile();
//        for (MaxProjectCountClient client : maxProjectsClients) {
//            System.out.println("Client Name: " + client.getName()+ " Project Count: " + client.getProjectCount());
//        }
//        System.out.println("------------------");
//
//        // викликаємо метод для знаходження найбільщої зарплати працівника
//        List<MaxSalaryWorker> maxSalaryWorkers = databaseQueryService.findMaxSalaryWorkerFromFile();
//        for (MaxSalaryWorker maxSalaryWorker : maxSalaryWorkers) {
//            System.out.println("Worker Name: " + maxSalaryWorker.getWorkerName() + " Levels Name: " + maxSalaryWorker.getLevelsName()
//                    +  " Salary: " + maxSalaryWorker.getSalary());
//        }
//        System.out.println("------------------");
//
//        // викликаємо метод для знаходження наймолодшоно і найстаршого працівника
//        List<YoungestOldestWorkers> youngestOldestWorkersList = databaseQueryService.findYoungestOldestWorkers();
//        for (YoungestOldestWorkers worker : youngestOldestWorkersList) {
//            System.out.println("Type: " + worker.type + ", Name: " + worker.name + ", Birthday: " + worker.birthday);
//        }
//        System.out.println("------------------");

    }
}
