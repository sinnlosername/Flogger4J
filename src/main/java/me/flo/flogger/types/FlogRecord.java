package me.flo.flogger.types;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import me.flo.flogger.Flogger4J;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger
 */
@AllArgsConstructor
@EqualsAndHashCode
public class FlogRecord {

    @Getter
    private final Flogger4J flogger;
    @Setter @Getter
    private FlogLevel level;
    @Setter @Getter
    private String message;
    @Getter
    private final long timestamp;

}
