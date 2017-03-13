package controller;

import model.Triangle;
import model.TriangleWithSideLength;

import java.math.BigDecimal;

public class TriangleWithSideLengthFactory implements TriangleFactory {

    public Triangle createTriangle(String[] args) {
        return new TriangleWithSideLength(new BigDecimal(args[0]), new BigDecimal(args[1]), new BigDecimal(args[2]));
    }
}
