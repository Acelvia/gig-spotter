package com.acelvia.client;

/**
 * Can be thrown to indicate that something went wrong with a call to an external API.
 */
public class APIUsageException extends Exception {

    public APIUsageException(String message) {
        super(message);
    }

    public APIUsageException(String message, Throwable cause) {
        super(message, cause);
    }
}
