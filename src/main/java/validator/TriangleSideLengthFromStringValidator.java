package validator;

import message.MessageService;

import java.math.BigDecimal;
import java.util.Arrays;

public class TriangleSideLengthFromStringValidator implements Validator<String[]> {

    private final MessageService messageService;

    private final static String INVALID_NUMBER_OF_SIDE_DETAILS = "triangle.invalidNumberOfSide.details";
    private final static String INVALID_SIDE_LENGTH_NOT_POSITIVE_DETAILS = "triangle.invalidSideLength.notPositive.details";
    private final static String INVALID_SIDE_LENGTH_NOT_A_NUMBER_DETAILS = "triangle.invalidSideLength.notANumber.details";
    private final static String INVALID_SIDE_LENGTH_FOR_A_TRIANGLE = "triangle.invalidSideLength.notATriangle.details";

    public TriangleSideLengthFromStringValidator(MessageService messageService) {
        this.messageService = messageService;
    }

    public ValidationResult validate(String[] toValidate) {
        if (toValidate == null || toValidate.length != 3) {
            return new ValidationResult(ValidationCode.INVALID_NUMBER_OF_SIDE, getDetailMessage(INVALID_NUMBER_OF_SIDE_DETAILS, toValidate != null ? toValidate.length : 0));
        }

        BigDecimal[] sides = new BigDecimal[3];
        for (int i = 0; i < 3; i++) {
            try {
                sides[i] = new BigDecimal(toValidate[i]);
                if (sides[i].signum() <= 0) {
                    return new ValidationResult(ValidationCode.INVALID_SIDE_LENGTH, getDetailMessage(INVALID_SIDE_LENGTH_NOT_POSITIVE_DETAILS, toValidate[i]));
                }
            } catch (NumberFormatException exp) {
                return new ValidationResult(ValidationCode.INVALID_SIDE_LENGTH, getDetailMessage(INVALID_SIDE_LENGTH_NOT_A_NUMBER_DETAILS, toValidate[i]));
            }
        }

        Arrays.sort(sides);
        BigDecimal maxSideLength = sides[2];
        BigDecimal sumOfTwoOtherSides = sides[0].add(sides[1]);
        if (maxSideLength.compareTo(sumOfTwoOtherSides) >= 0) {
            return new ValidationResult(ValidationCode.INVALID_SIDE_LENGTH, getDetailMessage(INVALID_SIDE_LENGTH_FOR_A_TRIANGLE, maxSideLength, sumOfTwoOtherSides));
        }

        return new ValidationResult(ValidationCode.VALID);
    }

    private String getDetailMessage(String stringKey, Object... objects) {
        return messageService.getString(stringKey, objects);
    }


}
