import Implementations.Client;
import Implementations.InputReaderImpl;
import Implementations.MessageQueueImpl;
import Implementations.MessageReceiverImpl;
import interfaces.InputReader;
import interfaces.MessageQueue;
import interfaces.MessageReceiver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {


        try {
            Socket socket = new Socket("127.0.0.1",56789);
            MessageReceiver messageReceiver = new MessageReceiverImpl(new DataInputStream(socket.getInputStream()));
            MessageQueue messageQueue = new MessageQueueImpl(new DataOutputStream(socket.getOutputStream()));
            InputReader inputReader = new InputReaderImpl(System.in, messageQueue);
            Client client = new Client(messageReceiver, inputReader);
            client.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
