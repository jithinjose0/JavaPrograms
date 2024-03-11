package first_java_project;

import java.util.Arrays;
import java.util.Comparator;

import java.util.Random;

class Employee {
//Declare the private variables for the employee's name, salary, and age.
	private String name;
	private double salary;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

public class EmployeeClass extends Thread {

	private Employee[] employees;

	public EmployeeClass() {
		employees = new Employee[10];
	}

	public void addRandomEmployees() {
		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			Employee emp = new Employee();
			emp.setName("Employee " + (i + 1));
			emp.setSalary(rand.nextInt(90000) + 10000); // Random salary between 50000 and 149999
			emp.setAge(rand.nextInt(40) + 20); // Random age between 20 and 59
			employees[i] = emp;
		}
	}

//    Thread method
	public void run() {
		addRandomEmployees();
	}

	public static void main(String[] args) {
		EmployeeClass employeeClass = new EmployeeClass();
		employeeClass.start(); // Start the thread

		try {
			employeeClass.join(); // Wait for the thread to finish
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Sorting by age
		Arrays.sort(employeeClass.employees, Comparator.comparingInt(Employee::getAge));
		System.out.println("Sorted by age:");
		for (Employee emp : employeeClass.employees) {
			System.out.println("Name: " + emp.getName() + ", Age: " + emp.getAge() + ", Salary: " + emp.getSalary());
		}

		// Sorting by salary
		Arrays.sort(employeeClass.employees, Comparator.comparingDouble(Employee::getSalary));
		System.out.println("\nSorted by salary:");
		for (Employee emp : employeeClass.employees) {
			System.out.println("Name: " + emp.getName() + ", Age: " + emp.getAge() + ", Salary: " + emp.getSalary());
		}

	}

}
