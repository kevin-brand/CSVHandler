import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class App {
    public static void main(String[] args) throws Exception {
        EmployeeManager employeeManager = new EmployeeManager();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Type \"help\" to get a list of available commands!");
        while (true) {
            System.out.print("Enter a command (or 'exit' to quit): ");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("help")) {
                DisplayHelp();
            }

            if (command.equalsIgnoreCase("exit")) {
                break; // Exit the loop if the command is "exit"
            }

            if (command.equalsIgnoreCase("load")) {
                LoadCSV(employeeManager);
            }

            if (command.equalsIgnoreCase("add")) {
                System.out.print("Please enter [ID,Date,Work Time,Hourly Wage]: ");
                command = scanner.nextLine();

                String[] values = command.split(",");
                String id = values[0];
                String date = values[1];
                String workTime = values[2];
                String hourlyWage = values[3];

                AddEmployeeWorkRecordToCSV(id, date, workTime, hourlyWage);
            }

            if (command.equals("display")) {
                List<Employee> employees = employeeManager.getAllEmployeesReadOnly();

                for (Employee employee : employees) {
                    DisplayEmployeeInformation(employee);
                }
            }
        }

        scanner.close();
    }

    private static void DisplayEmployeeInformation(Employee employee) {
        System.out.println("");
        List<EmployeeWorkRecord> workRecords = employee.getAllWorkRecordsReadOnly();
        System.out.println("ID\tDatum\t\tArbeitszeit in Stunden\tStundenlohn");
        for (EmployeeWorkRecord workRecord : workRecords) {
            DisplayEmployeeWorkRecord(workRecord);
        }
        System.out.println("");

        System.out.println("Arbeitszeit (Gesamt)\tLohn (Gesamt)\t\tLohn (Mittelwert)");
        String totalWorkHours = Integer.toString(employee.getTotalWorkHours());
        String totalPay = Integer.toString(employee.getTotalPay());
        String averageHourlyWage = Double.toString(employee.getAverageHourlyWage());
        System.out.println(totalWorkHours + "\t\t\t" + totalPay + "\t\t\t" + averageHourlyWage);
        System.out.println("");
        System.out.println("-------------------------------------------------------------------");
    }

    private static void DisplayHelp() {
        System.out.println("exit\t\t-\t\tquits the programm");
        System.out.println("load\t\t-\t\tloads the csv");
        System.out.println(
                "display\t\t-\t\tdisplays all employee work records, their total work hours, total pay and average hourly wage");
        System.out.println("add\t\t-\t\tadd another work record");
    }

    private static void AddEmployeeWorkRecordToCSV(String id, String date, String workTime, String hourlyWage) {
        String filePath = "C:\\Users\\kevin\\Desktop\\kosten.csv"; // Specify the file path
        String csvSeperator = ";";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = id + csvSeperator + date + csvSeperator + workTime + csvSeperator + hourlyWage;
            writer.write(line);
            writer.newLine();
            System.out.println("Line written to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private static void LoadCSV(EmployeeManager employeeManager) {
        String csvFile = "C:\\Users\\kevin\\Desktop\\kosten.csv";
        String line;
        String csvSeperator = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int i = -1;
            while ((line = br.readLine()) != null) {
                i++;
                if (i == 0)
                    continue;

                String[] data = line.split(csvSeperator);
                EmployeeWorkRecord workRecord = new EmployeeWorkRecord(data);
                employeeManager.addWorkRecordToEmployeeAndCreateIfNew(workRecord);

                System.out.println("Work record of employee " + Integer.toString(workRecord.getEmployeeId())
                        + " was loaded succesfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void DisplayEmployeeWorkRecord(EmployeeWorkRecord workRecord) {

        String id = Integer.toString(workRecord.getEmployeeId());
        String date = workRecord.getWorkDate();
        String hoursWorked = Double.toString(workRecord.getHoursWorked());
        String hourlyWage = Double.toString(workRecord.getHourlyWage());

        System.out.println(id + "\t" + date + "\t" + hoursWorked + "\t\t\t" + hourlyWage);
    }
}
