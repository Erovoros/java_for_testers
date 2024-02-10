package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {


    public static void printTriangleArea(Triangle t) {
        var text = String.format("Площадь треугольника со сторонами %f,%f и %f = %f", t.a, t.b, t.c, t.triangleArea());
        System.out.println(text);

    }

    public double triangleArea() {

        var p = triangleHalfPerimeter();

        var s = (Math.sqrt(p * (p - a) * (p - b) * (p - c)));

        return s;

    }

    public double triangleHalfPerimeter() {
        return (a + b + c) / 2;
    }


}
