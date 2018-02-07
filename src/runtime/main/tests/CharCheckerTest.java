package runtime.main.tests;

import org.junit.Test;
import runtime.main.CharChecker;

import static org.junit.Assert.*;

public class CharCheckerTest {

    @Test
    public void shouldWork() {
        CharChecker checker = new CharChecker();
        assertEquals(true, checker.isCharValid('к'));
    }

}