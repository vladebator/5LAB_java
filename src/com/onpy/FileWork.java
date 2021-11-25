package com.onpy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWork {

    public static void serialize(Triangles triangles, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream outOOS = new ObjectOutputStream(fileOut);
            outOOS.writeObject(triangles);
            outOOS.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Triangles deserialize(String fileName) {
        Triangles triangles = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream inOIS = new ObjectInputStream(fileIn);
            triangles = (Triangles) inOIS.readObject();
            inOIS.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Not found!");
            c.printStackTrace();
        }
        return triangles;
    }

    public static void jacksonSerialize(Triangles triangles, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.writeValue(new File(fileName), triangles);
    }

    public static ArrayList<Triangle> jacksonDeSerialize(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Triangles newTriangles = objectMapper.readValue(new File(fileName), Triangles.class);
        return newTriangles.triangles;
    }

    public static void save(ArrayList<Triangle> triangles, String fileWay) throws IOException {

        byte[] bytesToWrite;
        byte[] x1, x2, x3, delimiter;
        String delimiters = "∎";

        for (Triangle object : triangles) {

            // Сторона  №1 треугольника
            String s2 = Double.toHexString(object.getX1());
            x1 = s2.getBytes(StandardCharsets.UTF_8);

            // Сторона №2 треугольника
            String s3 = Double.toHexString(object.getX2());
            x2 = s3.getBytes(StandardCharsets.UTF_8);

            // Сторона №3 треугольника
            String s4 = Double.toHexString(object.getX3());
            x3 = s4.getBytes(StandardCharsets.UTF_8);

            // разделитель
            delimiter = delimiters.getBytes(StandardCharsets.UTF_8);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            outputStream.write(x1);
            outputStream.write(delimiter);
            outputStream.write(x2);
            outputStream.write(delimiter);
            outputStream.write(x3);

            bytesToWrite = outputStream.toByteArray();
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

    public static void load(ArrayList<Triangle> triangles, String fileWay) {
        triangles.clear();
        byte[] readData = new byte[0];
        int amount = 0;
        try {
            FileInputStream inFile = new FileInputStream(fileWay);
            int bytesAvailable = inFile.available(); //сколько можно считать
            System.out.println("Available: " + bytesAvailable);

            byte[] bytesRead = new byte[bytesAvailable]; //куда считываем
            int count = inFile.read(bytesRead, 0, bytesAvailable);

            System.out.println("Было считано байт: " + count);
            System.out.println(Arrays.toString(bytesRead));
            readData = bytesRead;
            amount = count;
            inFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Невозможно произвести чтение из файла:" + fileWay);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода:" + e);
        }
        byte[] dannie = readData;
        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        int q = 0;

        for (int i = 0; i < amount; i++) {

            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
            }

            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                x1 = Integer.parseInt(String.valueOf(x1), 2);
            }
            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                x2 = Integer.parseInt(String.valueOf(x2), 2);
            }
            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                x3 = Integer.parseInt(String.valueOf(x3), 2);
            }
            if (i == amount - 1) {
                break;
            }
        }
    }
}