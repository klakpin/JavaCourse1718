package interfaces;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public interface User {

    int getRoomId();

    void setRoomId(int roomId);

    String getNickname();

    void setNickname(String nickname);

    DataInputStream getDataInputStream();

    DataOutputStream getDataOutputStream();

    void closeConnection();
}
