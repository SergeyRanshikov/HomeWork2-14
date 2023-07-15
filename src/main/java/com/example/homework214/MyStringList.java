package com.example.homework214;

import org.apache.xerces.xs.StringList;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyStringList{
    private String[] array;
    private int size;

    public MyStringList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative");
        }
        array = new String[initialCapacity];
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void resizeArray(int newSize) {
        String[] newArray = new String[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }


    public String add(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (size == array.length) {
            resizeArray(array.length * 2);
        }
        array[size] = item;
        size++;
        return item;
    }


    public String add(int index, String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size == array.length) {
            resizeArray(array.length * 2);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
        return item;
    }


    public String set(int index, String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        checkIndex(index);
        String oldItem = array[index];
        array[index] = item;
        return oldItem;
    }


    public String remove(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Item not found");
    }


    public String remove(int index) {
        checkIndex(index);
        String removedItem = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        array[size] = null;
        if (array.length > 4 && size < array.length / 4) {
            resizeArray(array.length / 2);
        }
        return removedItem;
    }


    public boolean contains(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return true;
            }
        }
        return false;
    }


    public int indexOf(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }


    public int lastIndexOf(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }


    public String get(int index) {
        checkIndex(index);
        return array[index];
    }


//    public boolean equals(StringList otherList) {
//        if (otherList == null) {
//            throw new IllegalArgumentException("Other list cannot be null");
//        }
//        if (size != otherList.size()) {
//            return false;
//        }
//        for (int i = 0; i < size; i++) {
//            if (!array[i].equals(otherList.get(i))) {
//                return false;
//            }
//        }
//        return true;
//    }




    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }


    public String[] toArray() {
        return Arrays.copyOf(array, size);
    }
}
