package com.epam.jmp.semaphores;

import com.epam.jmp.domain.Message;
import com.epam.jmp.exceptions.JMPInterruptedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

/**
 * JMPSemaphoreConsumer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPSemaphoreConsumer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMPSemaphoreConsumer.class);

    private final Queue<Message> dataQueue;
    private final Semaphore semProd;
    private final Semaphore semCon;

    public JMPSemaphoreConsumer(Semaphore semConsumer, Semaphore semProducer, Queue<Message> dataQueue) {
        this.dataQueue = dataQueue;
        this.semCon = semConsumer;
        this.semProd = semProducer;
    }

    @Override
    public void run() {
        Stream.iterate(0, i -> i < 20, i -> i + 1)
            .forEach(integer -> {
                try {
                    semCon.acquire();
                    LOGGER.info("message: {}", dataQueue.poll());
                    semProd.release();
                } catch (InterruptedException e) {
                    throw new JMPInterruptedException(e);
                }
            });
    }
}
