package task5.blocking_queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task5.JMPModule2Task5Exception;
import task5.domain.Message;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

/**
 * Consumer
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
        IntStream.range(0, 20).forEach(value -> {
            try {
                LOGGER.info(String.valueOf(dataQueue.take()));
            } catch (InterruptedException e) {
                throw new JMPModule2Task5Exception(e);
            }
        });
    }
}
