package controller;

import model.Triangle;
import model.TriangleTypeBySideLength;

import java.util.Optional;

/**
 * Interface for different strategies to process input and construct triangle
 * as well as how to output triangle type.<br/>
 * Lots of implementations can be supported in the future such as:<br/>
 * <ul>
 *     <li>FileTriangleProcessor: args contains file name, read file to get detail about triangle</li>
 *     <li>APITriangleProcessor: args contains api to get details to construct triangle</li>
 *     <li>{@link ConsoleTriangleProcessor}</li>
 * </ul>
 */
public interface TriangleProcessor {
    Optional<Triangle> constructTriangle(String[] args);
    void outputTriangleType(TriangleTypeBySideLength triangleTypeBySideLength);
}
