package com.cta4j.api.aws.exception;

public class SecretException extends RuntimeException {
    public SecretException(String message) {
        super(message);
    }

    public SecretException(String message, Throwable cause) {
        super(message, cause);
    }
}
