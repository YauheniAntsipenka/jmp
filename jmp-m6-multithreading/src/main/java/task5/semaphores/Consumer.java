package task5.semaphores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * Consumer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Consumer implements Runnable {

    private final DataQueue dataQueue;
    private final Semaphore semProd;
    private final Semaphore semCon;
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    public Consumer(Semaphore semConsumer, Semaphore semProducer, DataQueue dataQueue) {
        this.dataQueue = dataQueue;
        this.semCon = semConsumer;
        this.semProd = semProducer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++){
            try {
                semCon.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LOGGER.info(String.valueOf(dataQueue.remove()));
            semProd.release();
        }
    }
}
