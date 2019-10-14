package ru.nspk.osks;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DIYArrayListTest {

    @Test
    public void testAddIntegers() {
        DIYArrayList<Integer> arr = new DIYArrayList<>();
        for (int i = 0; i < 30; i++) {
            arr.add(i);
        }
        assertEquals(30, arr.size());
        assertEquals((Integer) 0, arr.get(0));
        assertEquals((Integer) 29, arr.get(29));
    }

    @Test
    public void testAddStrings() {
        DIYArrayList<String> arr = new DIYArrayList<>();
        for (int i = 0; i < 30; i++) {
            arr.add(Integer.toString(i));
        }
        assertEquals(30, arr.size());
        assertEquals("0", arr.get(0));
        assertEquals("29", arr.get(29));
    }

    @Test
    public void testAddDoubles() {
        DIYArrayList<Double> arr = new DIYArrayList<>();
        for (int i = 0; i < 30; i++) {
            arr.add((double) i);
        }
        assertEquals(30, arr.size());
        assertEquals(0.0, arr.get(0), 0.0);
        assertEquals(29.0, arr.get(29), 0.0);
    }

    @Test
    public void testCollectionsAddAll() {
        DIYArrayList<Integer> arr = new DIYArrayList<>();
        for (int i = 0; i < 30; i++) {
            arr.add(i);
        }
        Collections.addAll(arr, 3, 5, 8);
        assertEquals(33, arr.size());
    }

    @Test
    public void testCollectionsCopy() {
        DIYArrayList<Integer> arr1 = new DIYArrayList<>();
        for (int i = 0; i < 30; i++) {
            arr1.add(i);
        }
        DIYArrayList<Integer> arr2 = new DIYArrayList<>(arr1.size());
        Collections.copy(arr2, arr1);
        assertEquals(30, arr2.size());
        assertArrayEquals(arr1.toArray(), arr2.toArray());
    }

    @Test
    public void testCollectionsSort() {
        List<Integer> arr1 = new ArrayList<>(Arrays.asList(1, 4, 7, 8));
        DIYArrayList<Integer> arr2 = new DIYArrayList<>(Arrays.asList(7, 4, 1, 8));
        Collections.sort(arr2);
        assertArrayEquals(arr1.toArray(), arr2.toArray());
    }

    @Test
    public void testIteratorNoSuchElementException() {
        java.util.Iterator iterator = new DIYArrayList<>().iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorConcurrentModificationException() {
        List<Integer> arr = new DIYArrayList<>();
        java.util.Iterator iterator = arr.iterator();
        arr.add(1);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void testContainsNull() {
        List<String> arr = new DIYArrayList<>();
        arr.add(null);
        assertTrue(arr.contains(null));
    }
}
