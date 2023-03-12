package edu.neu.cs5004.lab2;

import java.util.Objects;

public class Node<K, V>{
    public K key;
    public V val;
    public Node prev, next;

    public Node(K key, V val){
        this.key = key;
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node<?, ?> node)) return false;
        return Objects.equals(key, node.key) && Objects.equals(val, node.val);
    }
}
