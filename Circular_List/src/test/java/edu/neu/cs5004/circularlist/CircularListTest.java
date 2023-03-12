package edu.neu.cs5004.circularlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CircularListTest {
    CircularList<Integer> testList;
    Iterator<Integer> it;

    @BeforeEach
    void setUp() {
        testList = new CircularList<>(5);
    }

    @Test
    void add() {
        // [01234]
        // add 6
        // 6][1234
        // add 7
        // 67][234
        for (int i = 0; i < 5; i++) {
            testList.add(i);
            assertEquals(0, testList.getStart());
            assertEquals(i, testList.getEnd());
        }
        assertEquals(5, testList.size());
        testList.add(6);
        it = testList.iterator();
        assertEquals(5, testList.size());
        assertEquals(1, testList.getStart());
        assertEquals(0, testList.getEnd());
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        testList.add(7);
        it = testList.iterator();
        assertEquals(5, testList.size());
        assertEquals(2, testList.getStart());
        assertEquals(1, testList.getEnd());
        assertEquals(2, it.next());
    }

    @Test
    void randomAdd1() {
        // 56][234
        // remove 2
        // 56]2[34
        // add 9
        // 569][34
        for (int i = 0; i < 7; i++) {
            testList.add(i);
        }
        it = testList.iterator();
        assertEquals(2, it.next());
        assertEquals(2, testList.getStart());
        it.remove();
        assertFalse(testList.contains(2));
        assertEquals(4, testList.size());
        assertEquals(3, testList.getStart());
        assertEquals(1, testList.getEnd());
        testList.add(9);
        assertEquals(3, testList.getStart());
        assertEquals(2, testList.getEnd());
        assertEquals(5, testList.size());
    }
    @Test
    void randomAdd2() {
        // 56][234
        // remove 3
        // 6]6[245
        // add 8
        // 68][245
        testList = new CircularList<>(5);
        for(int i = 0; i < 7; i++){
            testList.add(i);
        }
        it = testList.iterator();
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        it.remove();
        assertEquals(2, testList.getStart());
        assertEquals(4, testList.size());
        assertEquals(4, it.next());
        assertFalse(testList.contains(3));
        testList.add(8);
        assertEquals(2, testList.getStart());
    }

    @Test
    void randomAdd3() {
        // 56][234
        // remove 4
        // 6]6[235
        // add 9
        // 69][234
        for (int i = 0; i < 7; i++) {
            testList.add(i);
        }
        it = testList.iterator();
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertEquals(4, it.next());
        it.remove();
        testList.add(9);
        assertTrue(testList.contains(9));
        assertEquals(2, testList.getStart());
        assertEquals(1, testList.getEnd());
        assertEquals(5, it.next());
        assertEquals(6, it.next());
        assertEquals(9, it.next());


    }
    @Test
    void contains() {
        for(int i = 0; i < 5; i++){
            testList.add(i*2);
            assertEquals(0, testList.getStart());
            assertEquals(i, testList.getEnd());
            assertTrue(testList.contains(i*2));
            assertFalse(testList.contains(i*2+1));
        }
    }

    @Test
    void SizeAdd() {
        for(int i = 0; i < 5; i++){
            testList.add(i);
            assertEquals(i+1, testList.size());
        }
        assertEquals(5, testList.size());
        for(int i = 5; i < 10; i++){
            testList.add(i);
            assertEquals(5, testList.size());
        }
    }

    @Test
    void SizeRemoveStart() {
        // [012]
        // remove start: 0
        // 0[12]
        // remove start: 1
        // 01[2]
        // remove start: 2
        // 012[], start = -1 means no elements in the list
        for(int i = 0; i < 3; i++){
            testList.add(i);
        }
        int i = 0;
        it = testList.iterator();
        assertTrue(it.hasNext());
        assertEquals(i++, it.next());
        it.remove();
        assertEquals(1, testList.getStart());
        assertEquals(2, testList.getEnd());
        assertEquals(2, testList.size());
        assertEquals(i++, it.next());
        it.remove();
        assertEquals(2, testList.getStart());
        assertEquals(2, testList.getEnd());
        assertEquals(1, testList.size());
        assertEquals(i++, it.next());
        it.remove();
        assertEquals(-1, testList.getStart());
        assertEquals(2, testList.getEnd());
        assertEquals(0, testList.size());
        assertFalse(it.hasNext());
    }

    @Test
    void SizeRemoveStartAtEnd() { // when start points to the last element, ensure index works
        // 5678][4
        // remove start(which points to the last element)
        // [5678]4
        for(int i = 0; i < 9; i++){
            testList.add(i);
        }
        it = testList.iterator();
        assertTrue(it.hasNext());
        assertEquals(4, it.next());
        it.remove();
        assertEquals(0, testList.getStart());
        assertEquals(3, testList.getEnd());
        assertEquals(4, testList.size());
    }

    @Test
    void SizeRemoveMiddleOrEnd() {
        // [012]
        // remove 1
        // [02]2
        // remove 2
        // [0]22, curPos == start == 0
        // remove 0
        // []022  start = -1, curPos = -2
        // add 9
        // False: [0]229 start = end = 0
        // True: [9]022

        for(int i = 0; i < 3; i++){
            testList.add(i);
        }
        int i = 0;
        it = testList.iterator();
        assertTrue(it.hasNext());
        assertEquals(i++, it.next());
        assertEquals(i++, it.next());
        assertEquals(0, testList.getStart());
        assertEquals(2, testList.getEnd());
        assertEquals(3, testList.size());
        it.remove();
        assertEquals(0, testList.getStart());
        assertEquals(1, testList.getEnd());
        assertEquals(2, testList.size());
        assertEquals(i++, it.next());
        it.remove();
        assertEquals(0, testList.getStart());
        assertEquals(0, testList.getEnd());
        assertEquals(1, testList.size());
        assertFalse(it.hasNext());
        it = testList.iterator();
        assertEquals(0, it.next());
        it.remove();
        testList.add(9);
        assertEquals(0, testList.getStart());
        assertEquals(0, testList.getEnd());
        assertEquals(1, testList.size());
        assertEquals(9, it.next());
    }

    @Test
    void NoElementsToRemove(){
        // throw exceptions when we remove twice after next()
        for(int i = 0; i < 5; i++){
            testList.add(i);
        }
        int i = 0;
        it = testList.iterator();
        assertTrue(it.hasNext());
        assertEquals(i, it.next());
        it.remove();
        assertThrows(IllegalStateException.class, () -> it.remove());
    }

    @Test
    void iterator() {
        int i = 0;
        for(; i < 5; i++){
            testList.add(i);
        }

        for(i = 0, it = testList.iterator(); it.hasNext();i++){
            assertEquals(i, it.next());
        }
        for(i = 0; i < 5; i++){
            testList.add(i*2+1);
        }
        for(i = 0, it = testList.iterator(); it.hasNext();i++){
            assertEquals(i*2+1, it.next());
        }
    }

    @Test
    void noNextElementException(){ // throw
        it = testList.iterator();
        assertThrows(NoSuchElementException.class, () ->  it.next());
        for(int i = 0; i < 5; i++){
            testList.add(i);
        }
        it = testList.iterator();
        while(it.hasNext()){it.next();}
        assertThrows(NoSuchElementException.class, () ->  it.next());
    }

    @Test
    void forEach() {
        int i = 0;
        for(; i < 5; i++){
            testList.add(i);
        }
        i = 0;
        testList.forEach(System.out::println);
        it = testList.iterator();
        assertEquals(0, it.next());
        it.forEachRemaining(System.out::println);
    }
}