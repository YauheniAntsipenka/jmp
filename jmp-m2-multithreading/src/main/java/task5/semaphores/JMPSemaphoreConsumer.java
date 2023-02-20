package task5.semaphores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task5.JMPModule2Task5Exception;
import task5.domain.Message;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * Consumer
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
        IntStream.range(0, 20).forEach(value -> {
            try {
                semCon.acquire();
                LOGGER.info("message: {}", dataQueue.poll());
                semProd.release();
            } catch (InterruptedException e) {
                throw new JMPModule2Task5Exception(e);
            }
        });
    }
}
