package model;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(JUnitParamsRunner.class)
public class TriangleWithSideLengthTest {

    private Object[] triangleTypeTests() {
        return new Object[]{
                new Object[] {b("4.0"), b("4.0"), b("5"), TriangleTypeBySideLength.ISOSCELES},
                new Object[] {b("5.0"), b("7.5"), b("5"), TriangleTypeBySideLength.ISOSCELES},
                new Object[] {b("6.8"), b("3"), b("6.800"), TriangleTypeBySideLength.ISOSCELES},
                new Object[] {b("8.50"), b("8.500"), b("8.5"), TriangleTypeBySideLength.EQUILATERAL},
                new Object[] {b("6"), b("6.0"), b("6.0000"), TriangleTypeBySideLength.EQUILATERAL},
                new Object[] {b("4.7"), b("4.6999999"), b("4.7000001"), TriangleTypeBySideLength.SCALENE},
                new Object[] {b("3.5"), b("4"), b("5.2"), TriangleTypeBySideLength.SCALENE},
        };
    }

    @Test
    @Parameters(method = "triangleTypeTests")
    public void triangle_type_by_side_length_should_be_determined_correctly(BigDecimal side1, BigDecimal side2, BigDecimal side3, TriangleTypeBySideLength expectedType) {
        TriangleWithSideLength triangle = new TriangleWithSideLength(side1, side2, side3);
        Assert.assertEquals("Triangle type is not as expected", expectedType, triangle.getTypeBySideLength());

    }

    private BigDecimal b(String value) {
        return new BigDecimal(value);
    }
}