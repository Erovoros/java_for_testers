package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {


    @Test
    void canCalculatePerimeter() {
        var t1 = new Triangle (3, 4, 5);
        var t2 = new Triangle (3, 2, 4);

        Assertions.assertEquals(6.0, t1.triangleArea());
        Assertions.assertEquals(2.9047375096555625, t2.triangleArea());

    }


}
