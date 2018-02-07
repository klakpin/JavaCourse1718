package runtime.main;

import java.io.FileReader;
import java.io.IOException;

public interface StringParser {

    void parse(TextAnalyzer analyzer, Flag flag, FileReader fileReader, CharChecker charChecker) throws IOException;
}
