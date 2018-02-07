package Implementations;

import interfaces.InputReader;
import interfaces.MessageReceiver;
import interfaces.OnNewMessageListener;

public class Client {

    private MessageReceiver messageReceiver;
    private InputReader inputReader;

    public Client(MessageReceiver messageReceiver, InputReader inputReader) {
        this.messageReceiver = messageReceiver;
        this.inputReader = inputReader;
    }

    public void init() {
        messageReceiver.setOnNewMessageListener(System.out::println);
        inputReader.startReading();
        messageReceiver.startReceiving();
    }
}
