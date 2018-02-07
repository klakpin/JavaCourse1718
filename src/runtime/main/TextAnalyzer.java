package runtime.main;

import java.util.HashMap;

public class TextAnalyzer {

    private HashMap<String, Integer> wordsCounter = new HashMap<>();

    public void addWord(String word) {
        if (wordsCounter.containsKey(word)) {
            wordsCounter.put(word, wordsCounter.get(word) + 1);
        } else {
            wordsCounter.put(word, 1);
        }
    }

    public String getStats() {
        return wordsCounter.toString();
    }

}
