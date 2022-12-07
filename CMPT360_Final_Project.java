/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.cmpt360_final_project;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author vrfvega
 */
public class CMPT360_Final_Project {

    private static final String INSTRUCTOR_PATH = "C:\\Users\\vrfvega\\Documents\\NetBeansProjects\\CMPT360_Final_Project\\src\\main\\java\\com\\mycompany\\cmpt360_final_project\\instructor.txt";
    private static final String DEPARTMENT_PATH = "C:\\Users\\vrfvega\\Documents\\NetBeansProjects\\CMPT360_Final_Project\\src\\main\\java\\com\\mycompany\\cmpt360_final_project\\department.txt";

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

    public static ArrayList<Instructor> readInstructorsFromFile() throws IOException {
        File f = new File(INSTRUCTOR_PATH);
        ArrayList<Instructor> instructorList = new ArrayList<>();
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] items = line.split(",");

            int id = Integer.parseInt(items[0]);
            String name = items[1];
            String department = items[2];

            Instructor newInstructor = new Instructor(id, name, department);
            instructorList.add(newInstructor);
        }
        sc.close();
        return instructorList;
    }

    public static ArrayList<Department> readDepartmentsFromFile() throws IOException {
        File f = new File(DEPARTMENT_PATH);
        ArrayList<Department> departmentList = new ArrayList<>();
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] items = line.split(",");

            String name = items[0];
            String location = items[1];
            int budget = Integer.parseInt(items[2]);

            Department newDepartment = new Department(name, location, budget);
            departmentList.add(newDepartment);
        }
        sc.close();
        return departmentList;
    }

    public static void writeToFile(String path, String line) throws IOException {
        File f = new File(path);

        FileWriter fw = new FileWriter(f, true);
        PrintWriter output = new PrintWriter(fw);

        output.println(line);
        output.close();
    }

    public static void instructor_Info(Scanner input) throws IOException {
        ArrayList<Instructor> instructorsList = readInstructorsFromFile();
        ArrayList<Department> departmentsList = readDepartmentsFromFile();

        boolean instructorNotFound = true;
        String dept = "";

        while (instructorNotFound) {
            System.out.print("Enter the instructor ID: ");
            int input_id = input.nextInt();

            for (Instructor instructor : instructorsList) {
                if (instructor.getID() == input_id) {
                    System.out.println(instructor);
                    dept = instructor.getAffiliated_Department();
                    instructorNotFound = false;
                    break;
                }
            }

            if (instructorNotFound) {
                System.out.println("The ID does not appear in the file.");
            }
        }

        for (Department department : departmentsList) {
            if (department.getName().equals(dept)) {
                System.out.println("Location: " + department.getLocation() + "\n");
                break;
            }
        }
    }

    public static void insert_Instructor(Scanner input) throws IOException {
        ArrayList<Instructor> instructorsList = readInstructorsFromFile();
        ArrayList<Department> departmentsList = readDepartmentsFromFile();

        int id = 0;
        String department = "";
        boolean uniqueID = false;
        boolean departmentNotFound = true;
        
        while (!uniqueID) {
            System.out.print("Enter instructor ID: ");
            id = input.nextInt();

            for (Instructor instructor : instructorsList) {
                if (instructor.getID() == id) {
                    System.out.println("The instructor id is already present.");
                    uniqueID = false;
                    break;
                } else {
                    uniqueID = true;
                }
            }
        }

        System.out.print("Enter instructor name: ");
        String fname = input.next();
        String lname = input.next();
        String name = fname + " " + lname;
        
        while (departmentNotFound) {
            System.out.print("Enter affiliated department: ");
            department = input.next().toUpperCase();

            for (Department dept : departmentsList) {
                if (dept.getName().equals(department)) {
                    departmentNotFound = false;;
                    break;
                } else {
                    departmentNotFound = true;
                }
            }

            if (departmentNotFound) {
                System.out.println("The department does not exist.");
            }
        }

        String outputText = id + "," + name + "," + department;
        writeToFile(INSTRUCTOR_PATH, outputText);
        
        System.out.println();
    }

    public static void insert_Department(Scanner input) throws IOException {
        ArrayList<Department> departmentsList = readDepartmentsFromFile();

        String department_name = "";
        String location = "";
        int budget = 0;

        boolean budgetValidNumber = false;
        boolean nameConditionsMet = false;
        boolean uniqueDeptName = false;
        boolean deptNameValidLength;
        boolean locationValidLength = false;

        while (!nameConditionsMet) {
            System.out.print("Enter department name: ");
            department_name = input.next().toUpperCase();

            for (Department department : departmentsList) {
                if (!department.getName().equals(department_name)) {
                    uniqueDeptName = true;
                } else {
                    uniqueDeptName = false;
                    break;
                }
            }

            if (!uniqueDeptName) {
                System.out.println("The department already exists.");
            }

            if (department_name.length() != 4) {
                System.out.println("The department name should be 4 letters.");
                continue;
            } else {
                deptNameValidLength = true;
            }

            if (deptNameValidLength && uniqueDeptName) {
                nameConditionsMet = true;
            }
        }

        while (!locationValidLength) {
            System.out.print("Enter location: ");
            location = input.next().toUpperCase();

            if (location.length() != 3) {
                System.out.println("The location should be 3 letters.");
            } else {
                locationValidLength = true;
            }
        }

        while (!budgetValidNumber) {
            System.out.print("Enter budget: ");
            budget = input.nextInt();

            if (budget > 0 && budget <= 100000) {
                budgetValidNumber = true;
            } else {
                System.out.println("The budget should be greater than $0 up to a max of $100,000.");
            }
        }

        String outputText = department_name + "," + location + "," + budget;
        writeToFile(DEPARTMENT_PATH, outputText);
        
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        int choice;
        boolean running = true;
        Scanner input = new Scanner(System.in);

        while (running) {
            clearScreen();
            System.out.println("1. Get instructor information");
            System.out.println("2. Insert a new instructor");
            System.out.println("3. Insert a new department");
            System.out.println("4. Exit");
            choice = input.nextInt();
            clearScreen();

            switch (choice) {
                case 1:
                    instructor_Info(input);
                    break;
                case 2:
                    insert_Instructor(input);
                    break;
                case 3:
                    insert_Department(input);
                    break;
                case 4:
                    running = false;
                    input.close();
                    break;
            }
        }
    }
}
