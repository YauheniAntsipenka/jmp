package task5.blocking_queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task5.domain.Message;

import java.util.Random;

/**
 * Producer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Producer implements Runnable {

    private final DataQueue dataQueue;
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++){
            Message message = new Message(new Random().nextInt(), Thread.currentThread().getName());
            dataQueue.add(message);
            LOGGER.info(String.valueOf(message));
        }
    }
}
