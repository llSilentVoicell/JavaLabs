package org.example;

import java.util.Random;
import java.util.Scanner;

public class MatrixApp {
    private static final int MAX_SIZE = 20;
    private static final int MIN_RANDOM = 1;
    private static final int MAX_RANDOM = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість рядків матриці: ");
        int rows = scanner.nextInt();

        System.out.print("Введіть кількість ствопців матриці: ");
        int cols = scanner.nextInt();

        if (rows <= 0 || cols <= 0 || rows > MAX_SIZE || cols > MAX_SIZE) {
            System.out.println("Невірний розмір матриці!");
            return;
        }

        System.out.print("Створити матрицю вручну (1) чи рандомно (2)? ");
        int choice = scanner.nextInt();

        int[][] matrix = new int[rows][cols];
        if (choice == 1) {
            fillMatrixManually(matrix, scanner);
        } else {
            fillMatrixRandomly(matrix);
        }

        printMatrix(matrix);
        analyzeMatrix(matrix);
    }

    private static void fillMatrixManually(int[][] matrix, Scanner scanner) {
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    private static void fillMatrixRandomly(int[][] matrix) {
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(MAX_RANDOM - MIN_RANDOM + 1) + MIN_RANDOM;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("Матриця:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%4d", value);
            }
            System.out.println();
        }
    }

    private static void analyzeMatrix(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        double sum = 0;
        int count = 0;
        double product = 1;

        for (int[] row : matrix) {
            for (int value : row) {
                if (value < min) min = value;
                if (value > max) max = value;
                sum += value;
                product *= value != 0 ? value : 1; // уникаємо множення на 0
                count++;
            }
        }

        double average = sum / count;
        double geometricMean = Math.pow(product, 1.0 / count);

        System.out.println("Мінімальний елемент: " + min);
        System.out.println("Максимальний елемент: " + max);
        System.out.println("Середнє арифметичне: " + average);
        System.out.println("Середнє геометричне: " + geometricMean);
    }
}
