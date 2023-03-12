package edu.neu.cs5004.lab2;
import edu.neu.cs5004.lab2.Node;

public class DoubleList{
    public Node head, tail;
    public int size;

    public DoubleList(){
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // add node to the newest place
    public void addLast(Node node){
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        node.next = tail;
        size++;
    }

    // remove a node
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    // remove the least recently used one
    public Node removeFirst(){
        if(head.next == tail){
            return null;
        }
        Node n = head.next;
        remove(n);
        return n;
    }

    public int size(){
        return size;
    }
}
