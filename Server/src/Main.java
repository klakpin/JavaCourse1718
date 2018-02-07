import implementations.*;
import interfaces.ConnectionHandler;
import interfaces.MessageHandler;
import interfaces.MessageQueue;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {

        MessageQueue messageQueue = new MessageQueueImpl();
        MessageHandler messageHandler = new MessageHandlerImpl();
        Resources resources = new Resources();

        try {
            ServerSocket serverSocket = new ServerSocket(56789);
            ConnectionHandler connectionHandler = new ConnectionHandlerImpl(serverSocket);

            Server server = new Server(messageQueue, connectionHandler, messageHandler, resources);
            server.addRoom();
            server.addRoom();
            server.init();

        } catch (IOException e) {
            System.out.println("Cannot start server");
            e.printStackTrace();
        }


    }
}
