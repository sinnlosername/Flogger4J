package me.flo.flogger.impl;

import me.flo.flogger.types.FlogFormatter;
import me.flo.flogger.types.FlogRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Florian on 20.10.17 in me.flo.flogger.impl
 */
public class DailyLogFilePublisher extends LogWriterPublisher  {

    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
    private final File dir;
    private int lastDayOfMonth = -1;


    public DailyLogFilePublisher(final File directory) throws IOException {
        super(newFileWriter(directory));
        this.dir = directory;
    }

    private static FileWriter newFileWriter(final File dir) throws IOException {
        return new FileWriter(new File(dir, format.format(new Date(System.currentTimeMillis())) + ".log"));
    }

    @Override
    public void handle(final FlogRecord record, final FlogFormatter formatter) {
        final LocalDate date = LocalDate.now();

        if (lastDayOfMonth != -1 && lastDayOfMonth != date.getDayOfMonth()) {
            try {
                writer.close();
                writer = newFileWriter(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        lastDayOfMonth = date.getDayOfMonth();

        super.handle(record, formatter);
    }

}
