package com.onpy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Math.pow;

public class Triangles implements Serializable {
    int inCountTriangle = 2;
    int numberTriangle = 0;

    @JsonProperty("triangles")
    ArrayList<Triangle> triangles;
    @JsonProperty("description")
    String description;
    static final long serialVersionUID = 656565665L;

    public Triangles() {
        description = LocalDate.now().toString();
        this.triangles = new ArrayList<>();
    }

    public Triangles(Triangles triangles) {
        this.triangles = triangles.triangles;
        this.description = triangles.description;
    }

    void add(Triangle triangle) {
        triangles.add(triangle);
    }

    @Override
    public String toString() {
        return "Triangles{" +
                triangles +
                '}';
    }

    void serializeFile(String fileName) {
        FileWork.serialize(this, fileName);
    }

    void deserializeFile(String fileName) {
        Triangles er = new Triangles(FileWork.deserialize(fileName));
        this.triangles = er.triangles;
        this.description = er.description;
    }

    void JacksonSerializeFile(String fileName) throws IOException {
        FileWork.jacksonSerialize(this, fileName);
        triangles.clear();
    }

    void jacksonDeserializeFile(String fileName) throws IOException {
        this.triangles = FileWork.jacksonDeSerialize(fileName);
    }

    void saveFile(String fileName) throws IOException {
        FileWork.save(triangles, fileName);
    }

    public void dataInput() throws IOException {

        Triangle[] triangles = new Triangle[inCountTriangle];
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < triangles.length; i++) {
            triangles[i] = new Triangle();

            System.out.print("Введите сторону Х1 для треугольника №" + (i + 1) + ": ");
            triangles[i].x1 = scan.nextInt();
            System.out.print("Введите сторону Х2 для треугольника №" + (i + 1) + ": ");
            triangles[i].x2 = scan.nextInt();
            System.out.print("Введите сторону Х3 для треугольника №" + (i + 1) + ": ");
            triangles[i].x3 = scan.nextInt();
            triangles[i].numberTriangle = ++numberTriangle;

            triangles[i].perimeter = triangles[i].x1 + triangles[i].x2 + triangles[i].x3;
            triangles[i].alpha = Math.abs(Math.cos(((pow(triangles[i].x1, 2) + pow(triangles[i].x3, 2) - pow(triangles[i].x2, 2)) / 2 * triangles[i].x1 * triangles[i].x3)));
            triangles[i].betta = Math.abs(Math.cos(((pow(triangles[i].x1, 2) + pow(triangles[i].x2, 2) - pow(triangles[i].x3, 2)) / 2 * triangles[i].x1 * triangles[i].x2)));
            triangles[i].gamma = Math.abs(Math.cos(((pow(triangles[i].x2, 2) + pow(triangles[i].x3, 2) - pow(triangles[i].x1, 2)) / 2 * triangles[i].x3 * triangles[i].x2)));
            triangles[i].square = 0.5 * triangles[i].x1 * triangles[i].x2 * Math.sin(triangles[i].alpha);

            System.out.println("Периметр треугольника №" + triangles[i].numberTriangle + ": " + triangles[i].perimeter);
            System.out.println("Косинус угла Alpha = " + triangles[i].alpha);
            System.out.println("Косинус угла Betta = " + triangles[i].betta);
            System.out.println("Косинус угла Gamma = " + triangles[i].gamma);
            System.out.println("Площадь треугольника №" + triangles[i].numberTriangle + " = " + triangles[i].square);

            if (triangles[i].x1 == triangles[i].x2 || triangles[i].x1 == triangles[i].x3 || triangles[i].x2 == triangles[i].x3) {
                System.out.println("Треугольник №" + triangles[i].numberTriangle + " является равнобедренным.");
                triangles[i].isosceles = 1;
            } else {
                System.out.println("Треугольник №" + triangles[i].numberTriangle + " не является равнобедренным.");
            }
        }
        double averageSquare = 0;
        double max = 0;
        double min = 0;
        int countNormalTriangle = 0;
        int countIsoscelesTriangle = 0;

        // В этом цикле я вычисляю среднюю площадь для обычных треугольников
        for (int i = 0; i < triangles.length; i++) {
            if (triangles[i].isosceles == 0) {
                countNormalTriangle = countNormalTriangle + 1;
                averageSquare = +triangles[i].square;
            }
        }

        // В этом цикле находим минимальную площадь равнобедренных треугольников
        for (int i = 0; i < triangles.length; i++) {
            if (triangles[i].isosceles == 1) {
                countIsoscelesTriangle = countIsoscelesTriangle + 1;
                for (int j = 0; j != triangles.length; j++) {
                    if (!(triangles[i].square < max)) {
                        max = triangles[i].square;
                    }
                    if (triangles[i].square < min) {
                        min = triangles[i].square;
                    }
                }
            }
        }
        averageSquare = averageSquare / countNormalTriangle;
        // Выводим теперь эти данные на экран, среднюю площадь треугольников и мин.площ. равнобедренного треугольника.
        System.out.println("Средняя площадь треугольников = " + averageSquare);
        System.out.println("Минимальная площадь равнобедренного треугольника = " + min);

        System.out.println("Выполнить сохранение данных в файл? (Enter 1 = Yes)");
        int saveData = scan.nextInt();
        if(saveData == 1) {
            //System.out.println("Введите путь к файлу:");
            ArrayList<Triangle> triangleArrayList = new ArrayList<>();
            triangleArrayList.addAll(Arrays.asList(triangles));
            FileWork.save(triangleArrayList, "d:\\1.txt");
            //BinaryDataSaver.save(triangleArrayList, wayToFile);
            System.out.println("Файл успешно сохранён!");
        }
    }
}