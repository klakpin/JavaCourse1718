package Tests;

import runtime.main.MyHashMap;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @org.junit.jupiter.api.Test
    void addContains() {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        assertEquals(myHashMap.put("One", "1"), null);
        assertEquals(myHashMap.put("One", "2"), "1");
        assertEquals(myHashMap.put("Two", "2"), null);
        assertEquals(myHashMap.put("Three", "3"), null);
    }

}