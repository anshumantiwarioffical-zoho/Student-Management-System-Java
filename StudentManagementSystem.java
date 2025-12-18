// Student Management System (Console Based Java Project)

// Importing utility package for ArrayList, Scanner, Iterator
import java.util.*;

// Importing IO package for File handling and Serialization
import java.io.*;

// Student class represents one student record
class Student implements Serializable {

    int id;        // Student ID
    String name;   // Student Name
    int marks;     // Student Marks

    // Constructor to initialize student object
    Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Converts object data into readable format
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

// Main class
public class StudentManagementSystem {

    // ArrayList to store student objects
    static ArrayList<Student> students = new ArrayList<>();

    // Scanner for user input
    static Scanner sc = new Scanner(System.in);

    // File name to store student data
    static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {

        // Load saved data from file if available
        loadFromFile();

        int choice = 0;

        // Menu loop
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Save & Exit");
            System.out.print("Enter choice: ");

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter numbers only.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    saveToFile();
                    System.out.println("Data saved. Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);
    }

    // Method to add a student
    static void addStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            students.add(new Student(id, name, marks));
            System.out.println("Student added successfully.");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            sc.nextLine();
        }
    }

    // Method to view all students
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Method to delete a student by ID
    static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        Iterator<Student> it = students.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Student s = it.next();
            if (s.id == id) {
                it.remove();
                found = true;
                System.out.println("Student deleted successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Save student data to file
    static void saveToFile() {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(students);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error while saving data.");
        }
    }

    // Load student data from file
    static void loadFromFile() {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));
            students = (ArrayList<Student>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}
