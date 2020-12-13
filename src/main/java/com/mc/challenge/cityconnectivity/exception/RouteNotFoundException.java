package com.mc.challenge.cityconnectivity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RouteNotFoundException extends Exception {

    /**
     *
     * @param message
     */
    public RouteNotFoundException(final String message) {
        super(message);
    }
}
