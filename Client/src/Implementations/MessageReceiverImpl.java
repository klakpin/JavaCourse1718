package Implementations;

import interfaces.OnNewMessageListener;
import interfaces.MessageReceiver;

import java.io.DataInputStream;
import java.io.IOException;

public class MessageReceiverImpl implements MessageReceiver {

    private OnNewMessageListener onNewMessageListener;
    private DataInputStream dataInputStream;
    private volatile boolean work;
    public MessageReceiverImpl(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    @Override
    public void startReceiving() {
        work = true;
        Runnable runnable = () -> {
            System.out.println("MessageReceiver started");
            String message;
            while (work) {
                try {
                    message = dataInputStream.readUTF();
                    onNewMessageListener.onNewMessage(message);
                } catch (IOException e) {
                    System.out.println("Connection closed");
                    System.exit(0);
//                        e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void setOnNewMessageListener(OnNewMessageListener onNewMessageListener) {
        this.onNewMessageListener = onNewMessageListener;
    }

}
