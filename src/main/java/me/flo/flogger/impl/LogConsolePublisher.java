package me.flo.flogger.impl;

import me.flo.flogger.types.FlogLevel;
import me.flo.flogger.types.FlogPublisher;
import me.flo.flogger.types.FlogRecord;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger.impl
 */
public final class LogConsolePublisher extends FlogPublisher {

    @Override
    public void handle(FlogRecord record) {
        (record.getLevel() == FlogLevel.ERROR ? System.err : System.out).println(getFormatter().format(record));
    }

}
