package model;

public class TriangleException extends Exception {
    public TriangleException(String msg) {
        super(msg);
    }

    public TriangleException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
