package implementations;

import interfaces.*;

import java.net.Socket;
import java.util.HashMap;

public class Server {

    private HashMap<Integer, Room> rooms = new HashMap<>();

    private MessageQueue messageQueue;
    private ConnectionHandler connectionHandler;
    private MessageHandler messageHandler;
    private Resources resources;

    private int userCounter = 0;
    private int roomCounter = 0;

    public Server(MessageQueue messageQueue, ConnectionHandler connectionHandler, MessageHandler messageHandler, Resources resources) {
        this.messageQueue = messageQueue;
        this.connectionHandler = connectionHandler;
        this.messageHandler = messageHandler;
        this.resources = resources;
    }

    public void addRoom() {
        rooms.put(++roomCounter, new RoomImpl());
    }

    public void init() {
        connectionHandler.setConnectionListener(socket -> {
            System.out.println("New connection!");
            User user = new UserImpl(socket);
            user.setNickname("User " + ++userCounter);
            messageQueue.sendMessage(new Message(resources.greetingText, user.getDataOutputStream()));
            messageHandler.startAccepting(user);
        });

        messageHandler.setMessageListener(new MessageListenerImpl(messageQueue, rooms, resources));

        messageQueue.startSending();
        connectionHandler.startAccepting();

    }
}
