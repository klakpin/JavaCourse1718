package interfaces;

public interface MessageQueue {
    void sendMessage(String message);

    void start();

    void stop();

    void clear();
}
