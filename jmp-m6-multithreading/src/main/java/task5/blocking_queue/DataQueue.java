package task5.blocking_queue;

import task5.domain.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * DataQueue
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class DataQueue {

    private final BlockingQueue<Message> queue;

    public DataQueue() {
        this.queue = new LinkedBlockingQueue<>();
    }

    public void add(Message message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Message remove() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
