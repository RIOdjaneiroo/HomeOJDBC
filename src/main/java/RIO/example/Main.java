package RIO.example;

import RIO.example.entities.LongestProject;
import RIO.example.entities.MaxProjectCountClient;
import RIO.example.entities.MaxSalaryWorker;
import RIO.example.entities.YoungestOldestWorkers;
import RIO.example.utils.DatabaseQueryService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome!");
        System.out.println("------------------");
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        // викликаємо метод для знаходження найтривалішого проекту
        List<LongestProject> longestProjects = databaseQueryService.findLongestProject();
        // виводимо результат роботи методу
        for (LongestProject longestProject : longestProjects) {
            System.out.println("Project ID: " + longestProject.getProjectId()+" Months: " + longestProject.getMonths());
        }
        System.out.println("------------------");
        // викликаємо метод для знаходження клієнтів і кількості проектів
        List<MaxProjectCountClient> maxProjectsClients = databaseQueryService.findMaxProjectsClientFromFile();
        for (MaxProjectCountClient client : maxProjectsClients) {
            System.out.println("Client Name: " + client.getName()+ " Project Count: " + client.getProjectCount());
        }
        System.out.println("------------------");

        // викликаємо метод для знаходження найбільщої зарплати працівника
        List<MaxSalaryWorker> maxSalaryWorkers = databaseQueryService.findMaxSalaryWorkerFromFile();
        for (MaxSalaryWorker maxSalaryWorker : maxSalaryWorkers) {
            System.out.println("Worker Name: " + maxSalaryWorker.getWorkerName() + " Levels Name: " + maxSalaryWorker.getLevelsName()
                    +  " Salary: " + maxSalaryWorker.getSalary());
        }
        System.out.println("------------------");

        // викликаємо метод для знаходження наймолодшоно і найстаршого працівника
        List<YoungestOldestWorkers> youngestOldestWorkersList = databaseQueryService.findYoungestOldestWorkers();
        for (YoungestOldestWorkers worker : youngestOldestWorkersList) {
            System.out.println("Type: " + worker.type + ", Name: " + worker.name + ", Birthday: " + worker.birthday);
        }
        System.out.println("------------------");

    }
}
