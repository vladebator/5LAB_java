package com.onpy;

import java.io.Serializable;
import java.util.Scanner;

class Triangle implements Serializable {
    static final long serialVersionUID = 454564564564564L;
    long numberTriangle;
    double x1;
    double x2;
    double x3;
    double perimeter;
    double alpha;
    double betta;
    double gamma;
    double square;
    long isosceles = 0;
    String y;

    public Triangle(double x1, double x2, double x3) {
        this.setX1(x1);
        this.setX2(x2);
        this.setX3(x3);
    }

    public Triangle(){}

    public void setX1(double x1) {
        double trueSide = x1;
        if (trueSide <= 0) {
            System.out.print("Ошибка ввода. Сторона треугольника не может быть отрицательной! ");
            Scanner scan = null;
            trueSide = scan.nextDouble();
        }
        this.x1 = (int) trueSide;
    }

    public double getX1() {
        return x1;
    }

    public void setX2(double x2) {
        double trueSide = x2;
        if (trueSide <= 0) {
            System.out.print("Ошибка ввода. Сторона треугольника не может быть отрицательной! ");
            Scanner scan = null;
            trueSide = scan.nextDouble();
        }
        this.x2 = trueSide;
    }

    public double getX2() {
        return x2;
    }

    public void setX3(double x3) {
        double trueSide = x3;
        if (trueSide <= 0) {
            System.out.print("Ошибка ввода. Сторона треугольника не может быть отрицательной! ");
            Scanner scan = null;
            trueSide = scan.nextDouble();
        }
        this.x3 = trueSide;
    }

    public double getX3() {
        return x3;
    }

  /*  public double getNumTriangle() {
        return numberTriangle;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getY() {
        return y;
    }*/

    @Override
    public String toString() {
        return "x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                '}';
    }
}