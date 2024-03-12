package first_java_project;

import java.util.Random;
import java.util.concurrent.Callable;

public class EmployeeData implements Callable<EmployeeDetails[]> {

	
	public EmployeeDetails[] call() throws Exception {
		return addRandomEmployees();
	}

	private EmployeeDetails[] addRandomEmployees() {
		EmployeeDetails[] employees = new EmployeeDetails[10];
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			EmployeeDetails emp = new EmployeeDetails();
			emp.setName("Employee " + (i + 1));
			emp.setSalary(random.nextInt(90000) + 10000); // Random salary between 10000 and 99999
			emp.setAge(random.nextInt(40) + 20); // Random age between 20 and 59
			employees[i] = emp;
		}
		return employees;
	}
}
