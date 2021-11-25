package com.onpy;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static Triangles triangles = new Triangles();

    public static int CheckCorrectFunction(String value) {
        int func = 0;
        try {
            func = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            func = -1;
        }
        return func;
    }

    public static void main(String[] args) throws IOException {
        int function = 0;
        Scanner scan = new Scanner(System.in);

        triangles.add(new Triangle(1, 2, 3));
        triangles.add(new Triangle(4, 5, 6));
        triangles.add(new Triangle(7, 8, 9));

        do {
            System.out.println("\nСписок функций: ");
            System.out.println("[1] Вывод массива на экран");
            System.out.println("[2] Сохранение в файл");
            System.out.println("[3] Сериализация базы данных");
            System.out.println("[4] Десериализация базы данных");
            System.out.println("[5] Сериализация Jackson базы данных");
            System.out.println("[6] Десериализация Jackson базы данных");
            System.out.println("[6] Очистка");
            System.out.println("[8] Ввод данных треугольников");
            System.out.println("[9] Выход из программы");
            do {
                System.out.print("Введите номер функции: ");
                function = CheckCorrectFunction(scan.nextLine());
            } while (function == -1);

            switch (function) {
                case 1:
                    System.out.println(triangles);
                    break;
                case 2:
                    System.out.println("Введите путь к файлу:");
                    String saveFileName = scan.nextLine();
                    triangles.saveFile(saveFileName);
                    break;
                case 3:
                    System.out.print("\nВведите путь для сохранения файла:");
                    String serializeFileName = scan.nextLine();
                    triangles.serializeFile(serializeFileName);
                    break;
                case 4:
                    System.out.print("\nВведите путь к базе данных:");
                    String deserializeFileName = scan.nextLine();
                    triangles.deserializeFile(deserializeFileName);
                    break;
                case 5:
                    System.out.print("\nВведите путь для сохранения файла:");
                    String jacksonSerializeFileName = scan.nextLine();
                    triangles.JacksonSerializeFile(jacksonSerializeFileName);
                    break;
                case 6:
                    System.out.print("\nВведите путь к базе данных:");
                    String jacksonDeserializeFileName = scan.nextLine();
                    triangles.jacksonDeserializeFile(jacksonDeserializeFileName);
                    break;
                case 7:
                    triangles.triangles.clear();
                    break;
                case 8:
                    Triangles trianglesInput = new Triangles();
                    trianglesInput.dataInput();
                    System.out.println(triangles);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("\nТакой функции нету");
            }
        } while (function != 0);
    }
}