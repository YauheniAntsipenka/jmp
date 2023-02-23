package com.epam.jmp.blocking_queue;

import com.epam.jmp.domain.Message;
import com.epam.jmp.exceptions.JMPInterruptedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

/**
 * Producer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPBQProducer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMPBQProducer.class);

    private final BlockingQueue<Message> dataQueue;

    public JMPBQProducer(BlockingQueue<Message> dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        Stream.iterate(0, i -> i < 20, i -> i + 1)
            .forEach(integer -> {
                try {
                    Message message = new Message(new Random().nextInt(), Thread.currentThread().getName());
                    dataQueue.put(message);
                    LOGGER.info("message: {}", message);
                } catch (InterruptedException e) {
                    throw new JMPInterruptedException(e);
                }
        });
    }
}
