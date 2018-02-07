package implementations;

import interfaces.ConnectionHandler;
import interfaces.ConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandlerImpl implements ConnectionHandler {

    private ConnectionListener listener;
    private ServerSocket serverSocket;

    private volatile boolean accept = true;

    public ConnectionHandlerImpl(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void startAccepting() {
        if (listener == null) {
            return;
        }

        Runnable runnable = () -> {
            System.out.println("Connection Handler is working.");
            while (accept) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Connection handler got new connection");
                    listener.newConnection(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void setConnectionListener(ConnectionListener listener) {
        this.listener = listener;
    }
}
