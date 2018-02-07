package implementations;

import interfaces.MessageQueue;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueueImpl implements MessageQueue {

    private final LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private volatile boolean work = true;

    @Override
    public void sendMessage(Message message) {
        synchronized (queue) {
            queue.add(message);
        }
    }

    @Override
    public void startSending() {
        work = true;
        Runnable runnable = () -> {
            System.out.println("Message queue is started.");
            Message message;
            while (work) {
                try {
                    message = queue.take();
                    System.out.println("Message queue has new message: " + message.getText());
                    try {
                        DataOutputStream dataOutputStream = message.getDataOutputStream();
                        dataOutputStream.writeUTF(message.getText());
                        dataOutputStream.flush();
                    }catch (SocketException e) {
                        System.out.println("User was disconnected.");
                    } catch (IOException e) {
                        System.out.println("Problems with stream output, ignoring message " + message.getText());
                    }
                } catch (InterruptedException e) {
                    System.out.println("Message queue was interrupted, relaunching");
                    work = false;
                    startSending();
                    return;
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void stopSending() {
        work = false;
    }
}
