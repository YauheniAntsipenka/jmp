package task5.semaphores;

import java.util.concurrent.Semaphore;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) {
        DataQueue dataQueue = new DataQueue();
        Semaphore semProducer = new Semaphore(1); //if permit > 0 => thread can use resource
        Semaphore semConsumer = new Semaphore(0);

        new Thread(new Producer(semProducer, semConsumer, dataQueue)).start();
        new Thread(new Consumer(semConsumer, semProducer, dataQueue)).start();
    }
}
