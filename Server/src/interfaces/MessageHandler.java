package interfaces;

public interface MessageHandler {

    void startAccepting(User user);

    void setMessageListener(MessageListener messageListener);

    void stopAccepting();
}
