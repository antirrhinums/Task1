package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
    }

    @Test
    void testAddElement() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testAddElementAtIndex() {
        list.add(1);
        list.add(3);
        list.add(2, 1);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testGetElement() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testRemoveElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals(3, list.get(1));
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testSortUsingComparable() {
        list.add(3);
        list.add(2);
        list.add(1);
        list.sort(null);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testSortUsingComparator() {
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort((a, b) -> b - a);
        assertEquals(3, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(1, list.get(2));
    }

    @Test
    void testSetElement() {
        list.add(1);
        list.add(3);
        list.set(1, 2);
        assertEquals(2, list.get(1));
    }

    @Test
    void testOutOfBounds() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 7));
    }

    @Test
    void testAddElementLarge(){
        for(int i = 0; i < 1000; ++i){
            list.add(i);
        }

        assertEquals(list.size(), 1000);
        assertEquals(list.get(0), 0);
        assertEquals(list.get(999), 999);
    }

    @Test
    void testAddElementAtIndexLarge() {
        for(int i = 0; i < 997; ++i){
            list.add(i);
        }

        list.add(0xaa, 500);
        list.add(0xbb, 600);
        list.add(0xcc, 700);

        assertEquals(list.size(), 1000);
        assertEquals(list.get(0), 0);

        assertEquals(list.get(500), 0xaa);
        assertEquals(list.get(600), 0xbb);
        assertEquals(list.get(700), 0xcc);

        assertEquals(list.get(999), 996);
    }

    @Test
    void testGetElementLarge(){
        for(int i = 0; i < 1000; ++i){
            list.add(i);
        }

        assertEquals(list.get(0), 0);
        assertEquals(list.get(500), 500);
        assertEquals(list.get(list.size() - 1), list.size() - 1);
    }

    @Test
    void testRemoveElementLarge() {
        for (int i = 0; i < 1000; ++i) {
            list.add(i);
        }

        list.remove(0);
        list.remove(list.size() - 1);
        list.remove(list.size() / 2);

        assertEquals(997, list.size());

        while(list.size() > 0){
            list.remove(0);
        }

        assertEquals(0, list.size());
    }

    @Test
    void testClearLarge() {
        for (int i = 0; i < 1000; ++i) {
            list.add(i);
        }

        list.clear();

        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testSortUsingComparableLarge() {
        Random random = new Random();
        for (int i = 0; i < 1000; ++i) {
            list.add(random.nextInt(10000));
        }

        list.sort(null);

        for (int i = 1; i < list.size(); ++i) {
            assertTrue(list.get(i - 1) <= list.get(i));
        }
    }

    @Test
    void testSortUsingComparatorLarge() {
        Random random = new Random();
        for (int i = 0; i < 1000; ++i) {
            list.add(random.nextInt(10000));
        }

        list.sort((a,b) -> b - a);

        for (int i = 1; i < list.size(); ++i) {
            assertTrue(list.get(i - 1) >= list.get(i));
        }
    }

    @Test
    void testSetElementLarge() {
        for (int i = 0; i < 1000; ++i) {
            list.add(i);
        }

        list.set(500, 9999);

        assertEquals(9999, list.get(500));
    }
}