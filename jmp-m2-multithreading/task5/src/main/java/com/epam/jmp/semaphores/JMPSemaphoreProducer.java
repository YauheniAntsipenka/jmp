package com.epam.jmp.semaphores;

import com.epam.jmp.domain.Message;
import com.epam.jmp.exceptions.JMPInterruptedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

/**
 * Producer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPSemaphoreProducer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMPSemaphoreProducer.class);

    private final Semaphore semProd;
    private final Semaphore semCon;
    private final Queue<Message> dataQueue;

    public JMPSemaphoreProducer(Semaphore semProducer, Semaphore semConsumer, Queue<Message> dataQueue) {
        this.dataQueue = dataQueue;
        this.semProd = semProducer;
        this.semCon = semConsumer;
    }

    @Override
    public void run() {
        Stream.iterate(0, i -> i < 20, i -> i + 1)
            .forEach(integer -> {
                try {
                    Message message = new Message(new Random().nextInt(), Thread.currentThread().getName());
                    semProd.acquire();
                    dataQueue.add(message);
                    LOGGER.info("message: {}", message);
                    semCon.release();
                } catch (InterruptedException e) {
                    throw new JMPInterruptedException(e);
                }
        });
    }
}
