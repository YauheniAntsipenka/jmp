package task5.blocking_queue;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) {
        DataQueue dataQueue = new DataQueue();
        new Thread(new Producer(dataQueue)).start();
        new Thread(new Consumer(dataQueue)).start();
    }
}
