package implementations;

import interfaces.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserImpl implements User {

    private Socket userSocket;
    private String nickName;
    private int roomId = -1;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public UserImpl(Socket userSocket) {
        this.userSocket = userSocket;
    }

    @Override
    public int getRoomId() {
        return roomId;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickName = nickname;
    }

    @Override
    public void closeConnection() {
        dataOutputStream = null;
        dataInputStream = null;

        try {
            userSocket.close();
        } catch (IOException e) {
            System.out.println("Cannot close connection with user " + getNickname());
        }
    }

    @Override
    public DataInputStream getDataInputStream() {
        if (dataInputStream == null) {
            try {
                dataInputStream = new DataInputStream(userSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return dataInputStream;
    }

    @Override
    public DataOutputStream getDataOutputStream() {
        if (dataOutputStream == null) {
            try {
                dataOutputStream = new DataOutputStream(userSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return dataOutputStream;
    }

    @Override
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
