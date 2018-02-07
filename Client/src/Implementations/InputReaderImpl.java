package Implementations;

import interfaces.InputReader;
import interfaces.MessageQueue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class InputReaderImpl implements InputReader {

    private InputStream inputStream;
    private MessageQueue messageQueue;
    private volatile boolean work = true;

    public InputReaderImpl(InputStream inputStream, MessageQueue messageQueue) {
        this.inputStream = inputStream;
        this.messageQueue = messageQueue;
    }

    @Override
    public void startReading() {
        messageQueue.start();

        Runnable runnable = () -> {
            System.out.println("InputReader started");
            Scanner scanner = new Scanner(inputStream);
            String input;
            while (work) {
                input = scanner.nextLine();
                if (input.equals("/quit")) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        System.out.println("Error while closing input stream");
                        e.printStackTrace();
                    }
                        messageQueue.clear();
                        System.exit(0);

                }
                messageQueue.sendMessage(input);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

    @Override
    public void stop() {
        messageQueue.stop();
        work = false;
    }
}
