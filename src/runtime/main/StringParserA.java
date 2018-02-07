package runtime.main;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StringParserA implements StringParser {

    private CharChecker charChecker;
    private TextAnalyzer analyzer;
    private volatile Flag flag;
    private char currentChar;
    private FileReader fileReader;

    @Override
    public void parse(TextAnalyzer analyzer, Flag flag, FileReader fileReader) throws IOException {
        charChecker = new CharChecker();
        this.analyzer = analyzer;
        this.flag = flag;
        this.fileReader = fileReader;
        currentChar = 0;

        while (flag.value()) {
            currentChar = (char) fileReader.read();
            if (currentChar == '\uFFFF') {
                fileReader.close();
                return;
            }

            if (!charChecker.isCharValid(currentChar)) {
                onIllegalCharacterFound();
                return;
            }

            if ('А' <= currentChar && currentChar <= 'я') {
                    parseWord();
            }
        }
        fileReader.close();
    }

    private void onIllegalCharacterFound() throws IOException {
        flag.setValue(false);
        System.out.println("Illegal character found, stop parsing: " + currentChar);
        fileReader.close();
    }

    private void parseWord() throws IOException {
        StringBuilder tempStr = new StringBuilder();
        do {
            if (currentChar == '\uFFFF') {
                fileReader.close();
                break;
            }
            if (!charChecker.isCharValid(currentChar)) {
                onIllegalCharacterFound();
            }
            tempStr.append(currentChar);
        }
        while ((currentChar = (char) fileReader.read()) != '\uFFFF'
                && 'А' <= currentChar && currentChar <= 'я');
        synchronized (analyzer) {
            analyzer.addWord(tempStr.toString());
            System.out.println(analyzer.getStats());
        }
        try {
            TimeUnit.MILLISECONDS.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
