package task5.blocking_queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Consumer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Consumer implements Runnable {

    private final DataQueue dataQueue;
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    public Consumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++){
            LOGGER.info(String.valueOf(dataQueue.remove()));
        }
    }
}
