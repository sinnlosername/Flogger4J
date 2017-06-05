package me.flo.flogger.impl;

import me.flo.flogger.types.FlogFormatter;
import me.flo.flogger.types.FlogPublisher;
import me.flo.flogger.types.FlogRecord;

import java.io.Writer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger.impl
 */
public abstract class AsyncFlogWriterPublisher implements FlogPublisher {

    public AsyncFlogWriterPublisher(Writer writer, int queueSize) {
        if (queueSize < 0) throw new IllegalArgumentException("queue size must be >=0");
        queue = new ArrayBlockingQueue<>(queueSize == 0 ? 100 : queueSize);
        startThread(new FlogWriterPublisher(writer), queue);
    }

    private final BlockingQueue<FlogRecord> queue;

    @Override
    public void handle(FlogRecord record, FlogFormatter formatter) {
        queue.offer(record);
    }

    public abstract void startThread(FlogWriterPublisher publisher, BlockingQueue<FlogRecord> queue);

}
