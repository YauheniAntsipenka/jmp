package task5.semaphores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task5.domain.Message;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Producer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Producer implements Runnable {

    private final Semaphore semProd;
    private final Semaphore semCon;
    private final DataQueue dataQueue;
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    public Producer(Semaphore semProducer, Semaphore semConsumer, DataQueue dataQueue) {
        this.dataQueue = dataQueue;
        this.semProd = semProducer;
        this.semCon = semConsumer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++){
            Message message = new Message(new Random().nextInt(), Thread.currentThread().getName());
            try {
                semProd.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dataQueue.add(message);
            LOGGER.info(String.valueOf(message));
            semCon.release();
        }
    }
}
