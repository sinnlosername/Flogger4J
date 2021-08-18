package me.flo.flogger.impl;

import me.flo.flogger.types.FlogPublisher;
import me.flo.flogger.types.FlogRecord;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Florian on 05.06.17 in me.flo.flogger.impl
 */
public abstract class AsyncLogWriterPublisher extends FlogPublisher implements Runnable {

    private final LogWriterPublisher publisher;
    private final BlockingQueue<Object> queue;

    public AsyncLogWriterPublisher(final LogWriterPublisher publisher, final int queueSize) {
        this.publisher = publisher;

        this.queue = queueSize == -1 ? new LinkedBlockingQueue<>() : new ArrayBlockingQueue<>(queueSize);
        startAsync(this);
    }

    @Override
    public void run() {
        while (publisher != null) {
            try {
                final Object record = queue.take();
                publisher.handle((FlogRecord) record);
            } catch (InterruptedException e) {
                new RuntimeException("FLOGGER CRASH", e).printStackTrace();
            }
        }
    }

    @Override
    public void handle(FlogRecord record) {
        if (!queue.offer(record))
            throw new RuntimeException("No space left in logging queue.");
    }

    protected abstract void startAsync(final Runnable r);

}
