package validator;

import java.util.Optional;

public class ValidationResult {

    private final ValidationCode validationCode;
    private final Optional<String> message;

    public final static String DEFAULT_EMPTY_MESSAGE = null;

    public ValidationResult(ValidationCode validationCode) {
        this(validationCode, DEFAULT_EMPTY_MESSAGE);
    }

    public ValidationResult(ValidationCode validationCode, String message) {
        this.validationCode = validationCode;
        this.message = Optional.ofNullable(message);
    }


    public boolean isValid() {
        return this.validationCode.equals(ValidationCode.VALID);
    }

    public ValidationCode getValidationCode() {
        return this.validationCode;
    }

    public Optional<String> getOptionalMessage() {
        return this.message;
    }
}
