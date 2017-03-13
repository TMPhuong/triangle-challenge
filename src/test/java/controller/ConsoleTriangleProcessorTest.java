package controller;

import message.MessageService;
import model.Triangle;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import validator.ValidationResult;
import validator.Validator;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleTriangleProcessorTest {

    @Mock private TriangleFactory triangleFactory;
    @Mock private Validator validator;
    @Mock private MessageService messageService;
    @Mock private ErrorService errorService;
    @Mock private ValidationResult validationResult;
    @Mock private Triangle triangle;

    private ConsoleTriangleProcessor processor;

    private final String[] nonEmptyInput = new String[] {RandomStringUtils.random(5)};

    @Before
    public void setup() {
        processor = new ConsoleTriangleProcessor(triangleFactory, validator, messageService, errorService);
    }

    @Test
    public void construct_triangle_validator_throw_exception_should_and_return_empty_optional_triangle() {
        doThrow(new RuntimeException("Fake error")).when(validator).validate(eq(nonEmptyInput));

        Optional<Triangle> triangleOptional = processor.constructTriangle(nonEmptyInput);
        Assert.assertFalse(triangleOptional.isPresent());
    }

    @Test
    public void construct_triangle_validation_fail_should_return_empty_optional_triangle() {

        doReturn(validationResult).when(validator).validate(eq(nonEmptyInput));
        doReturn(false).when(validationResult).isValid();

        Optional<Triangle> triangleOptional = processor.constructTriangle(nonEmptyInput);
        verify(errorService, times(1)).handleInvalidResult(validationResult);
        Assert.assertFalse(triangleOptional.isPresent());
    }

    @Test
    public void construct_triangle_validation_success_should_return_triangle() {
        doReturn(validationResult).when(validator).validate(eq(nonEmptyInput));
        doReturn(true).when(validationResult).isValid();
        doReturn(triangle).when(triangleFactory).createTriangle(nonEmptyInput);

        Optional<Triangle> triangleOptional = processor.constructTriangle(nonEmptyInput);
        verify(errorService, times(0)).handleInvalidResult(validationResult);
        Assert.assertTrue(triangleOptional.isPresent());
    }

}