package runtime.main;

import java.util.ArrayList;

public class CharChecker {

    public ArrayList<Character> allowedChars;

    public CharChecker() {
        allowedChars = new ArrayList<>();

        for (char i = 'А'; i <= 'я'; i++) {
            allowedChars.add(i);
        }

        for (char i = '0'; i <= '9'; i++) {
            allowedChars.add(i);
        }

        allowedChars.add('.');
        allowedChars.add(',');
        allowedChars.add('?');
        allowedChars.add('!');
        allowedChars.add(':');
        allowedChars.add('(');
        allowedChars.add(')');
        allowedChars.add('\"');
        allowedChars.add('\'');
        allowedChars.add('\n');
        allowedChars.add('-');
        allowedChars.add(' ');
        allowedChars.add((char)-1);

    }

    public boolean isCharValid(char character) {
        return allowedChars.contains(character);
    }
}