package com.epam.jmp.semaphores;

import com.epam.jmp.domain.Message;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) {
        Queue<Message> dataQueue = new LinkedList<>();
        Semaphore semProducer = new Semaphore(1);
        Semaphore semConsumer = new Semaphore(0);

        new Thread(new JMPSemaphoreProducer(semProducer, semConsumer, dataQueue)).start();
        new Thread(new JMPSemaphoreConsumer(semConsumer, semProducer, dataQueue)).start();
    }
}
