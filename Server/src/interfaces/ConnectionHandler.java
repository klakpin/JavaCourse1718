package interfaces;

public interface ConnectionHandler {
    void startAccepting();

    void setConnectionListener(ConnectionListener listener);
}
