import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Employee {
    private int id;
    private List<EmployeeWorkRecord> workRecords = new ArrayList<>();

    public Employee(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public int getTotalWorkHours() {
        int totalWorkHours = 0;

        for (EmployeeWorkRecord workRecord : workRecords) {
            totalWorkHours += workRecord.getHoursWorked();
        }

        return totalWorkHours;
    }

    public int getTotalPay() {
        int totalPay = 0;

        for (EmployeeWorkRecord workRecord : workRecords) {
            totalPay += workRecord.calculatePay();
        }

        return totalPay;
    }

    public List<EmployeeWorkRecord> getAllWorkRecordsReadOnly() {
        return Collections.unmodifiableList(workRecords);
    }

    public double getAverageHourlyWage() {
        return getTotalPay() / getTotalWorkHours();
    }

    public void addWorkRecord(EmployeeWorkRecord workRecord) {
        workRecords.add(workRecord);
    }
}
