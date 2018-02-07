package runtime.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void doWork() throws FileNotFoundException {


        TextAnalyzer analyzer = new TextAnalyzer();
        ThreadDispatcher dispatcher = new ThreadDispatcher(analyzer);

        FileReader fr = new FileReader("i1.txt");
        FileReader fr2 = new FileReader("i2.txt");

        ArrayList<FileReader> readers = new ArrayList<>();

        readers.add(fr);
        readers.add(fr2);

        dispatcher.calculateWords(readers);

    }

    public static void main(String[] args) throws IOException {
        doWork();
    }
}
