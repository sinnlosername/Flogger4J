package me.flo.flogger.impl;

import me.flo.flogger.types.FlogFormatter;
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
    private final BlockingQueue<Object[]> queue;

    public AsyncLogWriterPublisher(final LogWriterPublisher publisher, final int queueSize) {
        this.publisher = publisher;

        this.queue = queueSize == -1 ? new LinkedBlockingQueue<>() : new ArrayBlockingQueue<>(queueSize);
        startAsync(this);
    }

    @Override
    public void run() {
        //noinspection LoopConditionNotUpdatedInsideLoop
        while (publisher != null) {
            try {
                final Object[] array = queue.take();
                System.out.println("-1 queue element. Now: " + queue.size());
                publisher.handle((FlogRecord) array[0], (FlogFormatter) array[1]);
            } catch (InterruptedException e) {
                new RuntimeException("FLOGGER CRASH", e).printStackTrace();
            }
        }
    }

    @Override
    public void handle(FlogRecord record, FlogFormatter formatter) {
        if (!queue.offer(new Object[] {record, formatter}))
            throw new RuntimeException("No space left in logging queue.");
    }

    protected abstract void startAsync(final Runnable r);

}
