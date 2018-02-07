package implementations;

import interfaces.MessageHandler;
import interfaces.MessageListener;
import interfaces.User;

import java.io.DataInputStream;
import java.io.IOException;

public class MessageHandlerImpl implements MessageHandler {

    private MessageListener messageListener;
    private volatile boolean work;

    @Override
    public void startAccepting(User user) {
        work = true;
        Runnable runnable = () -> {
            DataInputStream dataInputStream = user.getDataInputStream();
            if (dataInputStream == null) {
                System.out.println("Cannot accept messages from user " + user.getNickname());
            } else {
                System.out.println("Message handler began handling messages from user " + user.getNickname());
            }
            String message;

            while (work) {
                try {
                    message = user.getDataInputStream().readUTF();
                    messageListener.newMessage(user, message);
                } catch (IOException e) {
                    work = false;
                    System.out.println("User " + user.getNickname() + " has disconnected. Closing connection with user...");
                    user.closeConnection();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void stopAccepting() {
        work = false;
    }
}
