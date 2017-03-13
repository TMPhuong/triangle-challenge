package controller;

import message.MessageService;
import model.Triangle;
import model.TriangleTypeBySideLength;
import validator.ValidationResult;
import validator.Validator;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

/**
 * With this strategy, we read triangle side length details directly from the input.<br/>
 * If there is no argument, we will prompt user to key in side details from the console.
 */
public class ConsoleTriangleProcessor implements TriangleProcessor {

    private static final String DELIMITER = " ";
    private final TriangleFactory triangleFactory;
    private final Validator<String[]> validator;
    private final MessageService messageService;
    private final ErrorService errorService;

    private static final String UNKNOWN_ERROR_KEY = "triangle.unknownException";
    private static final String TRIANGLE_TYPE_OUTPUT_KEY = "triangle.type.out";
    private static final String TRIANGLE_SIDE_PROMPT_KEY = "triangle.sideLength.prompt";

    public ConsoleTriangleProcessor(TriangleFactory triangleFactory, Validator<String[]> validator, MessageService messageService, ErrorService errorService) {
        this.triangleFactory = triangleFactory;
        this.validator = validator;
        this.messageService = messageService;
        this.errorService = errorService;
    }

    public Optional<Triangle> constructTriangle(String[] args) {
        try {
            if (args == null || args.length == 0) {
                args = readFromUserConsoleInput();
            } else {
                System.out.println("Input is: " + Arrays.toString(args));
            }
            ValidationResult validationResult = validator.validate(args);
            if (validationResult.isValid()) {
                return Optional.of(triangleFactory.createTriangle(args));
            } else {
                errorService.handleInvalidResult(validationResult);
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println(messageService.getString(UNKNOWN_ERROR_KEY) + e.getMessage());
            return Optional.empty();
        }
    }

    public void outputTriangleType(TriangleTypeBySideLength triangleType) {
        System.out.println(String.format(messageService.getString(TRIANGLE_TYPE_OUTPUT_KEY), triangleType.getDescription(messageService)));
    }


    private String[] readFromUserConsoleInput() {
        System.out.println(messageService.getString(TRIANGLE_SIDE_PROMPT_KEY));
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().split(DELIMITER);
    }
}
