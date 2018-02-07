package interfaces;

public interface MessageReceiver {

    void startReceiving();

    void setOnNewMessageListener(OnNewMessageListener onNewMessageListener);
}
