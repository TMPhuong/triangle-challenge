package validator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import message.MessageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(JUnitParamsRunner.class)
public class TriangleSideLengthFromStringValidatorTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final static String INVALID_NUMBER_OF_SIDE_DETAILS = "triangle.invalidNumberOfSide.details";
    private final static String INVALID_SIDE_LENGTH_NOT_POSITIVE_DETAILS = "triangle.invalidSideLength.notPositive.details";
    private final static String INVALID_SIDE_LENGTH_NOT_A_NUMBER_DETAILS = "triangle.invalidSideLength.notANumber.details";
    private final static String INVALID_SIDE_LENGTH_FOR_A_TRIANGLE = "triangle.invalidSideLength.notATriangle.details";

    @Mock MessageService messageService;

    private TriangleSideLengthFromStringValidator validator;


    @Before
    public void setup() {
        validator = new TriangleSideLengthFromStringValidator(messageService);
    }

    private Object[] invalidCases() {
        return new Object[]{
                new Object[] { null, ValidationCode.INVALID_NUMBER_OF_SIDE, INVALID_NUMBER_OF_SIDE_DETAILS },
                new Object[] { new String[] {}, ValidationCode.INVALID_NUMBER_OF_SIDE, INVALID_NUMBER_OF_SIDE_DETAILS },
                new Object[] { new String[] { "abc", "1" }, ValidationCode.INVALID_NUMBER_OF_SIDE, INVALID_NUMBER_OF_SIDE_DETAILS },
                new Object[] { new String[] { "2.0", "3", "4", "5" }, ValidationCode.INVALID_NUMBER_OF_SIDE, INVALID_NUMBER_OF_SIDE_DETAILS },
                new Object[] { new String[] { "2", "3", "-4" }, ValidationCode.INVALID_SIDE_LENGTH, INVALID_SIDE_LENGTH_NOT_POSITIVE_DETAILS },
                new Object[] { new String[] { "2.0", "2a", "4" }, ValidationCode.INVALID_SIDE_LENGTH, INVALID_SIDE_LENGTH_NOT_A_NUMBER_DETAILS },
                new Object[] { new String[] { "2.0", "6", "3" }, ValidationCode.INVALID_SIDE_LENGTH, INVALID_SIDE_LENGTH_FOR_A_TRIANGLE },
                new Object[] { new String[] { "3.0", "3", "6.00" }, ValidationCode.INVALID_SIDE_LENGTH, INVALID_SIDE_LENGTH_FOR_A_TRIANGLE },
        };
    }

    @Test
    @Parameters(method = "invalidCases")
    public void invalid_cases_should_be_return_correct_error_code_and_msg(String[] arguments, ValidationCode expectedValidationCode, String msgKey) {

        ArgumentCaptor<String> msgKeyCaptor = ArgumentCaptor.forClass(String.class);
        String returnedMsg = RandomStringUtils.random(20);
        doReturn(returnedMsg).when(messageService).getString(msgKeyCaptor.capture(), any());

        ValidationResult validationResult = validator.validate(arguments);
        Assert.assertEquals("Validation code is not as expected", expectedValidationCode, validationResult.getValidationCode());
        Assert.assertEquals("Validation msg key is not as expected", msgKey, msgKeyCaptor.getValue());
        Assert.assertEquals("Validation msg is not as expected", returnedMsg, validationResult.getOptionalMessage().get());

    }


    private Object[] validCases() {
        return new Object[]{
                new Object[]{ new String[] { "3.0", "5", "4" }},
                new Object[]{ new String[] { "3", "10.5", "9.875" }},
                new Object[]{ new String[] { "3.0", "6.999999", "4" }},
        };
    }

    @Test
    @Parameters(method = "validCases")
    public void valid_cases_should_return_correct_validation_code(String[] arguments) {
        ValidationResult validationResult = validator.validate(arguments);

        Assert.assertEquals("Validation code is not as expected", ValidationCode.VALID, validationResult.getValidationCode());

    }

}