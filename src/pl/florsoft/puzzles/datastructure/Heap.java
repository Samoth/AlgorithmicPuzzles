package pl.florsoft.puzzles.datastructure;

import java.util.NoSuchElementException;

public class Heap<E extends Comparable<E>> {

    private Object[] array;
    private int lastElem = -1;

    public Heap(int maxHeapSize) {
        array = new Object[maxHeapSize];
    }

    public void insert(E e) {
        if (lastElem + 1 >= array.length) {
            throw new IllegalStateException(); // heap is full
        }
        array[++lastElem] = e;
        moveUpIfNeeded(lastElem);
    }

    @SuppressWarnings("unchecked")
    private void moveUpIfNeeded(int idx) {
        int parent = getParentIdx(idx);
        if (idx == 0 || ((E) array[idx]).compareTo((E) array[parent]) > 0) {
            return;
        }
        swap(idx, parent);
        moveUpIfNeeded(parent);
    }

    @SuppressWarnings("unchecked")
    public E extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E toReturn = (E) array[0];
        swap(0, lastElem--);
        moveDownIfNeeded(0);
        return toReturn;
    }

    @SuppressWarnings("unchecked")
    private void moveDownIfNeeded(int idx) {
        int leftChildIdx = getLeftChildIdx(idx);
        int rightChildIdx = getRightChildIdx(idx);
        if (leftChildIdx <= lastElem) {
            int idxToCheck = rightChildIdx <= lastElem && ((E) array[rightChildIdx]).compareTo((E) array[leftChildIdx]) < 0
                    ? rightChildIdx : leftChildIdx;
            if (((E) array[idxToCheck]).compareTo((E) array[idx]) < 0) {
                swap(idx, idxToCheck);
                moveDownIfNeeded(idxToCheck);
            }
        }
    }

    public int size() {
        return lastElem + 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int getParentIdx(int idx) {
        return (int) Math.ceil((double) idx / 2) - 1;
    }

    private int getLeftChildIdx(int idx) {
        return 2 * idx + 1;
    }

    private int getRightChildIdx(int idx) {
        return 2 * idx + 2;
    }

    private void swap(int idx1, int idx2) {
        Object tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }

}
