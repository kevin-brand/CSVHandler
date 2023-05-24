public class EmployeeWorkRecord {
    private int employeeId;
    private String workDate;
    private double hoursWorked;
    private double hourlyWage;

    public EmployeeWorkRecord(int employeeId, String workDate, double hoursWorked, double hourlyWage) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.hoursWorked = hoursWorked;
        this.hourlyWage = hourlyWage;
    }

    public EmployeeWorkRecord(String[] data) {
        int employeeId = Integer.parseInt(data[0]);
        String workDate = data[1];
        double hoursWorked = Double.parseDouble(data[2]);
        double hourlyWage = Double.parseDouble(data[3]);

        this.employeeId = employeeId;
        this.workDate = workDate;
        this.hoursWorked = hoursWorked;
        this.hourlyWage = hourlyWage;
    }

    // Getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getWorkDate() {
        return workDate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    // Calculate the total pay
    public double calculatePay() {
        return hoursWorked * hourlyWage;
    }
}
