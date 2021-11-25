package com.onpy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class FileWork {

    /**
     * Нативная сериализация java
     * @param triangles Объект класса Triangles
     * @param fileName Путь к файлу для сохранения
     * @throws IOException Обработка ошибок ввода/вывода
     */
    public static void serialize(Triangles triangles, String fileName) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream outOOS = new ObjectOutputStream(fileOut);
            outOOS.writeObject(triangles);
            outOOS.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // examResults.examResults.clear();
    }

    public static Triangles deserialize(String fileName) throws IOException {
        Triangles triangles = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream inOIS = new ObjectInputStream(fileIn);
            triangles = (Triangles) inOIS.readObject();
            inOIS.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException с) {
            System.out.println("Not found!");
            с.printStackTrace();
        }
        return triangles;
    }

    public static void jacksonSerialize(Triangles triangles, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.writeValue(new File(fileName), triangles);
    }

    public static ArrayList jacksonDeSerialize(ArrayList<Triangle> triangles, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Triangles newTriangles = objectMapper.readValue(new File(fileName), Triangles.class);
        return triangles = newTriangles.triangles;
    }

    public static void save(ArrayList<Triangle> triangles, String fileWay) throws IOException {

        byte[] bytesToWrite;
        byte[] numberTriangle, x1, x2, x3, razd;
        String razdel = "∎";

        for (Triangle object : triangles) {

           /* // номер треугольника
            String s1 = Double.toHexString(object.getNumTriangle());
            numberTriangle = s1.getBytes(StandardCharsets.UTF_8);*/

            // Сторона  №1 треугольника
            String s2 = Double.toHexString(object.getX1()); // todo get hex value from int (by Speranskyy) =)
            x1 = s2.getBytes(StandardCharsets.UTF_8);

            // Сторона №2 треугольника
            String s3 = Double.toHexString(object.getX2());
            x2 = s3.getBytes(StandardCharsets.UTF_8);

            // Сторона №3 треугольника
            String s4 = Double.toHexString(object.getX3());
            x3 = s4.getBytes(StandardCharsets.UTF_8);

            // разделитель
            razd = razdel.getBytes(StandardCharsets.UTF_8);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            /*outputStream.write(numberTriangle);
            outputStream.write(razd);*/
            outputStream.write(x1);
            outputStream.write(razd);
            outputStream.write(x2);
            outputStream.write(razd);
            outputStream.write(x3);

            bytesToWrite = outputStream.toByteArray();
            /**
             *
             */
            FileOutputStream outFile = null;
            boolean isOpened = false;
            try {
                outFile = new FileOutputStream(fileWay, true);
                isOpened = true;
                outFile.write(bytesToWrite); // запись в файл
            } catch (FileNotFoundException e) {
                System.out.println("Невозможно произвести запись в файл:" + fileWay);
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода:" + e);
            }
            if (isOpened) {
                outFile.close();
            }
        }
    }

    public static void load(ArrayList<Triangle> triangles, String fileWay) throws IOException {
        triangles.clear();
        byte[] wert = new byte[0];
        int amount = 0;
        try {

            /**
             *
             */
            FileInputStream inFile = new FileInputStream(fileWay);
            int bytesAvailable = inFile.available(); //сколько можно считать
            System.out.println("Available: " + bytesAvailable);

            byte[] bytesReaded = new byte[bytesAvailable]; //куда считываем
            int count = inFile.read(bytesReaded, 0, bytesAvailable);

            System.out.println("Было считано байт: " + count);
            System.out.println(Arrays.toString(bytesReaded));
            byte[] trap = bytesReaded;
            wert = trap;
            amount = count;
            inFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Невозможно произвести чтение из файла:" + fileWay);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода:" + e);
        }
        byte[] dannie = wert;
        int numberTriange = 0;
        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        int q = 0;
        String num = "";

        for (int i = 0; i < amount; i++) {

            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                byte[] ab = new byte[1];
                ab[0] = dannie[i];
                String str = new String(ab, StandardCharsets.UTF_8);
                num = num + str;
                numberTriange = Integer.parseInt(num, 2);
            }

            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                byte[] ab = new byte[1];
                ab[0] = dannie[i];
                String str = new String(ab, StandardCharsets.UTF_8);
                //x1 = x1 + str;
                x1 = Integer.parseInt(String.valueOf(x1), 2);
            }
            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                byte[] ab = new byte[1];
                ab[0] = dannie[i];
                String str = new String(ab, StandardCharsets.UTF_8);
                //m1 = m1 + str;
                x2 = Integer.parseInt(String.valueOf(x2), 2);
            }
            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                byte[] ab = new byte[1];
                ab[0] = dannie[i];
                String str = new String(ab, StandardCharsets.UTF_8);
                //d1 = d1 + str;
                x3 = Integer.parseInt(String.valueOf(x3), 2);
            }
            if (i == amount - 1) {
                break;
            }
            //triangles.add(new triangle(x1, x2, x3, numberTriange));
            numberTriange = 0;
            x1 = 0;
            x2 = 0;
            x3 = 0;
        }
    }
}