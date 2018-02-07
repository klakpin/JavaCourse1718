package implementations;

import interfaces.MessageListener;
import interfaces.MessageQueue;
import interfaces.Room;
import interfaces.User;

import java.util.HashMap;
import java.util.Map;

public class MessageListenerImpl implements MessageListener {

    private MessageQueue messageQueue;
    private HashMap<Integer, Room> rooms;
    private Resources resources;

    public MessageListenerImpl(MessageQueue messageQueue, HashMap<Integer, Room> rooms, Resources resources) {
        this.messageQueue = messageQueue;
        this.rooms = rooms;
        this.resources = resources;
    }

    private void sendToRoom(User sender, String text) {
        for (User userInRoom : rooms.get(sender.getRoomId()).getUsers()) {
            if (!userInRoom.getNickname().equals(sender.getNickname())) {
                messageQueue.sendMessage(new Message(sender.getNickname() + ": " + text, userInRoom.getDataOutputStream()));
            }
        }
    }

    private void sendToRoom(int roomId, String text) {
        for (User userInRoom : rooms.get(roomId).getUsers()) {
                messageQueue.sendMessage(new Message("[INFO] " + text, userInRoom.getDataOutputStream()));
        }
    }

    private void onCommandMessage(User user, String text) {

        if (text.equals("/exit")) {
            int roomId = user.getRoomId();
            rooms.get(roomId).getUsers().remove(user);
            user.closeConnection();
            sendToRoom(roomId, "User " + user.getNickname() + " has disconnected");

        } else if (text.equals("/help")) {
            messageQueue.sendMessage(new Message(resources.help, user.getDataOutputStream()));

        } else if (text.equals("/rooms")) {
            StringBuilder roomsList = new StringBuilder();
            for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
                roomsList.append("id: ")
                        .append(entry.getKey())
                        .append('\n');
            }
            messageQueue.sendMessage(new Message(roomsList.toString(), user.getDataOutputStream()));

        } else if (text.matches("/selectroom \\d+")) {

            int roomId = Integer.parseInt(text.split(" ")[1]);
            if (rooms.containsKey(roomId)) {
                if (user.getRoomId() != -1) {
                    rooms.get(user.getRoomId()).getUsers().remove(user);
                }
                user.setRoomId(roomId);
                rooms.get(roomId).addUser(user);
                sendToRoom(roomId, "[INFO] " + " user " + user.getNickname() + " has connected.");
            } else {
                messageQueue.sendMessage(new Message("No such room\n", user.getDataOutputStream()));
            }
        } else {
            messageQueue.sendMessage(new Message("Command not recognised\n", user.getDataOutputStream()));
            messageQueue.sendMessage(new Message(resources.help, user.getDataOutputStream()));
        }

    }

    private void onTextMessage(User user, String text) {
        if (user.getRoomId() == -1) {
            messageQueue.sendMessage(new Message("You should be in room to send messages", user.getDataOutputStream()));
        } else {
            sendToRoom(user, text);
        }
    }

    @Override
    public void newMessage(User user, String text) {
        System.out.println("New message received: " + text);
        if (text.charAt(0) == '/') {
            onCommandMessage(user, text);
        } else {
            onTextMessage(user, text);
        }
    }
}
