package me.flo.flogger.impl;

import lombok.AllArgsConstructor;
import me.flo.flogger.types.FlogException;
import me.flo.flogger.types.FlogFormatter;
import me.flo.flogger.types.FlogPublisher;
import me.flo.flogger.types.FlogRecord;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger.impl
 */
@AllArgsConstructor
public class LogWriterPublisher extends FlogPublisher {

    protected Writer writer;

    @Override
    public void handle(FlogRecord record, FlogFormatter formatter) {
        try {
            writer.write(formatter.format(record) + '\n');
            writer.flush();
        } catch (IOException e) {
            throw new FlogException("Failed to write to writer", e);
        }
    }

}
