package Tests;

import org.junit.jupiter.api.Test;
import runtime.main.List;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    @Test
    public void test() {
        List<String, String> myList = new List<>();

        assertEquals(myList.add("One", "1"), null);

        assertEquals(myList.get("One"), "1");
        assertEquals(myList.get("Two"), null);

        assertEquals(myList.add("One", "2"), "1");
        assertEquals(myList.add("One", "1"), "2");
        assertEquals(myList.add("Two", "2"), null);
        assertEquals(myList.add("Three", "3"), null);

        assertEquals(myList.remove("Two"), "2");
        assertEquals(myList.get("Two"), null);

        assertEquals(myList.get("One"), "1");
        assertEquals(myList.get("Three"), "3");
    }
}