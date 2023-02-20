package task5.blocking_queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task5.JMPModule2Task5Exception;
import task5.domain.Message;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

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
        IntStream.range(0, 20).forEach(value -> {
            try {
                Message message = new Message(new Random().nextInt(), Thread.currentThread().getName());
                dataQueue.put(message);
                LOGGER.info("message: {}", message);
            } catch (InterruptedException e) {
                throw new JMPModule2Task5Exception(e);
            }
        });
    }
}
