package com.mc.challenge.cityconnectivity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalProcessingException extends Exception {
    /**
     *
     * @param message
     */
    public InternalProcessingException(final String message) {
        super(message);
    }
}
