package edu.neu.cs5004.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testConstructor() {
        Node node = new Node("a", "b");
        assertEquals("a", node.key);
        assertEquals("b", node.val);
        node = new Node("a", 1);
        assertEquals("a", node.key);
        assertEquals(1, node.val);
        node = new Node(3, 1);
        assertEquals(3, node.key);
        assertEquals(1, node.val);
        node = new Node('a', "abcde");
        assertEquals('a', node.key);
        assertEquals("abcde", node.val);

    }
    @Test
    void testEquals() {
        Node node1 = new Node("a", "b");
        Node node2 = new Node("a", "b");
        assertEquals(node1, node2);
        node2.val = "c";
        assertNotEquals(node1, node2);
    }
}