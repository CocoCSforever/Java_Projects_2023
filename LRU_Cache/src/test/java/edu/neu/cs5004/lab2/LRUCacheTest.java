package edu.neu.cs5004.lab2;

import edu.neu.cs5004.lab2.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    public final int testSize = 5;
    public LRUCache<String, String> testCache;
//    public Node node;
    public Random r = new Random();
    @BeforeEach
    void setUp() {
        testCache = new LRUCache<>(testSize);
    }

    @Test
    void constructor() {
        testCache = new LRUCache<>();
        assertEquals(1000,testCache.maxSize);

    }

    @Test
    void addSingleItem(){
        testCache.addItem("1", "student1");
        assertEquals(1,testCache.map.size());
        assertEquals(1,testCache.list.size());
        assertEquals(new Node<String, String>("1", "student1"), testCache.map.get("1"));
    }

    @Test
    void addMultipleItem(){
        testCache.addItem("1", "student1");
        testCache.addItem("2", "student2");
        testCache.addItem("3", "student3");
        testCache.addItem("4", "student4");
        testCache.addItem("5", "student5");
        assertEquals(5,testCache.map.size());
        assertEquals(5,testCache.list.size());
        assertEquals(new Node<String, String>("1", "student1"), testCache.list.head.next);
        assertEquals(new Node<String, String>("5", "student5"), testCache.list.tail.prev);
        String curKey = String.valueOf(r.nextInt(5)+1);
        assertEquals(new Node<String, String>(curKey, "student"+curKey), testCache.map.get(curKey));
    }

    @Test
    void addDuplicateItem(){
        // add item with key but updated its value
        Node testNode = new Node<String, String>("5", "student5copy");
        testCache.addItem("5", "student5");
        testCache.addItem("5", "student5copy");
        assertEquals(1,testCache.map.size());
        assertEquals(1,testCache.list.size());
        assertEquals(testNode, testCache.map.get("5"));
        assertEquals(testNode, testCache.list.head.next);
        // add duplicate item
        testCache.addItem("5", "student5copy");
        assertEquals(1,testCache.map.size());
        assertEquals(1,testCache.list.size());
        assertEquals(testNode, testCache.list.head.next);
        assertEquals(testNode, testCache.list.tail.prev);
    }

    @Test
    void addExceedSize(){
        testCache.addItem("1", "student1");
        testCache.addItem("2", "student2");
        testCache.addItem("3", "student3");
        testCache.addItem("4", "student4");
        testCache.addItem("5", "student5");
        testCache.addItem("6", "student6");
        testCache.addItem("7", "student7");
        assertEquals(5,testCache.map.size());
        assertEquals(5,testCache.list.size());
        assertEquals(new Node<String, String>("3", "student3"), testCache.list.head.next);
        assertEquals(new Node<String, String>("7", "student7"), testCache.list.tail.prev);
        assertFalse(testCache.map.containsKey("1"));
        assertFalse(testCache.map.containsKey("2"));
    }

    @Test
    void removeSingleItem() throws ItemNotFoundException {
        testCache.addItem("1", "student1");
        testCache.addItem("2", "student2");
        testCache.addItem("3", "student3");
        testCache.addItem("4", "student4");
        testCache.addItem("5", "student5");
        testCache.removeItem("3");
        assertEquals(4,testCache.map.size());
        assertEquals(4,testCache.list.size());
        assertFalse(testCache.map.containsKey("3"));
        assertEquals(new Node<String, String>("1", "student1"), testCache.list.head.next);
        assertEquals(new Node<String, String>("5", "student5"), testCache.list.tail.prev);
    }

    @Test
    void removeMultipleItem() throws ItemNotFoundException {
        testCache.addItem("1", "student1");
        testCache.addItem("2", "student2");
        testCache.addItem("3", "student3");
        testCache.addItem("4", "student4");
        testCache.addItem("5", "student5");
        testCache.removeItem("3");
        testCache.removeItem("5");
        testCache.removeItem("1");
        assertEquals(2,testCache.map.size());
        assertEquals(2,testCache.list.size());
        assertFalse(testCache.map.containsKey("3"));
        assertFalse(testCache.map.containsKey("5"));
        assertFalse(testCache.map.containsKey("1"));
        assertEquals(new Node<String, String>("2", "student2"), testCache.list.head.next);
        assertEquals(new Node<String, String>("4", "student4"), testCache.list.tail.prev);
        assertEquals(new Node<String, String>("4", "student4"), testCache.map.get("2").next);
    }

    @Test
    void removeException() {
        testCache.addItem("1", "student1");
        assertThrows(ItemNotFoundException.class, () -> testCache.removeItem("3"));
        assertThrows(ItemNotFoundException.class, () -> testCache.removeItem("2"));
        assertThrows(ItemNotFoundException.class, () -> testCache.removeItem("0"));
    }

    @Test
    void getSingleItem() throws ItemNotFoundException {
        testCache.addItem("1", "student1");
        testCache.addItem("2", "student2");
        testCache.addItem("3", "student3");
        testCache.addItem("4", "student4");
        testCache.addItem("5", "student5");
        assertEquals("student2", testCache.getItem("2"));
        assertEquals(5,testCache.map.size());
        assertEquals(5,testCache.list.size());
        assertEquals(new Node<String, String>("2", "student2"), testCache.list.tail.prev);
        assertEquals(new Node<String, String>("5", "student5"), testCache.list.tail.prev.prev);
        assertEquals(new Node<String, String>("3", "student3"), testCache.list.head.next.next);
    }

    @Test
    void getMultipleItem() throws ItemNotFoundException {
        testCache.addItem("1", "student1");
        testCache.addItem("2", "student2");
        testCache.addItem("3", "student3");
        testCache.addItem("4", "student4");
        testCache.addItem("5", "student5");
        assertEquals("student2", testCache.getItem("2"));
        assertEquals("student4", testCache.getItem("4"));
        assertEquals("student1", testCache.getItem("1"));
        assertEquals("student5", testCache.getItem("5"));
        assertEquals("student3", testCache.getItem("3"));
        assertEquals(5,testCache.map.size());
        assertEquals(5,testCache.list.size());
        assertEquals(new Node<String, String>("3", "student3"), testCache.list.tail.prev);
        assertEquals(new Node<String, String>("5", "student5"), testCache.list.tail.prev.prev);
        assertEquals(new Node<String, String>("2", "student2"), testCache.list.head.next);
        assertEquals(new Node<String, String>("4", "student4"), testCache.list.head.next.next);
    }

    @Test
    void getException() throws ItemNotFoundException {
        testCache.addItem("1", "student1");
        testCache.addItem("2", "student2");
        assertThrows(ItemNotFoundException.class, () -> testCache.getItem("3"));
        assertThrows(ItemNotFoundException.class, () -> testCache.getItem("4"));
        assertThrows(ItemNotFoundException.class, () -> testCache.getItem("0"));
        assertEquals(2,testCache.map.size());
        assertEquals(2,testCache.list.size());
    }
}