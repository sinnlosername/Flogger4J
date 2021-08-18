package me.flo.flogger.impl;

import lombok.Getter;
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

    @Getter
    private File currentFile;
    private int lastDayOfMonth = -1;


    public DailyLogFilePublisher(final File directory) throws IOException {
        super(new FileWriter(newFile(directory), true));
        currentFile = newFile(directory);
        this.dir = directory;
    }

    private static File newFile(final File dir) throws IOException {
        File f = new File(dir, format.format(new Date(System.currentTimeMillis())) + ".log");
        if (!f.exists()) f.createNewFile();
        return f;
    }

    @Override
    public void handle(final FlogRecord record) {
        final LocalDate date = LocalDate.now();

        if (lastDayOfMonth != -1 && lastDayOfMonth != date.getDayOfMonth()) {
            try {
                writer.close();

                File nf = newFile(dir);
                writer = new FileWriter(nf, true);

                writerSwitched((FileWriter) writer, currentFile);
                currentFile = nf;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        lastDayOfMonth = date.getDayOfMonth();

        super.handle(record);
    }

    @SuppressWarnings("unused")
    protected void writerSwitched(final FileWriter newWriter, final File oldFile) {}

}
