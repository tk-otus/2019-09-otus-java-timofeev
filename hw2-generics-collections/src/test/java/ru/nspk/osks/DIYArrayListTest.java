package ru.nspk.osks;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

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
    }

    @Test
    public void testCollectionsSort() {
        List<Integer> arr1 = new ArrayList<>(Arrays.asList(7, 4, 1, 8));
        DIYArrayList<Integer> arr2 = new DIYArrayList<>(arr1.size());
        Collections.copy(arr2, arr1);
        Collections.sort(arr1);
        Collections.sort(arr2);
        assertEquals(arr1, arr2);
    }
}
