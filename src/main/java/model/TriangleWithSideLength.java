package model;

import java.math.BigDecimal;

/**
 * Triangle which is defined by 3 side lengths.
 */
public class TriangleWithSideLength implements Triangle {

    private final BigDecimal side1, side2, side3;
    private final TriangleTypeBySideLength typeBySideLength;

    /**
     * Construct a triangle from 3 side lengths in<br/>
     * <b>No validation is done with argument, you need to ensure that value passed in is valid</b><br/>
     * You can use {@link validator.TriangleSideLengthFromStringValidator#validate(String[])} to valid arguments first.<br/>
     * All side lengths are final and cannot be changed
     * @param side1
     * @param side2
     * @param side3
     */
    public TriangleWithSideLength(BigDecimal side1, BigDecimal side2, BigDecimal side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.typeBySideLength = determineType();
    }

    /**
     * Determine type of triangle by side length.<br/>
     * Side length's comparision should be done by compareTo() to make sure
     * that BigDecimal("5.0") and BigDecimal("5") is considered equal.<br/>
     * @return final type by side length of this triangle
     * @see TriangleTypeBySideLength
     */
    private TriangleTypeBySideLength determineType() {
        if (side1.compareTo(side2) == 0 || side2.compareTo(side3) == 0 || side3.compareTo(side1) == 0) {
            if (side1.compareTo(side2) == 0 && side2.compareTo(side3) == 0) {
                return TriangleTypeBySideLength.EQUILATERAL;
            }
            return TriangleTypeBySideLength.ISOSCELES;
        }
        return TriangleTypeBySideLength.SCALENE;
    }

    public TriangleTypeBySideLength getTypeBySideLength() {
        return typeBySideLength;
    }
}
