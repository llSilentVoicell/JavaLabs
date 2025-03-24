package org.example;

import org.example.database.DatabaseManager;
import org.example.model.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initDatabase();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть номер місяця (1-12): ");
        int month = scanner.nextInt();

        List<Student> students = DatabaseManager.getStudentsBornInMonth(month);

        if (students.isEmpty()) {
            System.out.println("Немає студентів, народжених у цьому місяці.");
        } else {
            System.out.println("Студенти, народжені у вибраному місяці:");
            students.forEach(System.out::println);
        }
    }
}