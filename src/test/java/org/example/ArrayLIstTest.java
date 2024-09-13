package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}