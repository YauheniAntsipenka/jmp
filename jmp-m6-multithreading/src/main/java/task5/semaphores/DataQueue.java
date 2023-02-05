package task5.semaphores;

import task5.domain.Message;

import java.util.LinkedList;
import java.util.Queue;

/**
 * DataQueue
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class DataQueue {

    private final Queue<Message> queue;

    public DataQueue() {
        this.queue = new LinkedList<>();
    }

    public void add(Message message) {
        queue.add(message);
    }

    public Message remove() {
        return queue.poll();
    }
}
