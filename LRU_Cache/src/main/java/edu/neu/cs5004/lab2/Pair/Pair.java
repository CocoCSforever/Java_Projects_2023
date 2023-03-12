/*package edu.neu.cs5004.lab2.Pair;

import java.util.Objects;

public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K k, V v){
        key = k;
        value = v;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;
        return Objects.equals(getKey(), pair.getKey()) && Objects.equals(getValue(), pair.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}*/
