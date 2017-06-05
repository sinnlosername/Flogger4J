package me.flo.flogger.types;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger
 */
public interface FlogFormatter {

    FlogFormatter DEFAULT = new Default();

    String format(FlogRecord record);

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    final class Default implements FlogFormatter {

        private final SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        private FlogRecord cacheRecord;
        private String cacheFormatted;

        @Override
        public String format(FlogRecord record) {
            if (cacheRecord != null && cacheRecord.equals(record))
                return cacheFormatted;
            cacheRecord = record;
            return cacheFormatted = ('[' + format.format(record.getTimestamp()) +
                    " -> " + record.getLevel().name() + "]: " + record.getMessage());
        }

    }

}
