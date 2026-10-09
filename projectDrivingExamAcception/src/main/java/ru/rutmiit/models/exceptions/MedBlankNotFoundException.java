package ru.rutmiit.models.exceptions;

public class MedBlankNotFoundException extends RuntimeException {
    public MedBlankNotFoundException(String message) {
        super(message);
    }

    public MedBlankNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
