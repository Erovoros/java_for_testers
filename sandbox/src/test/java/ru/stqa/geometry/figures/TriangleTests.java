package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    Triangle t1 = new Triangle(3, 4, 5);
    Triangle t2 = new Triangle(3, 2, 4);


    @Test
    void canCalculateHalfPerimeter() {
        Assertions.assertEquals(6.0, t1.triangleHalfPerimeter());
        Assertions.assertEquals(4.5, t2.triangleHalfPerimeter());
    }


    @Test
    void canCalculateArea() {
        Assertions.assertEquals(6.0, t1.triangleArea());
        Assertions.assertEquals(2.9047375096555625, t2.triangleArea());
    }



    @Test
    void cannotCreateNegativeTriangle() {
        try {
            new Triangle(-5.0, 4.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void cannotCreateInvalidTriangle() {
        try {
            new Triangle(- 3.0, 5.0, 8.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void testEquality() {
        var t1 = new Triangle (3.0, 5.0, 4.0);
        var t2 = new Triangle (5.0, 3.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }


}
