package pl.florsoft.puzzles.datastructure.list;

import java.util.NoSuchElementException;

public class ArrayList<E> {

    private int size = 0;
    private int maxSize;
    private Object[] values;

    public ArrayList(int maxSize) {
        this.maxSize = maxSize;
        this.values = new Object[maxSize];
    }

    public void add(E val) {
        if (size + 1 > maxSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        values[size] = val;
        size++;
    }

    public void add(E val, int idx) {
        if (size + 1 > maxSize || idx >= size || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(values, idx, values, idx + 1, size - idx);
        values[idx] = val;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int idx) {
        if (idx >= size || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) values[idx];
    }

    public E remove(E val) {
        int idx = indexOf(val);
        if (idx == -1) {
            throw new NoSuchElementException();
        }
        if (idx < size - 1) {
            System.arraycopy(values, idx + 1, values, idx, size - 1 - idx);
        }
        size--;
        return val;
    }

    @SuppressWarnings("unchecked")
    public E remove(int idx) {
        if (idx >= size || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E elem = (E) values[idx];
        if (idx < size - 1) {
            System.arraycopy(values, idx + 1, values, idx, size - 1 - idx);
        }
        size--;
        return elem;
    }

    public boolean contains(E val) {
        return indexOf(val) >= 0;
    }

    public int indexOf(E val) {
        int resultIdx = -1;
        int currentIdx = 0;
        while (currentIdx < size) {
            if (values[currentIdx] == null && val == null
                    || values[currentIdx] != null && values[currentIdx].equals(val)) {
                resultIdx = currentIdx;
                break;
            }
            currentIdx++;
        }
        return resultIdx;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(values[i]);
        }
        sb.append(" ]");
        return sb.toString();
    }

}
