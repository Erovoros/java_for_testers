package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0 ) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

        if ((a + b < c)||( a + c < b)||(b + c < a)) {
            throw new IllegalArgumentException("The sum of the two sides cannot be less than the third side");
        }

    }


    public static void printTriangleArea(Triangle t) {
        var text = String.format("Площадь треугольника со сторонами %f,%f и %f = %f", t.a, t.b, t.c, t.triangleArea());
        System.out.println(text);

    }

    public double triangleArea() {

        var p = triangleHalfPerimeter();

        var s = (Math.sqrt(p * (p - a) * (p - b) * (p - c)));

        return s;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return ((Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                ||(Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.b) == 0)
                ||(Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.a) == 0)
                ||(Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0)
                ||(Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.a) == 0)
                ||(Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.c) == 0));
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public double triangleHalfPerimeter() {
        return (a + b + c) / 2;
    }


}
