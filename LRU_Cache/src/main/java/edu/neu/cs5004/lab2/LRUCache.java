package edu.neu.cs5004.lab2;

import edu.neu.cs5004.lab2.exceptions.ItemNotFoundException;
import java.util.HashMap;

public class LRUCache<K, V> {
    private static int DEFAULT_SIZE=1000;
    public int maxSize;

    DoubleList list;
    HashMap<K, Node> map;

    public LRUCache() {
         this(DEFAULT_SIZE);
    }
    public LRUCache(int size) {
        this.maxSize = size;
        map = new HashMap<>();
        list = new DoubleList();
    }

    public void addItem(K key, V item) {
        if(map.containsKey(key)){
            delete(key);
            addRecently(key, item);
            return;
        }
        if(map.size() == maxSize) {
            removeLRU();
        }
        addRecently(key, item);
    }

    public V removeItem(K key) throws ItemNotFoundException {
        if(!map.containsKey(key)){
            throw new ItemNotFoundException(key + "doesn't exist in the cache.");
        }
        return delete(key);
    }

    public V getItem(K key) throws ItemNotFoundException{
        if(!map.containsKey(key)){
            throw new ItemNotFoundException(key + "doesn't exist in the cache.");
        }
        makeRecently(key);
        return (V)map.get(key).val;
    }

    //----------helper----------//
//    Item must exist to call makeRecently:
//    make a current item a recent one
    private void makeRecently(K key){
        Node<K, V> node = map.get(key);
        list.remove(node);
        list.addLast(node);
    }
//    Add an item and make it a recent one
    private void addRecently(K key, V val){
        Node<K, V> node = new Node<>(key, val);
        map.put(key, node);
        list.addLast(node);
    }
//    Item must exist
//    delete the item with [key] from cache(list and map) and return it.
    private V delete(K key){
        Node<K, V> node = map.get(key);
        list.remove(node);
        return (V)map.remove(key).val;
    }
//    remove the least recently used item
    private void removeLRU(){
        Node<K, V> node = list.removeFirst();
        map.remove(node.key);
    }
}
