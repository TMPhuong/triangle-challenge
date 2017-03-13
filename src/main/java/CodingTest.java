import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import model.Console;
import model.Triangle;
import controller.TriangleProcessor;

import java.io.IOException;
import java.util.Optional;

public class CodingTest {

    public static void main(String[] args) throws IOException {
        Injector injector = Guice.createInjector(new ConsoleCodingTestModule());

        final TriangleProcessor processor = injector.getInstance(Key.get(TriangleProcessor.class, Console.class));
        Optional<Triangle> triangleOptional = processor.constructTriangle(args);

        triangleOptional.ifPresent(triangle -> processor.outputTriangleType(triangle.getTypeBySideLength()));
    }

}
