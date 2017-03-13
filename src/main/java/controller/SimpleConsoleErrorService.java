package controller;

import message.MessageService;
import validator.ValidationResult;

/**
 * Simple service to print out details about invalid {@link ValidationResult} to console
 */
public class SimpleConsoleErrorService implements ErrorService {

    private final MessageService messageService;

    private final static String INVALID_NUMBER_OF_SIDE_KEY = "triangle.validation.invalidNumberOfSide";
    private final static String INVALID_SIDE_LENGTH_KEY = "triangle.validation.invalidSideLength";
    private final static String DETAIL_KEY = "triangle.validation.details";


    public SimpleConsoleErrorService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleInvalidResult(ValidationResult validationResult) {
        switch (validationResult.getValidationCode()) {
            case INVALID_NUMBER_OF_SIDE:
                System.out.println(messageService.getString(INVALID_NUMBER_OF_SIDE_KEY));
                break;
            case INVALID_SIDE_LENGTH:
                System.out.println(messageService.getString(INVALID_SIDE_LENGTH_KEY));
                break;
        }
        validationResult.getOptionalMessage().ifPresent(msg ->
                System.out.println(String.format(messageService.getString(DETAIL_KEY), msg)));
    }
}
