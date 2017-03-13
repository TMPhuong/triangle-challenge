package controller;

import validator.ValidationResult;

/**
 * Generic interface to handle {@link ValidationResult}
 */
public interface ErrorService {
    void handleInvalidResult(ValidationResult validationResult);
}
