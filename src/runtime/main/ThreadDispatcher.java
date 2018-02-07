package runtime.main;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Scanner;

public class ThreadDispatcher {

    private TextAnalyzer analyzer;
    private Flag flag = new Flag(true);
    private InputType type = new InputType();

    public ThreadDispatcher(TextAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void calculateWords(ArrayList<FileReader> input) {

        type.setType('A');
        Runnable changeWatcher = () -> {
            Scanner scanner = new Scanner(System.in);
            String inp;
            while (true) {
                inp = scanner.nextLine();
                System.out.println("Got new input: " + inp);
                switch (inp) {
                    case "A":
                        type.setType('A');
                        break;
                    case "B":
                        type.setType('B');
                        break;
                    case "q":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Unknown type");
                        break;
                }
            }
        };

        Thread inputReader = new Thread(changeWatcher);
        inputReader.start();

        for (final FileReader reader : input) {
            Runnable task = () -> {
                StringParser parser = (StringParser) Proxy.newProxyInstance(StringParser.class.getClassLoader()
                        , new Class[]{StringParser.class}, new StringParserInvocationHandler(type));
                try {
                    CharChecker checker = new CharChecker();
                    while (flag.value()) {
                        parser.parse(analyzer, flag, reader, checker);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    flag.setValue(false);
                    System.out.println("I/O error while file reading.");
                }
            };

            Thread thread = new Thread(task);
            thread.start();
        }
    }

    public class InputType {
        private char type;

        public char getType() {
            return type;
        }
        public void setType(char type) {
            this.type = type;
        }
    }
}
