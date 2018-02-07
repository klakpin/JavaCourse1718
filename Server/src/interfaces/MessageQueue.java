package interfaces;

import implementations.Message;

public interface MessageQueue {

    void sendMessage(Message message);

    void startSending();

    void stopSending();
}
