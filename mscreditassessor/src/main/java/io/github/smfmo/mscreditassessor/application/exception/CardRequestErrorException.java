package io.github.smfmo.mscreditassessor.application.exception;

public class CardRequestErrorException extends RuntimeException {
    public CardRequestErrorException(String message) {
        super(message);
    }
}
