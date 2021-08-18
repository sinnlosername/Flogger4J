package me.flo.flogger.types;

import lombok.Getter;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger
 */
@Getter
public abstract class FlogPublisher {

    private FlogLevel level;
    private FlogFormatter formatter;

    public void handleIntern(FlogRecord record) {
        if (!record.getLevel().isLoggable(level)) return;
        handle(record);
    }

    protected abstract void handle(FlogRecord record);

    public FlogPublisher level(FlogLevel level) {
        this.level = level;
        return this;
    }

    public FlogPublisher formatter(FlogFormatter formatter) {
        this.formatter = formatter;
        return this;
    }
}
