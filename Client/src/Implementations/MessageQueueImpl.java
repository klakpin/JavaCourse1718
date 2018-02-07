package Implementations;

import interfaces.MessageQueue;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueueImpl implements MessageQueue {

    private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private volatile boolean work;
    private DataOutputStream dataOutputStream;

    public MessageQueueImpl(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    @Override
    public void sendMessage(String message) {
        synchronized (queue) {
            queue.add(message);
        }
    }

    @Override
    public void start() {
        System.out.println("MessageQueue started");
        work = true;
        Runnable runnable = () -> {
            String message;
            while (work) {
                try {
                    message = queue.take();
                    try {
                        dataOutputStream.writeUTF(message);
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

    @Override
    public void stop() {
        work = false;
        queue.clear();
    }

    @Override
    public void clear() {
        stop();
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            System.out.println("Problems with closing output stream.");
            e.printStackTrace();
        }
    }

}
