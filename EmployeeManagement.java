import java.io.*;
import java.util.*;

class Employee implements Serializable {
     int id;
     String name;
     String department;
     double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}

public class EmployeeManagement {

    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {

        loadData();

        while (true) {
            System.out.println("\n===== EMPLOYEE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Sort by Salary");
            System.out.println("7. Save & Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> searchEmployee();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 6 -> sortBySalary();
                case 7 -> {
                    saveData();
                    System.out.println("Data saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ADD
    static void addEmployee() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        employees.add(new Employee(id, name, dept, salary));
        System.out.println("Employee added!");
    }

    // VIEW
    static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee e : employees) {
            print(e);
        }
    }

    // SEARCH
    static void searchEmployee() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Employee e : employees) {
            if (e.id == id) {
                print(e);
                return;
            }
        }
        System.out.println("Not found!");
    }

    // UPDATE (NEW FEATURE)
    static void updateEmployee() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();

        for (Employee e : employees) {
            if (e.id == id) {
                sc.nextLine();

                System.out.print("New Name: ");
                e.name = sc.nextLine();

                System.out.print("New Department: ");
                e.department = sc.nextLine();

                System.out.print("New Salary: ");
                e.salary = sc.nextDouble();

                System.out.println("Employee updated!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    // DELETE
    static void deleteEmployee() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        employees.removeIf(e -> e.id == id);
        System.out.println("Employee deleted!");
    }

    // SORT BY SALARY (NEW FEATURE)
    static void sortBySalary() {
        employees.sort((a, b) -> Double.compare(b.salary, a.salary));

        System.out.println("Sorted by Salary:");
        viewEmployees();
    }

    // PRINT
    static void print(Employee e) {
        System.out.println("\nID: " + e.id);
        System.out.println("Name: " + e.name);
        System.out.println("Department: " + e.department);
        System.out.println("Salary: " + e.salary);
    }

    // SAVE FILE (NEW FEATURE)
    static void saveData() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (Exception e) {
            System.out.println("Error saving data!");
        }
    }

    // LOAD FILE (NEW FEATURE)
    static void loadData() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (ArrayList<Employee>) ois.readObject();
        } catch (Exception e) {
            employees = new ArrayList<>();
        }
    }
}