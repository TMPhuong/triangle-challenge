package model;

/**
 * Interface for all triangles, common functions of a triangle can be defined here
 */
public interface Triangle extends GeometryObject {
    TriangleTypeBySideLength getTypeBySideLength();
}
