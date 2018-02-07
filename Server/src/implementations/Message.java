package implementations;

import java.io.DataOutputStream;

public class Message {
    private String text;
    private DataOutputStream dataOutputStream;

    public Message(String text, DataOutputStream dataOutputStream) {
        this.text = text;
        this.dataOutputStream = dataOutputStream;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setSocket(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }
}
