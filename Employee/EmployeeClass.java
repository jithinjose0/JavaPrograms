package first_java_project;


import java.util.Arrays;
import java.util.Comparator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EmployeeClass {

	private EmployeeDetails[] employees;

	public void display(String emp) {
		System.out.println(emp);

	}

	public void sortByAge() {
		Arrays.sort(employees, Comparator.comparingInt(EmployeeDetails::getAge));
		System.out.println("Sorted by age:");
		for (EmployeeDetails emp : employees) {
			String empl = "Name: " + emp.getName() + ", Age: " + emp.getAge() + ", Salary: " + emp.getSalary();
			display(empl);
		}
	}

	public void sortBySalary() {
		Arrays.sort(employees, Comparator.comparingDouble(EmployeeDetails::getSalary));
		System.out.println("\nSorted by salary:");
		for (EmployeeDetails emp : employees) {
			String emplo = "Name: " + emp.getName() + ", Age: " + emp.getAge() + ", Salary: " + emp.getSalary();
			display(emplo);
		}
	}

	public static void main(String[] args) {
		EmployeeClass employeeClass = new EmployeeClass();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<EmployeeDetails[]> future = executorService.submit(new EmployeeData());

		try {
			employeeClass.employees = future.get(); 
		} catch (Exception e) {
			e.printStackTrace();
		}

		executorService.shutdown();

		employeeClass.sortByAge();
		employeeClass.sortBySalary();

	}

}
