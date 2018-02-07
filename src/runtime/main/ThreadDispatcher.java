package runtime.main;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class ThreadDispatcher {

    private TextAnalyzer analyzer;
    private Flag flag = new Flag(true);

    public ThreadDispatcher(TextAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void calculateWords(ArrayList<FileReader> input) {
        for (int i = 0; i < input.size(); i++) {
            final FileReader reader = input.get(i);
            Runnable task = () -> {
                StringParser parser = (StringParser) Proxy.newProxyInstance(StringParser.class.getClassLoader()
                        , new Class[]{StringParser.class}, new StringParserInvokationHandler());
                try {
                    parser.parse(analyzer, flag, reader);
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
}
