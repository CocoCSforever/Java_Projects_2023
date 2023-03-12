package edu.neu.cs5004.circularlist;


import java.util.*;
import java.util.function.Consumer;

public class CircularList<T> implements Iterable<T> {
    private LinkedList<T> list = new LinkedList<>();
    private int start, end;
    private int size;

    public CircularList(int size){
        this.size = size;
        start = -1; // default value for start == no elements in list
    }

    public class CircularIterator implements Iterator<T> {
        private int currentPosition;
        private boolean removable;
        private int indexRemove; // only needed for  version2

        public CircularIterator(){
            currentPosition = -2; // default value for start == no elements remained for iterator
            removable = false;
        }

        @Override
        public boolean hasNext() { // should not change currentPosition
            if(currentPosition == -2){
                if(start == -1) return false;
//                currentPosition = (start - 1 + size)%size;
                return true;
            }
            if (currentPosition == end)
                return false;
            else{
                return true;
            }
        }

        @Override
        public T next() {
            if(hasNext()){
                if(currentPosition == -2){
                    currentPosition = (start - 1 + list.size())%list.size();
                }
                currentPosition = (currentPosition+1)%list.size();
                removable = true;
                indexRemove = currentPosition;
                return list.get(currentPosition);
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            //----------VERSION 1------//
            // loop through list to reset value to every index after removed element
            if (removable) {
                // if remove the last one, set start to -1
                if (size() == 1) {
                    start = -1;
                    currentPosition = -2;
                    removable = false;
                    return;
                }

                if (currentPosition == start) {
                    start = (start + 1) % (list.size());
                    currentPosition = -2;
                    removable = false;
                    return;
                }

                for (int temp = currentPosition; temp != end; ) {
                    list.set(temp, list.get((temp + 1) % list.size()));
                    temp = (temp + 1) % list.size();
                }
                currentPosition = (currentPosition - 1 + list.size()) % list.size();
                end = (end - 1 + list.size()) % list.size();
                removable = false;
                return;
            }
            throw new IllegalStateException();
        }
            //----------VERSION 2-------//
            // Saved for Notes, should use diff test case
            // use LinkedList.remove and reset pointers
//            if(removable) {
//                if(size() == 1){
//                    start = -1;
//                    currentPosition = -2;
//                    removable = false;
//                    return;
//                }
//                // especially when size()==2,
//                if(currentPosition == start){
//                    start = (start+1)%(list.size());
//                    currentPosition = -2;
//                    removable = false;
//                    return;
//                }
//                list.remove(currentPosition);
//                currentPosition = (currentPosition-1+list.size())%list.size();
//                end = (end-1+list.size())%list.size();
//                removable = false;
//                return;
//            }
//            throw new IllegalStateException();

        //Extra credit
        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            while (hasNext()){
                action.accept(next());
            }
        }
    }

    public void add(T item) {
        //----------VERSION 2-----------// MORE CLEAR ONE
        if(start == -1){
            end = -1;
        }

        // update value of end
        end = (end + 1) % size;

        // Add item
        if (list.size() == size) {
            // once we reach the max size, the list.size() won't change anymore
            list.set(end, item);
        } else {
            // list.size() < size
            list.add(end, item);
        }

        // Update start

        // linkedList is full, we should change start pointer, thus start++
        if (end == start) {
            start = (start + 1) % size;
        }
        // if start = -1, it's the only condition that we may have start = end after adding
        if(start == -1){
            start = end;
        }
    }

    public boolean contains(T item) {
        for(T t: this){
            if(t.equals(item)) return true;
        }
        return false;
    }

    public int size() {
        if(start == -1) return 0;
        return end >= start? end-start+1: end-start+size+1;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    @Override
    public Iterator<T> iterator() {
        return new CircularIterator();
    }

    //Extra credit
    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            action.accept(it.next());
        }
    }
}
/* Test case for remove and add
 // e < s
            // list.size() >  s--e           list.size() == s->e
            // 5]1[23 X                         56][23 X
            // add 8                            add 9
            // end++, list.set(end, 8)          end++, list.add(end, 9)
            // 58][23 X                         569][23

            // add to full list
            // 569][23   -->   5698][3

            // s < e
            // [012] XX                         [012]45
            // add 3                            add 3
            // end++, list.add(3)/list.set(end, 3)      end++, list.set(end, 3)
            // [0123]                           [0123]5
            // # Warning: if use add instead of set
            // we'll get [0123]45, when we loop through, we use list.size()
            // as the base size, will create problem

            // []022  start = -1, curPos = -2
            // add 9
            // False: [0]229 start = end = 0
            // True: [9]022
 */

/*
//------VERSION 1---------//
if(start == -1){
        // should change this part, when linkedlist is full, should not add more items
//            list.add(item);
//            start = 0;
//            end = 0;
//        }else {
//            if (size() == size) { // circular list is full
//                list.set(start, item);
//                end = start;
//                start = (start + 1) % size;
//            } else {
//                if (start <= end) {
//                    end = (end + 1) % size;
//                    list.add(end, item);
//                } else {
//                    end = (end + 1) % size;
//                    list.set(end, item);
//                }
//            }
//        }
 */