package me.flo.flogger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.flo.flogger.types.FlogFormatter;
import me.flo.flogger.types.FlogLevel;
import me.flo.flogger.types.FlogPublisher;
import me.flo.flogger.types.FlogRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger
 */
@RequiredArgsConstructor
public class Flogger4J {

    private static Flogger4J global;

    @Getter
    private final String name;
    @Setter @Getter
    private FlogLevel level = FlogLevel.INFO;
    @Setter @Getter
    private FlogFormatter formatter = FlogFormatter.DEFAULT;
    private final List<FlogPublisher> publishers = new ArrayList<>();

    public void log(FlogLevel level, String message) {
        if (level == FlogLevel.OFF) throw new IllegalArgumentException("Level can't be OFF");

        final FlogRecord record = new FlogRecord(this, level, message, System.currentTimeMillis());

        publishers.forEach(fp -> fp.handleIntern(record));
    }

    public void error(String message) {
        log(FlogLevel.ERROR, message);
    }

    public void warning(String message) {
        log(FlogLevel.WARN, message);
    }

    public void info(String message) {
        log(FlogLevel.INFO, message);
    }

    public void debug(String message) {
        log(FlogLevel.DEBUG, message);
    }

    public void trace(String message) {
        log(FlogLevel.TRACE, message);
    }

    public void addPublisher(FlogPublisher publisher) {
        if (publisher.getLevel() == null) publisher.level(this.level);
        if (publisher.getFormatter() == null) publisher.formatter(this.formatter);
        publishers.add(publisher);
    }

    public static Flogger4J initializeGlobal(final String name) {
        return global = new Flogger4J(name);
    }

    public static Flogger4J global() {
        return global;
    }

}
