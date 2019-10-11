package ru.nspk.osks;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class DIYArrayList<T> implements List<T> {
    /**
     * Общий пустой экземпляр массива, используемый для пустых экземпляров.
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * Массив объектов, который хранит элементы DIYArrayList.
     * Емкость DIYArrayList - это длина этого массива объектов.
     */
    private Object[] elementData; // non-private to simplify nested class access

    /**
     * Размен DIYArrayList (количество элементов которые он содержит).
     */
    private int size = 0;


    public DIYArrayList() {
        elementData = EMPTY_ELEMENTDATA;
    }

    public DIYArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        size = initialCapacity;
        elementData = new Object[initialCapacity];
    }


    /**
     * Возвращает количество элементов в списке. Если список содержит
     * более {@code Integer.MAX_VALUE} элементов, вернется
     * {@code Integer.MAX_VALUE}.
     *
     * @return количество элементов в списке
     */
    @Override
    public int size() {
        return size;
    }

    T elementData(int index) {
        return (T) elementData[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!isEmpty()) {
            for (Object e : elementData) {
                if (o.equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public Iterator<T> iterator() {
        return (ListIterator<T>) Arrays.asList(elementData).listIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T o) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = o;
        return true;
    }

    @Override
    public boolean remove(Object o) {

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return (ListIterator<T>) Arrays.asList(elementData).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private Object[] grow() {
        return elementData = Arrays.copyOf(elementData, size + 1);
    }
}
