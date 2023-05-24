import java.util.Hashtable;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class EmployeeManager {
    private Hashtable<Integer, Employee> employees = new Hashtable<Integer, Employee>();

    public void addEmployee(Employee employee) {
        if (employees.get(employee.getID()) != null) {
            return;
        }

        employees.put(employee.getID(), employee);
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public List<Employee> getAllEmployeesReadOnly() {
        List<Employee> employeeList = new ArrayList<>(employees.values());
        return Collections.unmodifiableList(employeeList);
    }

    public void addWorkRecordToEmployeeAndCreateIfNew(EmployeeWorkRecord workRecord) {
        if (employees.get(workRecord.getEmployeeId()) != null) {
            employees.get(workRecord.getEmployeeId()).addWorkRecord(workRecord);
            return;
        }

        Employee employee = new Employee(workRecord.getEmployeeId());
        employee.addWorkRecord(workRecord);
        employees.put(employee.getID(), employee);
    }
}
