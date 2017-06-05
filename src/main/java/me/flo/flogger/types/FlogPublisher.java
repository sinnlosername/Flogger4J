package me.flo.flogger.types;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger
 */
public interface FlogPublisher {

    void handle(FlogRecord record, FlogFormatter formatter);

}
