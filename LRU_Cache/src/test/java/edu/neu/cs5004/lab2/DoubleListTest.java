package edu.neu.cs5004.lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleListTest {
    public DoubleList testList;
    @BeforeEach
    void setUp() {
        testList = new DoubleList();
    }

    @Test
    void addLast() {
        Node node = new Node("1", "student1");
        testList.addLast(node);
        assertEquals(node, testList.tail.prev);
        node = new Node("2", "student2");
        testList.addLast(node);
        assertEquals(node, testList.tail.prev);
    }

    @Test
    void remove() {
        Node node1 = new Node("1", "student1");
        testList.addLast(node1);
        Node node2 = new Node("2", "student2");
        testList.addLast(node2);
        Node node3 = new Node("3", "student3");
        testList.addLast(node3);
        assertEquals(node1, testList.head.next);
        assertEquals(node3, testList.tail.prev);
        testList.remove(node1);
        assertEquals(2, testList.size());
        assertEquals(node2, testList.head.next);
        testList.remove(node3);
        assertEquals(1, testList.size());
        assertEquals(node2, testList.head.next);
        assertEquals(node2, testList.tail.prev);
    }

    @Test
    void removeFirst() {
        Node node1 = new Node("1", "student1");
        testList.addLast(node1);
        Node node2 = new Node("2", "student2");
        testList.addLast(node2);
        Node node3 = new Node("3", "student3");
        testList.addLast(node3);
        assertEquals(node1, testList.removeFirst());
        assertEquals(node2, testList.removeFirst());
        assertEquals(node3, testList.removeFirst());
        assertNull(testList.removeFirst());
    }


    @Test
    void size() {
        testList.addLast(new Node("1", "student1"));
        testList.addLast(new Node("1", "student1"));
        testList.addLast(new Node("1", "student1"));
        assertEquals(3, testList.size());
        testList.addLast(new Node("2", "student2"));
        testList.addLast(new Node("3", "student3"));
        assertEquals(5, testList.size());
    }
}