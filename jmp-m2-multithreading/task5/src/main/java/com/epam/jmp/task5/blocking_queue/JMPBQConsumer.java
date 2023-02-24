package com.epam.jmp.task5.blocking_queue;

import com.epam.jmp.task5.domain.Message;
import com.epam.jmp.task5.exceptions.JMPInterruptedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

/**
 * JMPBQConsumer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPBQConsumer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMPBQConsumer.class);

    private final BlockingQueue<Message> dataQueue;

    public JMPBQConsumer(BlockingQueue<Message> dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        Stream.iterate(0, i -> i < 20, i -> i + 1)
            .forEach(integer -> {
                try {
                    LOGGER.info("message: {}", dataQueue.take());
                } catch (InterruptedException e) {
                    throw new JMPInterruptedException(e);
                }
        });
    }
}
