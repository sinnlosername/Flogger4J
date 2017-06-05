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
public class Flogger {

    private static Flogger global;

    @Getter
    private final String name;
    @Setter @Getter
    private FlogLevel level = FlogLevel.INFO;
    @Setter @Getter
    private FlogFormatter formatter = FlogFormatter.DEFAULT;
    private final List<FlogPublisher> publishers = new ArrayList<>();

    public void log(FlogLevel level, String message) {
        if (level == FlogLevel.OFF) throw new IllegalArgumentException("Level can't be OFF");
        if (!level.isLoggable(this.level)) return;

        final FlogRecord record = new FlogRecord(this, level, message, System.currentTimeMillis());

        publishers.forEach(fp -> fp.handle(record, formatter));
    }

    public void error(String message) {
        log(FlogLevel.ERROR, message);
    }
    public void warning(String message) {
        log(FlogLevel.WARNING, message);
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
        publishers.add(publisher);
    }

    public static void initializeGlobal(final String name) {
        global = new Flogger(name);
    }

    public static Flogger global() {
        return global;
    }

}