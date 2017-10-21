package me.flo.flogger.types;

import lombok.Getter;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger
 */
public abstract class FlogPublisher {

    @Getter
    private FlogLevel level;

    public void handleIntern(FlogRecord record, FlogFormatter formatter) {
        if (!record.getLevel().isLoggable(level)) return;
        handle(record, formatter);
    }

    protected abstract void handle(FlogRecord record, FlogFormatter formatter);

    public FlogPublisher level(FlogLevel level) {
        this.level = level;
        return this;
    }

}
