
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {

    static class Student {
        int rollNumber;
        String name;
        double javaMarks;
        double sqlMarks;
        double linuxMarks;

        Student(int rollNumber, String name,
                double javaMarks, double sqlMarks,
                double linuxMarks) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.javaMarks = javaMarks;
            this.sqlMarks = sqlMarks;
            this.linuxMarks = linuxMarks;
        }

        double getTotal() {
            return javaMarks + sqlMarks + linuxMarks;
        }

        double getAverage() {
            return getTotal() / 3.0;
        }

        String getGrade() {
            double average = getAverage();

            if (average >= 90) {
                return "A";
            } else if (average >= 80) {
                return "B";
            } else if (average >= 70) {
                return "C";
            } else if (average >= 60) {
                return "D";
            } else {
                return "Fail";
            }
        }

        void display() {
            System.out.println("\n--- Student Report ---");
            System.out.println("Roll Number: " + rollNumber);
            System.out.println("Name: " + name);
            System.out.println("Java Marks: " + javaMarks);
            System.out.println("SQL Marks: " + sqlMarks);
            System.out.println("Linux Marks: " + linuxMarks);
            System.out.println("Total Marks: " + getTotal() + " / 300");
            System.out.printf("Average: %.2f%%%n", getAverage());
            System.out.println("Grade: " + getGrade());
        }
    }

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static int readMarks(String subject) {
        while (true) {
            System.out.print("Enter " + subject + " marks (0-100): ");
            if (sc.hasNextDouble()) {
                double marks = sc.nextDouble();

                if (marks >= 0 && marks <= 100) {
                    return (int) marks;
                }
                System.out.println("Marks must be between 0 and 100.");
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter roll number: ");
        int rollNumber = sc.nextInt();
        sc.nextLine();

        for (Student student : students) {
            if (student.rollNumber == rollNumber) {
                System.out.println("Roll number already exists.");
                return;
            }
        }

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        double javaMarks = readMarks("Java");
        double sqlMarks = readMarks("SQL");
        double linuxMarks = readMarks("Linux");

        Student student = new Student(
            rollNumber, name, javaMarks, sqlMarks, linuxMarks
        );

        students.add(student);
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student student : students) {
            student.display();
        }
    }

    static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        int rollNumber = sc.nextInt();

        for (Student student : students) {
            if (student.rollNumber == rollNumber) {
                student.display();
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number from 1 to 4.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    System.out.println("Thank you for using the application!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
