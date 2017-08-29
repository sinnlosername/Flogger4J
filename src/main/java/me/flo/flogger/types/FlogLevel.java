package me.flo.flogger.types;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger
 */
public enum FlogLevel {
    OFF,
    ERROR,
    WARN,
    INFO,
    DEBUG,
    TRACE;

    public boolean isLoggable(FlogLevel logLevel) {
        return this.ordinal() <= logLevel.ordinal();
    }

}
