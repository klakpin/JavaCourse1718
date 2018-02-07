package runtime.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void doWork() throws FileNotFoundException {


        TextAnalyzer analyzer = new TextAnalyzer();
        ThreadDispatcher dispatcher = new ThreadDispatcher(analyzer);

//        FileReader fr1 = new FileReader("i1.txt");
//        FileReader fr2 = new FileReader("i2.txt");
        FileReader fr3 = new FileReader("i3.txt");
        FileReader fr4 = new FileReader("i4.txt");

        ArrayList<FileReader> readers = new ArrayList<>();
//        readers.add(fr1);
//        readers.add(fr2);
        readers.add(fr3);
        readers.add(fr4);

        dispatcher.calculateWords(readers);

    }

    public static void main(String[] args) throws IOException {
        doWork();
    }
}
