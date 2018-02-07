package interfaces;

import java.net.Socket;

public interface ConnectionListener {
    void newConnection(Socket socket);
}
