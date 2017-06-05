package me.flo.flogger.types;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger.types
 */
public class FlogException extends RuntimeException {
    public FlogException(String message, Throwable cause) {
        super(message, cause);
    }
}
