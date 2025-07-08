package io.github.smfmo.mscreditassessor.application.exception;

import lombok.Getter;

public class MicroservicesCommunicationErrorException extends Exception {

    @Getter
    private Integer statusCode;

    public MicroservicesCommunicationErrorException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
