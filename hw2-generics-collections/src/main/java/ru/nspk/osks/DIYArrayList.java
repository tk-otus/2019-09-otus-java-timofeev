package ru.nspk.osks;

import java.util.*;
import java.util.function.Consumer;

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
    private int size;
    protected int modCount;


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

    public DIYArrayList(Collection<? extends T> t) {
        elementData = t.toArray();
        size = elementData.length;
        if (size != 0) {
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    @Override
    public boolean add(T o) {
        modCount++;
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = o;
        return true;
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

    private T elementData(int index) {
        return (T) elementData[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (Object e : elementData) {
                if (e == null) {
                    return true;
                }
            }
        } else {
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
        return new Iterator<>();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<>(0);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    // region Unsupported Operations
    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
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
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    // endregion

    private Object[] grow() {
        return Arrays.copyOf(elementData, size + 1);
    }

    private class Iterator<E> implements java.util.Iterator<E> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            checkForModification();
            int c = cursor;
            if (c >= size) {
                throw new NoSuchElementException();
            }
            lastRet = cursor;
            cursor++;
            return (E) elementData[c];
        }

        final void checkForModification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListIterator<E> extends Iterator<E> implements java.util.ListIterator<E> {

        ListIterator(int index) {
            super();
            cursor = index;
        }

        @Override
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForModification();

            try {
                DIYArrayList.this.set(lastRet, (T) e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        // region Unsupported Operations
        @Override
        public E previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer action) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
        // endregion
    }
}
