package com.epam.jmp.task5.blocking_queue;

import com.epam.jmp.task5.domain.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) {
        BlockingQueue<Message> dataQueue = new LinkedBlockingQueue<>();
        new Thread(new JMPBQProducer(dataQueue)).start();
        new Thread(new JMPBQConsumer(dataQueue)).start();
    }
}
